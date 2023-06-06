package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.ProcessorBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.sql.ResultSet;

public class ProcessorDaoImpl extends AbstractPCComponentDao{

    private static final ProcessorDaoImpl instance = new ProcessorDaoImpl("processors");

    private ProcessorDaoImpl(String tableName) {
        super(tableName);
    }
    public static ProcessorDaoImpl getInstance(){
        return instance;
    }

    @Override
    public PCComponent rowToObject(ResultSet resultSet) throws PCBuilderException {
        try{
            PCComponent processor = new ProcessorBean();
            return rowToGivenObject(processor, resultSet);
        } catch(Exception e){
            throw new PCBuilderException(e.getMessage(), e);
        }
    }
}
