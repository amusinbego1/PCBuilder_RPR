package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.dal.pc.PCDao;
import ba.unsa.etf.rpr.dal.pc.PCDaoImpl;

public class DaoFactory {

    private static PCDao pcDao = PCDaoImpl.getInstance();
    private DaoFactory(){}

    public static AbstractPCComponentDao getProcessorDao(){
        return ProcessorDaoImpl.getInstance();
    }

    public static AbstractPCComponentDao getGraphCardDao(){
        return GraphCardDaoImpl.getInstance();
    }

    public static AbstractPCComponentDao getRamDao(){
        return RamDaoImpl.getInstance();
    }

    public static void setPcDao(PCDao dao){
        pcDao = dao;
    }

    public static PCDao getPCDao(){
        return pcDao;
    }
}
