package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.decorator.pc.PC;
import ba.unsa.etf.rpr.dal.pc.PCDao;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.List;
import java.util.function.Predicate;


public abstract class AbstractPCManager extends AbstractManager<PC> {

    public void removeAll() throws PCBuilderException{
        try{
            PCDao dao= (PCDao) getDao();
            dao.removeAll();
        }
        catch(Exception e){
            throw new PCBuilderException(e.getMessage(), e);
        }
    }

}
