package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.ProcessorBean;
import ba.unsa.etf.rpr.dal.AbstractPCComponentDao;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import junitparams.JUnitParamsRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Order(3)
@RunWith(JUnitParamsRunner.class)
class AbstractManagerTest {

    private static final ProcessorManager processorManagerMock = spy(new ProcessorManager());

    private static AbstractPCComponentDao dao;
    private List<PCComponent> components;
    @BeforeEach
    public void setupEach() {
        components = new ArrayList(List.of(
                new ProcessorBean(1,"ThreadRipper", "Intel", "no url", "no url", "Some bad desc", 66),
                new ProcessorBean(2,"HexaCore", "Intel", "no url", "no url", "Some random desc...", 23)));
    }

    @BeforeAll
    public static void setupAll(){
        dao = mock(AbstractPCComponentDao.class);
        doReturn(dao).when(processorManagerMock).getDao();
    }

    @Test
    public void getAllTest() throws PCBuilderException {
        doReturn(components).when(dao).getAll();
        int size = processorManagerMock.getAll().size();
        assertEquals(2, size);
    }

    @Test
    public void updateTest() throws PCBuilderException{
        PCComponent newProcessor = new ProcessorBean(2,"PentaCore", "Intel", "no url", "no url", "Some random desc...", 23);
        doAnswer(invocation -> {
            PCComponent theComponent = (PCComponent)invocation.getArguments()[0];
            for(int i=0; i<components.size(); i++)
                if(components.get(i).getId() == theComponent.getId())
                    components.set(i, theComponent);
            return theComponent;
        }).when(dao).update(any(PCComponent.class));
        assertEquals("PentaCore", processorManagerMock.update(newProcessor).getName());
    }

    @ParameterizedTest
    @CsvSource({"1", "2"})
    public void getByIdTestSuccess(int id) throws PCBuilderException{
        doAnswer(invocation -> {
            PCComponent theComponent = null;
            for(PCComponent component: components)
                if(component.getId() == (Integer)invocation.getArgument(0))
                    theComponent = component;
            return theComponent;
        }).when(dao).getById(anyInt());
        assertNotNull(processorManagerMock.getById(id));
    }

    @ParameterizedTest
    @CsvSource({"0", "5", "-1"})
    public void getByIdTestThrows(int id) throws PCBuilderException{
        doAnswer(invocation -> {
            PCComponent theComponent = null;
            for (PCComponent component : components)
               if (component.getId() == (Integer) invocation.getArgument(0))
                   theComponent = component;
            return theComponent;
        }).when(dao).getById(anyInt());
        assertNull(processorManagerMock.getById(id));
    }

    @ParameterizedTest
    @CsvSource({"1", "2"})
    public void deleteByIdSuccess(int id) throws PCBuilderException{
        doAnswer(invocation -> {
            components.removeIf(e -> e.getId() == (Integer) invocation.getArgument(0));
            return components;
        }).when(dao).deleteById(anyInt());
        processorManagerMock.deleteById(id);
        assertEquals(1, components.size());
    }
    @ParameterizedTest
    @CsvSource({"0", "5", "-1"})
    public void deleteByIdFail(int id) throws PCBuilderException{
        doAnswer(invocation -> {
            components.removeIf(e -> e.getId() == (Integer) invocation.getArgument(0));
            return components;
        }).when(dao).deleteById(anyInt());
        processorManagerMock.deleteById(id);
        assertEquals(2, components.size());
    }
}