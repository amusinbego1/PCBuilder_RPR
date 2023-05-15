package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.decorator.pc.PC;
import ba.unsa.etf.rpr.dal.Dao;
import ba.unsa.etf.rpr.dal.DaoFactory;
import ba.unsa.etf.rpr.dal.pc.PCDao;
import ba.unsa.etf.rpr.dal.pc.PCDaoImpl;

public class PCManager extends AbstractPCManager{

    private static PCDao pcDao = DaoFactory.getPCDao(PCDaoImpl.getInstance());

    public PCManager(PCDao dao) {
        pcDao = dao;
    }

    public PCManager(){

    }

    @Override
    protected Dao<PC> getDao() {
        return pcDao;
    }

}
