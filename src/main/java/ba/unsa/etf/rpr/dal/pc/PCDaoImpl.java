package ba.unsa.etf.rpr.dal.pc;

import ba.unsa.etf.rpr.beans.decorator.pc.PC;
import ba.unsa.etf.rpr.beans.decorator.pc.PCBean;
import ba.unsa.etf.rpr.dal.DaoFactory;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PCDaoImpl extends AbstractPCDao{
    private static PCDao instance = null;
    private PCDaoImpl(String tableName) {
        super(tableName);
    }
    public static PCDao getInstance(){
        if(instance == null)
            instance = new PCDaoImpl("pcs");
        return instance;
    }

    @Override
    public PC rowToObject(ResultSet resultSet) throws PCBuilderException {
        try {
            return new PCBean(resultSet.getInt("pc_id"), List.of(
                    DaoFactory.getProcessorDao().getById(resultSet.getInt("processor_id")),
                    DaoFactory.getRamDao().getById(resultSet.getInt("ram_id")),
                    DaoFactory.getGraphCardDao().getById(resultSet.getInt("graphcard_id"))
            ));
        }
        catch(SQLException e){
            throw new PCBuilderException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> objectToRow(PC object) throws PCBuilderException {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("pc_id", object.getId());
        row.put("ram_id", object.getComponent("ram").getId());
        row.put("processor_id", object.getComponent("processor").getId());
        row.put("graphcard_id", object.getComponent("graphcard").getId());
        return row;
    }

}
