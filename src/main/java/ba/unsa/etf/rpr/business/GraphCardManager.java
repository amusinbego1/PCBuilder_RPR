package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dal.AbstractPCComponentDao;
import ba.unsa.etf.rpr.dal.DaoFactory;

public final class GraphCardManager extends AbstractPCComponentManager{
    @Override
    protected AbstractPCComponentDao getDao() {
        return DaoFactory.getGraphCardDao();
    }
}
