package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.List;

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
