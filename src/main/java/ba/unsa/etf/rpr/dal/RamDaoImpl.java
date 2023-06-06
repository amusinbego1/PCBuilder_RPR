package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.RamBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.sql.ResultSet;

public class RamDaoImpl extends AbstractPCComponentDao{

    private static final RamDaoImpl instance =  new RamDaoImpl("rams");

    private RamDaoImpl(String tableName) {
        super(tableName);
    }
    public static RamDaoImpl getInstance(){
        return instance;
    }

    @Override
    public PCComponent rowToObject(ResultSet resultSet) throws PCBuilderException {
        try{
            PCComponent ram = new RamBean();
            return rowToGivenObject(ram, resultSet);
        } catch(Exception e){
            throw new PCBuilderException(e.getMessage(), e);
        }
    }
}
