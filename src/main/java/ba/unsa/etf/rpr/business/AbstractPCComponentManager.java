package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.dal.AbstractPCComponentDao;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.List;

public abstract class AbstractPCComponentManager extends AbstractManager<PCComponent>{

    protected abstract AbstractPCComponentDao getDao();

    public List<? extends PCComponent> getByName(String name) throws PCBuilderException{
        return getDao().getByName(name);
    }

    public List<? extends PCComponent> getByManufacturer(String manufacturer) throws PCBuilderException{
        return getDao().getByManufacturer(manufacturer);
    }

    public List<? extends PCComponent> getWithPriceBetween(double lowPrice, double highPrice) throws PCBuilderException{
        return getDao().getWithPriceBetween(lowPrice, highPrice);
    }

    public List<? extends PCComponent> getWithHigherPrice(double price) throws PCBuilderException{
        return getDao().getWithHigherPrice(price);
    }

    public List<? extends PCComponent> getWithLowerPrice(double price) throws PCBuilderException {
        return getDao().getWithLowerPrice(price);
    }




    }
