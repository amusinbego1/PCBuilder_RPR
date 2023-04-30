package ba.unsa.etf.rpr.dal;

import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.List;

public interface Dao<T>{
    T getById(int id) throws PCBuilderException;
    T add(T item) throws PCBuilderException;
    T update(T item) throws PCBuilderException;
    void deleteById(int id) throws PCBuilderException;
    List<T> getAll() throws PCBuilderException;
}
