package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCComponent;

public class DaoFactory {

    public static AbstractPCComponentDao getProcessorDao(){
        return ProcessorDaoImpl.getInstance();
    }

    public static AbstractPCComponentDao getGraphCardDao(){
        return GraphCardDaoImpl.getInstance();
    }

    public static AbstractPCComponentDao getRamDao(){
        return RamDaoImpl.getInstance();
    }
}
