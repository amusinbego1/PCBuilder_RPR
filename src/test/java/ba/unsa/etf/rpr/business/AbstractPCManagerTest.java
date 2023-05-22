package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.GraphCardBean;
import ba.unsa.etf.rpr.beans.ProcessorBean;
import ba.unsa.etf.rpr.beans.RamBean;
import ba.unsa.etf.rpr.beans.decorator.pc.PC;
import ba.unsa.etf.rpr.beans.decorator.pc.PCBean;
import ba.unsa.etf.rpr.dal.pc.AbstractPCDao;
import ba.unsa.etf.rpr.dal.pc.PCDaoImpl;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AbstractPCManagerTest {

    private static final AbstractPCManager pcManager = spy(new PCManager());
    private static AbstractPCDao dao;

    private List<PC> pcs;

    @BeforeAll
    public static void setupAll(){
        dao = mock(PCDaoImpl.class);
        doReturn(dao).when(pcManager).getDao();
    }

    @BeforeEach
    public void setupEach(){
        pcs = new ArrayList<>(List.of(
                new PCBean(
                        new ArrayList<>(List.of(new ProcessorBean(), new RamBean(), new GraphCardBean()))
                ),
                new PCBean(
                        new ArrayList<>(List.of(new ProcessorBean(), new RamBean(), new GraphCardBean()))
                )
        ));
    }

   


}