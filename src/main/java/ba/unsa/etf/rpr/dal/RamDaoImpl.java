package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.RamBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.sql.ResultSet;

public class RamDaoImpl extends AbstractPCComponentDao{

    private RamDaoImpl(String tableName) {
        super(tableName);
    }

    private static final class InstanceHolder {
        private static final RamDaoImpl instance = new RamDaoImpl("rams");
    }

    public static RamDaoImpl getInstance(){
        return InstanceHolder.instance;
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
