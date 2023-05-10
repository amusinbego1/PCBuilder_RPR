package ba.unsa.etf.rpr.beans;

import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PCBeanTest {

    @Test
    public void idableImplementationTest(){
        PCBean component = new PCBean();
        component.setId(66);
        assertEquals(66, component.getId());
    }
    @Test
    void getPrice() {
        PCBean pc = new PCBean(List.of(
                new ProcessorBean("", "", "", "", "", 5),
                new RamBean("", "", "", "", "", 3),
                new GraphCardBean("", "", "", "", "", 2)));
        assertEquals(10, pc.getPrice());
    }
    
}