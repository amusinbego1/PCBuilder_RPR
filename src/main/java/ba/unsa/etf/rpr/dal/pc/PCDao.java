package ba.unsa.etf.rpr.dal.pc;

import ba.unsa.etf.rpr.beans.decorator.pc.PC;
import ba.unsa.etf.rpr.dal.Dao;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.List;

public interface PCDao extends Dao<PC> {
    default void addPCs(PC... pcs) throws PCBuilderException{
        addPCs(List.of(pcs));
    }
    void addPCs(List<PC> pcs) throws PCBuilderException;
    void removeAll() throws PCBuilderException;
}
