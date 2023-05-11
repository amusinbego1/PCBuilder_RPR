package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.dal.pc.PCDao;
import ba.unsa.etf.rpr.dal.pc.PCDaoStrategy;

public class DaoFactory {

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

    public static PCDao getPCDao(PCDao pcDao){
        PCDaoStrategy.setPCDao(pcDao);
        return PCDaoStrategy.getPcDao();
    }
}
