package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.decorator.pc.PC;
import ba.unsa.etf.rpr.dal.Dao;
import ba.unsa.etf.rpr.dal.DaoFactory;
import ba.unsa.etf.rpr.dal.pc.PCDao;
import ba.unsa.etf.rpr.dal.pc.PCDaoImpl;

public class PCBeanManager extends AbstractPCManager{

    private static PCDao pcDao = DaoFactory.getPCDao(PCDaoImpl.getInstance());

    public PCBeanManager(PCDao dao) {
        pcDao = dao;
    }

    public PCBeanManager(){

    }

    @Override
    protected Dao<PC> getDao() {
        return pcDao;
    }

}
