package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.decorator.pc.PC;
import ba.unsa.etf.rpr.beans.decorator.pc.PCBean;
import ba.unsa.etf.rpr.dal.Dao;
import ba.unsa.etf.rpr.dal.DaoFactory;
import ba.unsa.etf.rpr.dal.pc.PCDao;
import ba.unsa.etf.rpr.dal.pc.PCDaoImpl;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.ArrayList;
import java.util.List;

public class PCManager extends AbstractPCManager{

//
//    @SafeVarargs
//    @Override
//    public final List<PC> makePCs(List<PCComponent>... componentCollections) throws PCBuilderException {
//        List<PC> pcList = new ArrayList<>();
//        if(componentCollections.length != 3)
//            throw new PCBuilderException("Not 3 lists provided");
//        for(int i=0; i<componentCollections[0].size(); i++)
//            for(int j=0; j<componentCollections[1].size(); j++)
//                for(int k=0; k<componentCollections[2].size(); k++)
//                    pcList.add(new PCBean(List.of(componentCollections[0].get(i), componentCollections[1].get(j), componentCollections[2].get(k))));
//        return pcList;
//    }
}
