package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.beans.PCBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.List;

public interface PCDao extends Dao<PCBean> {
    default void addPCs(PCBean ... pcs) throws PCBuilderException{
        addPCs(List.of(pcs));
    }
    void addPCs(List<PCBean> pcs) throws PCBuilderException;
    void removeAll() throws PCBuilderException;
}
