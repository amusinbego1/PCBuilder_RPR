package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.RamBean;
import ba.unsa.etf.rpr.dal.AbstractPCComponentDao;
import ba.unsa.etf.rpr.dal.RamDaoImpl;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import junitparams.JUnitParamsRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
class AbstractPCComponentManagerTest {


    private static final AbstractPCComponentManager ramManagerMock = spy(new RamManager());

    private static AbstractPCComponentDao dao;

    private List<PCComponent> components;

    @BeforeAll
    public static void setupAll() {
        dao = mock(RamDaoImpl.class);
        doReturn(dao).when(ramManagerMock).getDao();
    }

    @BeforeEach
    public void setupEach() {
        components = new ArrayList<>(List.of(
                new RamBean(1, "Trident Z5 NEO", "Intel", "no url", "no  url", "no desc", 100),
                new RamBean(2, "Corsair Vengeance Dominator", "Corsair", "random url", "random url", "some good desc", 150)
        ));
    }


    @Test
    void getByNameTest() throws PCBuilderException {
        doAnswer((invocation) -> {
            List<PCComponent> filteredComponents = new ArrayList<>();
            for (PCComponent component : components)
                if (component.getName().equals(invocation.getArguments()[0]))
                    filteredComponents.add(component);
            return filteredComponents;
        }).when(dao).getByName(anyString());
        assertEquals(1, ramManagerMock.getByName("Trident Z5 NEO").get(0).getId());
    }

    @Test
    void getByManufacturerTest() throws PCBuilderException {
        components.add(new RamBean(3, "Trident Z5 NEO", "Intel", "no url", "no  url", "no desc", 100));
        doAnswer((invocation) -> {
            List<PCComponent> filteredComponents = new ArrayList<>();
            for (PCComponent component : components)
                if (component.getManufacturer().equals(invocation.getArguments()[0]))
                    filteredComponents.add(component);
            return filteredComponents;
        }).when(dao).getByManufacturer("Intel");
        assertEquals(2, ramManagerMock.getByManufacturer("Intel").size());
    }


    private static Object[] parametersToHigherPriceTest(){
        return new Object[]{
                new Object[]{10., 2},
                new Object[]{110., 1},
                new Object[]{200., 0}
        };
    }

    @ParameterizedTest
    @MethodSource("parametersToHigherPriceTest")
    void getWithHigherPriceTest(double price, int expectedSize) throws PCBuilderException {
        doAnswer((invocation) -> {
            List<PCComponent> filteredComponents = new ArrayList<>();
            for (PCComponent component : components)
                if (component.getPrice() > (Double)invocation.getArguments()[0])
                    filteredComponents.add(component);
            return filteredComponents;
        }).when(dao).getWithHigherPrice(anyDouble());
        assertEquals(expectedSize, ramManagerMock.getWithHigherPrice(price).size());
    }

    private static Object[] parametersToLowerPriceTest(){
        return new Object[]{
                new Object[]{10., 0},
                new Object[]{110., 1},
                new Object[]{200., 2}
        };
    }
    @ParameterizedTest
    @MethodSource("parametersToLowerPriceTest")
    void getWithLowerPriceTest(double price, int expectedSize) throws PCBuilderException {
        doAnswer((invocation) -> {
            List<PCComponent> filteredComponents = new ArrayList<>();
            for (PCComponent component : components)
                if (component.getPrice() < (Double)invocation.getArguments()[0])
                    filteredComponents.add(component);
            return filteredComponents;
        }).when(dao).getWithLowerPrice(anyDouble());
        assertEquals(expectedSize, ramManagerMock.getWithLowerPrice(price).size());
    }

    private static Object[] parametersToBetweenPriceTest(){
        return new Object[]{
                new Object[]{0., 90., 0},
                new Object[]{50., 120., 1},
                new Object[]{110., 130., 0},
                new Object[]{130., 170., 1},
                new Object[]{50., 200., 2}
        };
    }

    @ParameterizedTest
    @MethodSource("parametersToBetweenPriceTest")
    void getWithPriceBetweenTest(double low, double high, int expectedSize) throws PCBuilderException{
        doAnswer(invocation -> {
            List<PCComponent> filteredComponents = new ArrayList<>();
            for(PCComponent component: components)
                if(component.getPrice() > (Double)invocation.getArguments()[0] && component.getPrice() < (Double)invocation.getArguments()[1])
                    filteredComponents.add(component);
            return filteredComponents;
        }).when(dao).getWithPriceBetween(anyDouble(), anyDouble());
        assertEquals(expectedSize, ramManagerMock.getWithPriceBetween(low, high).size());
    }
}