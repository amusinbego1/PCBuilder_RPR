package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractPCDao extends AbstractDao<PCBean> implements PCDao{
    public AbstractPCDao(String tableName) {
        super(tableName);
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
