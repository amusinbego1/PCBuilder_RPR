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
    @Mock
    private static RamManager ramManagerMock;
    @Mock
    private static PCBeanManager pcBeanManagerMock;


    private List<? extends PCComponent> components;
    @BeforeEach
    public void setupEach() throws PCBuilderException {
        components = new ArrayList<>(List.of(
                new ProcessorBean(1,"ThreadRipper", "Intel", "no url", "no url", "Some bad desc", 66),
                new ProcessorBean(2,"HexaCore", "Intel", "no url", "no url", "Some random desc...", 23)));
    }
    


}