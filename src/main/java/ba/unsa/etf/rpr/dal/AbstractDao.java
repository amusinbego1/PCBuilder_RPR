package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.Idable;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private Connection connection;
    private String tableName;

    public AbstractDao(String tableName){
        try {
            this.tableName = tableName;
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = properties.getProperty("db.connection");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            try{
                this.connection = DriverManager.getConnection(url, username, password);
            }
            catch(SQLException e){
                String localUsername = properties.getProperty("localdb.username");
                String localPassword = properties.getProperty("localdb.password");
                this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pcbuilder?createDatabaseIfNotExist=true", localUsername, localPassword);
                injectSQLDatabase();
            }
        } catch (IOException | PCBuilderException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void injectSQLDatabase() throws PCBuilderException{
        try(DataInputStream input = new DataInputStream(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("dbdump.sql")))){
            String DbSql = new String(input.readAllBytes());
            for(String sqlCommand: DbSql.split(";")){
                Statement statement = this.connection.createStatement();
                statement.execute(sqlCommand + ";");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new PCBuilderException("Database dump failed");
        }
    }
    protected String getTableName() {
        return tableName;
    }

    public abstract T rowToObject(ResultSet resultSet) throws PCBuilderException;

    public abstract Map<String, Object> objectToRow(T object) throws PCBuilderException;

    public List<T> executeQuery(String query, Object[] parameters) throws PCBuilderException {
        List<T> resultList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            if (parameters != null)
                for (int i = 0; i < parameters.length; i++)
                    preparedStatement.setObject(i + 1, parameters[i]);

            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet != null && resultSet.next())
                    resultList.add(rowToObject(resultSet));
            }
        } catch (SQLException e) {
            throw new PCBuilderException("Incorrect query");
        }
        return resultList;
    }

    @Override
    public T getById(int id) throws PCBuilderException {
        try{
            return executeQuery("SELECT * FROM " + this.tableName + " WHERE " + this.tableName.substring(0, tableName.length()-1) + "_id = ?;", new Object[]{id}).get(0);
        }
        catch(Exception e){
            throw new PCBuilderException("Cannot get item by id");
        }
    }

    @Override
    public void deleteById(int id) throws PCBuilderException {
        try{
            executeQuery("DELETE FROM " + this.tableName + " WHERE " + this.tableName.substring(0, tableName.length()-1) + "_id = ?;", new Object[]{id});
        }
        catch(Exception e){
            throw new PCBuilderException("Cannot delete item by id");
        }
    }

    @Override
    public List<T> getAll() throws PCBuilderException {
        try{
            return executeQuery("SELECT * FROM " + this.tableName, null);
        }
        catch(Exception e){
            throw new PCBuilderException("Cannot get all items");
        }
    }

    @Override
    public T update(T item) throws PCBuilderException {
        try{
            String enhancedColumnNames = getColumnNames().replaceAll(",", " = ?,") + " = ? ";
            executeQuery("UPDATE " + this.tableName +  " SET " + enhancedColumnNames + " WHERE " + this.tableName.substring(0, tableName.length()-1) + "_id = ?;",
                    ArrayUtils.addAll(getItemArray(item), item.getId()));
        }
        catch(PCBuilderException e){
            throw new PCBuilderException("Cannot update item");
        }
        return item;
    }

    private String idZeroCheck(T item, String columnNames){
        if(item.getId() == 0){
            StringBuilder names = new StringBuilder(columnNames);
            return names.delete(0, columnNames.indexOf(",") + 1).toString();
        }
        return columnNames;
    }

    @Override
    public T add(T item) throws PCBuilderException {
        try{
            String columnNames = idZeroCheck(item, getColumnNames());
            executeQuery("INSERT INTO " + this.tableName + " (" + columnNames + ") VALUES (" + "?,".repeat(StringUtils.countMatches(columnNames, ","))  + " ?);", getItemArray(item));
        }
        catch(PCBuilderException e){
            throw new PCBuilderException("Cannot add item");
        }
        return item;
    }

    private Object[] getItemArray(T item) throws PCBuilderException{
        List<Object> rowList = new ArrayList<>();
        Map<String, Object> row = objectToRow(item);
        for(String key: row.keySet())
            rowList.add(row.get(key));
        if(!rowList.isEmpty() && item.getId() == 0)
            rowList.remove(0);
        return rowList.toArray();
    }


    private String getColumnNames() throws PCBuilderException {
        List<String> columnNamesList = new ArrayList<>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSetMetaData rsMetaData = statement.executeQuery("SELECT * FROM " + tableName + " WHERE 1=0;").getMetaData();
            for(int i=1; i<=rsMetaData.getColumnCount(); i++)
                columnNamesList.add(rsMetaData.getColumnName(i));
        } catch (SQLException e) {
            throw new PCBuilderException("Cannot get column names");
        }
        return columnNamesList.toString().trim().substring(1, columnNamesList.toString().trim().length()-1);
    }


}
