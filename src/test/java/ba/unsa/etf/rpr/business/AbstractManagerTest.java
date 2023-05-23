package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.ProcessorBean;
import ba.unsa.etf.rpr.dal.AbstractPCComponentDao;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Order(3)
@ExtendWith(MockitoExtension.class)
class AbstractManagerTest {

    private static final ProcessorManager processorManagerMock = spy(new ProcessorManager());

    private static AbstractPCComponentDao dao;
    private List<PCComponent> components;
    @BeforeEach
    public void setupEach() throws PCBuilderException {
        components = new ArrayList<PCComponent>(List.of(
                new ProcessorBean(1,"ThreadRipper", "Intel", "no url", "no url", "Some bad desc", 66),
                new ProcessorBean(2,"HexaCore", "Intel", "no url", "no url", "Some random desc...", 23)));
    }

    @BeforeAll
    public static void setupAll() throws PCBuilderException{
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


}