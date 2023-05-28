package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.GraphCardBean;
import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.RamBean;
import ba.unsa.etf.rpr.dal.AbstractPCComponentDao;
import ba.unsa.etf.rpr.dal.RamDaoImpl;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import junitparams.JUnitParamsRunner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Order(4)
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
                new RamBean(2, "Corsair Vengeance Dominator", "Corsair", "random url", "random url", "some good desc", 150),
                new GraphCardBean(3, "AMD Radeon Graphics", "AMD", "no url", "no url", "some decent desc", 400)
        ));
    }


    @ParameterizedTest
    @CsvSource({"Trident Z5 NEO", "Corsair Vengeance Dominator"})
    @DisplayName("Get PCComponent by its name (success)")
    void testGetByName_GetPCComponentByItsName_ShouldReturnCorrectSizeOfReturnedComponents(String name) throws PCBuilderException {
        components.add(new RamBean(3, "Trident Z5 NEO", "Intel", "no url", "no  url", "no desc", 100));
        doAnswer((invocation) -> {
            List<PCComponent> filteredComponents = new ArrayList<>();
            for (PCComponent component : components)
                if (component.getName().equals(invocation.getArguments()[0]))
                    filteredComponents.add(component);
            return filteredComponents;
        }).when(dao).getByName(anyString());
        assertEquals(name.equals("Trident Z5 NEO")? 2: 1, ramManagerMock.getByName(name).size());
    }

    @Test
    @DisplayName("Get PCComponent objects by manufacturer (success)")
    void testGetByManufacturer_GetPCComponentsByManufacturer_ShouldReturnCorrectSizeOfReturnComponents() throws PCBuilderException {
        components.add(new RamBean(3, "Trident Z5 NEO", "Intel", "no url", "no  url", "no desc", 100));
        doAnswer((invocation) -> {
            List<PCComponent> filteredComponents = new ArrayList<>();
            for (PCComponent component : components)
                if (component.getManufacturer().equals(invocation.getArguments()[0]))
                    filteredComponents.add(component);
            return filteredComponents;
        }).when(dao).getByManufacturer(anyString());
        assertEquals(2, ramManagerMock.getByManufacturer("Intel").size());
    }

    @ParameterizedTest
    @CsvSource({"10., 3",
                "110., 2",
                "200., 1",
                "450., 0"})
    @DisplayName("Get PCComponents that have bigger price)")
    void testGetWithHigherPrice_GetPCComponentsThatAreMoreExpensive_ShouldReturnCorrectSizeOfReturnedComponents(double price, int expectedSize) throws PCBuilderException {
        doAnswer((invocation) -> {
            List<PCComponent> filteredComponents = new ArrayList<>();
            for (PCComponent component : components)
                if (component.getPrice() > (Double)invocation.getArguments()[0])
                    filteredComponents.add(component);
            return filteredComponents;
        }).when(dao).getWithHigherPrice(anyDouble());
        assertEquals(expectedSize, ramManagerMock.getWithHigherPrice(price).size());
    }

    @ParameterizedTest
    @CsvSource({"10., 0",
                "110., 1",
                "200., 2"})
    @DisplayName("Get PCComponents that have smaller price")
    void testGetWithLowerPrice_GetPCComponentsThatAreCheaper_ShouldReturnCorrectSizeOfReturnedComponents(double price, int expectedSize) throws PCBuilderException {
        doAnswer((invocation) -> {
            List<PCComponent> filteredComponents = new ArrayList<>();
            for (PCComponent component : components)
                if (component.getPrice() < (Double)invocation.getArguments()[0])
                    filteredComponents.add(component);
            return filteredComponents;
        }).when(dao).getWithLowerPrice(anyDouble());
        assertEquals(expectedSize, ramManagerMock.getWithLowerPrice(price).size());
    }

    @ParameterizedTest
    @CsvSource({"0., 90., 0",
                "50., 120., 1",
                "110., 130., 0",
                "130., 170., 1",
                "50., 200., 2"})
    @DisplayName("Get PCComponents that have price between two given")
    void testGetWithPriceBetween_GetPCComponentsThatHavePriceBetweenTwoGiven_ShouldReturnCorrectSizeOfReturnedComponents(double low, double high, int expectedSize) throws PCBuilderException{
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