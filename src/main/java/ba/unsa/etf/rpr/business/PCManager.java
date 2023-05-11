package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.PCBean;
import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.dal.DaoFactory;
import ba.unsa.etf.rpr.dal.PCDao;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class PCManager extends AbstractManager<PCBean> {
    @Override
    protected PCDao getDao() {
        return DaoFactory.getPCDao();
    }

    public void addPCs(List<PCBean>pcs) throws PCBuilderException {
        getDao().addPCs(pcs);
    }

    public void addPCs(PCBean ... pcs) throws PCBuilderException{
        getDao().addPCs(pcs);
    }

}
