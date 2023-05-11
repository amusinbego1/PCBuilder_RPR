package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.Idable;
import ba.unsa.etf.rpr.dal.AbstractDao;
import ba.unsa.etf.rpr.dal.Dao;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.List;

public abstract class AbstractManager<T extends Idable> {
    protected abstract Dao<T> getDao();

    public T getById(int id) throws PCBuilderException { return getDao().getById(id);}

    public void deleteById(int id) throws PCBuilderException{
        getDao().deleteById(id);
    }

    public List<T> getAll() throws PCBuilderException{
        return getDao().getAll();
    }

    public T update(T item) throws PCBuilderException{
        return getDao().update(item);
    }

    public T add(T item) throws PCBuilderException{
        return getDao().add(item);
    }
}
