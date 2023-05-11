package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dal.AbstractPCComponentDao;
import ba.unsa.etf.rpr.dal.DaoFactory;

public final class RamManager extends AbstractPCComponentManager {
    @Override
    protected AbstractPCComponentDao getDao() {
        return DaoFactory.getRamDao();
    }
}
