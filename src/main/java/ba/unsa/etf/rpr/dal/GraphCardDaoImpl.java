package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.GraphCardBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.sql.ResultSet;

public class GraphCardDaoImpl extends AbstractPCComponentDao{

    private static final GraphCardDaoImpl instance =  new GraphCardDaoImpl("graphcards");;

    private GraphCardDaoImpl(String tableName) {
        super(tableName);
    }
    public static GraphCardDaoImpl getInstance(){
        return instance;
    }

    @Override
    public PCComponent rowToObject(ResultSet resultSet) throws PCBuilderException {
        try{
            PCComponent graphcard = new GraphCardBean();
            return rowToGivenObject(graphcard, resultSet);
        } catch(Exception e){
            throw new PCBuilderException(e.getMessage(), e);
        }
    }
}
