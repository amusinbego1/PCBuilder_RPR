package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.decorator.pc.PC;
import ba.unsa.etf.rpr.dal.Dao;
import ba.unsa.etf.rpr.dal.DaoFactory;
import ba.unsa.etf.rpr.dal.pc.PCDaoImpl;

public class PCBeanManager extends AbstractPCManager{
    @Override
    protected Dao<PC> getDao() {
        return DaoFactory.getPCDao(PCDaoImpl.getInstance());
    }

}
