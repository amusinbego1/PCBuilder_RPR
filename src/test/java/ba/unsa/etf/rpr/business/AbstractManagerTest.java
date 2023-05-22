package ba.unsa.etf.rpr.business;
import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.ProcessorBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbstractManagerTest {

    @Mock
    private static ProcessorManager processorManagerMock;
    
    private List<PCComponent> components;
    @BeforeEach
    public void setupEach() throws PCBuilderException {
        components = new ArrayList<PCComponent>(List.of(
                new ProcessorBean(1,"ThreadRipper", "Intel", "no url", "no url", "Some bad desc", 66),
                new ProcessorBean(2,"HexaCore", "Intel", "no url", "no url", "Some random desc...", 23)));
    }

    @Test
    public void getAllTest() throws PCBuilderException {
        doReturn(components).when(processorManagerMock).getAll();
        int size = processorManagerMock.getAll().size();
        assertEquals(2, size);
    }

    @Test
    public void updateTest() throws PCBuilderException{
        PCComponent newProcessor = new ProcessorBean(2,"PentaCore", "Intel", "no url", "no url", "Some random desc...", 23);
        for(int i=0; i<components.size(); i++)
            if(components.get(i).getId() == newProcessor.getId())
                components.set(i, newProcessor);
        assertEquals("PentaCore", components.get(1).getName());
    }


}