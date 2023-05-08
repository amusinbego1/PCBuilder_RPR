package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PCDaoImpl extends AbstractDao<PCBean> implements PCDao {
    private static PCDaoImpl instance = null;
    private PCDaoImpl(String tableName) {
        super(tableName);
    }

    public static PCDaoImpl getInstance(){
        if(instance == null)
            instance = new PCDaoImpl("pcs");
        return instance;
    }

    @Override
    public PCBean rowToObject(ResultSet resultSet) throws PCBuilderException {
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
    public Map<String, Object> objectToRow(PCBean object) throws PCBuilderException {
        Map<String, Object> row = new HashMap<>();
        row.put("pc_id", object.getId());
        row.put("ram_id", object.getComponent("ram").getId());
        row.put("processor_id", object.getComponent("processor").getId());
        row.put("graphcard_id", object.getComponent("graphcard").getId());
        return row;
    }

    @Override
    public void addPCs(List<PCBean> pcs) throws PCBuilderException {
        for (PCBean pc : pcs)
            this.add(pc);
    }


    @Override
    public void removeAll() throws PCBuilderException {
        executeQuery("DELETE FROM " + this.getTableName(), null);
    }
}
