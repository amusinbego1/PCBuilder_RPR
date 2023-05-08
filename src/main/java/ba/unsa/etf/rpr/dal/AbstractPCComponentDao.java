package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
    public Map<String, Object> objectToRow(PCComponent object) {
        Map<String, Object> row = new HashMap<>();
        row.put(getIdProperty(), object.getId());
        row.put(getDescProperty(), object.getDesc());
        row.put(getNameProperty(), object.getName());
        row.put("price", object.getPrice());
        row.put("img_url", object.getImgUrl());
        row.put("shop_url", object.getBuyUrl());
        return row;
    }

    public List<PCComponent> getComponentsByName(String name) throws PCBuilderException {
        return executeQuery("SELECT * FROM " + this.getTableName() + " WHERE " + getNameProperty() + " = ?;", new Object[] {name});
    }

    public List<PCComponent> getByManufacturer(String manufacturer) throws PCBuilderException{
        return executeQuery("SELECT * FROM " + this.getTableName() + " WHERE manufacturer = ?;", new Object[]{manufacturer});
    }

    public PCComponent getComponentById(int id) throws PCBuilderException{
        List<PCComponent> components = executeQuery("SELECT * FROM " + this.getTableName() + " WHERE " + getIdProperty() + " = ?;", new Object[]{id});
        if(components.isEmpty())
            throw new PCBuilderException("Cannot find component with given id");
        return components.get(0);
    }

    public String getImgUrlById(int id) throws PCBuilderException {
        List<PCComponent> components = executeQuery("SELECT * FROM " + this.getTableName() + " WHERE " + getIdProperty() + " =? ;", new Object[]{id});
        if(components.isEmpty())
            throw new PCBuilderException("Cannot find component img url with given id");
        return components.get(0).getImgUrl();
    }

    public String shopUrlById(int id) throws PCBuilderException {
        List<PCComponent> components = executeQuery("SELECT * FROM " + this.getTableName() + " WHERE " + getIdProperty() + " = ?;", new Object[]{id});
        if(components.isEmpty())
            throw new PCBuilderException("Cannot find component shop url with given id");
        return components.get(0).getBuyUrl();
    }

    public List<PCComponent> getAllComponents() throws PCBuilderException{
        return executeQuery("SELECT * FORM " + this.getTableName() + ";", null);
    }

    public String getComponentDescById(int id) throws PCBuilderException{
        List<PCComponent> components = executeQuery("SELECT * FROM " + this.getTableName() + " WHERE " + getIdProperty() + " = ?;", new Object[]{id});
        if(components.isEmpty())
            throw new PCBuilderException("Cannot find component description with given id");
        return components.get(0).getDesc();
    }

    public List<PCComponent> getComponentsWithHigherPrice(double price) throws PCBuilderException{
        return executeQuery("SELECT * FROM " + this.getTableName() + " WHERE price >= ?;", new Object[]{price});
    }

    public List<PCComponent> getComponentsWithLowerPrice(double price) throws PCBuilderException{
        return executeQuery("SELECT * FROM " + this.getTableName() + " WHERE price <= ?;", new Object[]{price});
    }


}
