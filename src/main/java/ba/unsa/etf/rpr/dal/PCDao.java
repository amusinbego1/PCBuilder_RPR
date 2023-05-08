package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.List;

public interface PCDao extends Dao<PCBean> {
    void addPC(PCBean pc) throws PCBuilderException;
    default void addPCs(PCBean ... pcs) throws PCBuilderException{
        addPCs(List.of(pcs));
    }
    void addPCs(List<PCBean> pcs) throws PCBuilderException;
    PCBean getPCById(int id) throws PCBuilderException;
    void removePCById(int id) throws PCBuilderException;
    List<PCBean> getAll() throws PCBuilderException;
    List<PCBean> removeAll() throws PCBuilderException;
}
