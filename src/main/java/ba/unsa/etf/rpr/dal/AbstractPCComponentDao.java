package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPCComponentDao extends AbstractDao<PCComponent> {
    public AbstractPCComponentDao(String tableName) {
        super(tableName);
    }

    private String getProperty(String suffix){
        StringBuilder property = new StringBuilder(this.getTableName());
        property.deleteCharAt(property.length() - 1).append(suffix);
        return property.toString();
    }
    private String getNameProperty(){
        return getProperty("_name");
    }

    private String getIdProperty(){
        return getProperty("_id");
    }

    private String getDescProperty(){
        return getProperty("_desc");
    }

    protected PCComponent rowToGivenObject(PCComponent component, ResultSet resultSet) throws PCBuilderException{
        try{
            component.setId(resultSet.getInt(getIdProperty()));
            component.setDesc(resultSet.getString(getDescProperty()));
            component.setPrice(resultSet.getDouble("price"));
            component.setName(resultSet.getString(getNameProperty()));
            component.setManufacturer(resultSet.getString("manufacturer"));
            component.setImgUrl(resultSet.getString("img_url"));
            component.setBuyUrl(resultSet.getString("shop_url"));
            return component;
        }
        catch(SQLException e){
            throw new PCBuilderException(e.getMessage(), e);
        }
    }


    @Override
    public Map<String, Object> objectToRow(PCComponent object) throws PCBuilderException {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put(getIdProperty(), object.getId());
        row.put(getNameProperty(), object.getName());
        row.put("manufacturer", object.getManufacturer());
        row.put("shop_url", object.getBuyUrl());
        row.put("img_url", object.getImgUrl());
        row.put(getDescProperty(), object.getDesc());
        row.put("price", object.getPrice());
        return row;
    }

    public List<? extends PCComponent> getByName(String name) throws PCBuilderException {
        return executeQuery("SELECT * FROM " + this.getTableName() + " WHERE LOWER(" + getNameProperty() + ") LIKE CONCAT( '%', LOWER(?), '%');", new Object[] {name});
    }

    public List<? extends PCComponent> getByManufacturer(String manufacturer) throws PCBuilderException{
        return executeQuery("SELECT * FROM " + this.getTableName() + " WHERE LOWER(manufacturer) LIKE CONCAT( '%', LOWER(?), '%');", new Object[]{manufacturer});
    }
    
    public List<? extends PCComponent> getWithPriceBetween(double lowPrice, double highPrice) throws PCBuilderException{
        return executeQuery("SELECT * FROM " + this.getTableName() + " WHERE price >= ? AND price <= ?;", new Object[]{lowPrice, highPrice});
    }
    public List<? extends PCComponent> getWithHigherPrice(double price) throws PCBuilderException{
        return executeQuery("SELECT * FROM " + this.getTableName() + " WHERE price >= ?;", new Object[]{price});
    }

    public List<? extends PCComponent> getWithLowerPrice(double price) throws PCBuilderException{
        return executeQuery("SELECT * FROM " + this.getTableName() + " WHERE price <= ?;", new Object[]{price});
    }


}
