package ba.unsa.etf.rpr.dal.pc;

import ba.unsa.etf.rpr.beans.decorator.pc.PC;
import ba.unsa.etf.rpr.dal.AbstractDao;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.List;

public abstract class AbstractPCDao extends AbstractDao<PC> implements PCDao{
    public AbstractPCDao(String tableName) {
        super(tableName);
    }

    @Override
    public void addPCs(List<PC> pcs) throws PCBuilderException {
        for (PC pc : pcs)
            this.add(pc);
    }


    @Override
    public void removeAll() throws PCBuilderException {
        executeQuery("DELETE FROM " + this.getTableName(), null);
    }
}
