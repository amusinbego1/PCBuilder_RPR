package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.Idable;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractDao<T extends Idable> implements Dao<T> {
    private Connection connection;
    private String tableName;

    public AbstractDao(String tableName) {
        try {
            this.tableName = tableName;
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemResource("application.properties").openStream());
            String url = properties.getProperty("db.connection");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    public abstract T rowToObject(ResultSet resultSet) throws PCBuilderException;

    public abstract Map<String, Object> objectToRow(T object);

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
            T item =  executeQuery("SELECT * FROM " + this.tableName + " WHERE " + this.tableName.substring(0, tableName.length()-1) + "_id = ?;", new Object[]{id}).get(0);
            return item;
        }
        catch(Exception e){
            throw new PCBuilderException("Cannot get item by id");
        }
    }

    @Override
    public T add(T item) throws PCBuilderException {
        try{
            String columnNames = getColumnNames();
            executeQuery("INSERT INTO " + this.tableName + " (" + columnNames + ") VALUES (" + "?,".repeat(StringUtils.countMatches(columnNames, ","))  + " ?);", getItemArray(item));
        }
        catch(PCBuilderException e){
            throw new PCBuilderException("Cannot add item");
        }
        return item;
    }

    private Object[] getItemArray(T item){
        List<Object> rowList = new ArrayList<>();
        Map<String, Object> row = objectToRow(item);
        for(String key: row.keySet())
            rowList.add(row.get(key));
        if(!rowList.isEmpty())
            rowList.remove(0);
        return rowList.toArray();
    }


    private String getColumnNames() throws PCBuilderException {
        List<String> columnNamesList = new ArrayList<>();
        try {
            Statement statement = this.connection.createStatement();
            ResultSetMetaData rsMetaData = statement.executeQuery("SELECT * FROM " + tableName + " WHERE 1=0;").getMetaData();
            for(int i=2; i<=rsMetaData.getColumnCount(); i++)
                columnNamesList.add(rsMetaData.getColumnName(i));
        } catch (SQLException e) {
            throw new PCBuilderException("Cannot get column names");
        }
        return columnNamesList.toString().trim().substring(1, columnNamesList.toString().trim().length()-1);
    }


}
