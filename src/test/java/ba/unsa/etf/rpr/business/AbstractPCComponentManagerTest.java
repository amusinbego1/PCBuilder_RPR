package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.RamBean;
import ba.unsa.etf.rpr.dal.AbstractPCComponentDao;
import ba.unsa.etf.rpr.dal.RamDaoImpl;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void getByManufacturerTest() {
    }

    @Test
    void getWithPriceBetweenTest() {
    }
}