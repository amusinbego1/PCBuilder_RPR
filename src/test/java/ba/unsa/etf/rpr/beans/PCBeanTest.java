package ba.unsa.etf.rpr.beans;

import ba.unsa.etf.rpr.beans.decorator.pc.PCBean;
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

    @Test
    void getComponentSuccess() throws PCBuilderException {
        PCBean pc = new PCBean(List.of(
                new ProcessorBean("", "", "", "", "", 5),
                new RamBean("", "", "", "", "", 3),
                new GraphCardBean("", "", "", "", "", 2)));
        assertEquals("ba.unsa.etf.rpr.beans.RamBean", pc.getComponent("ram").getClass().getName());
    }

    @Test
    void getComponentSuccess2() throws PCBuilderException {
        PCBean pc = new PCBean(List.of(
                new ProcessorBean("", "", "", "", "", 5),
                new RamBean("", "", "", "", "", 3),
                new GraphCardBean("", "", "", "", "", 2)));
        assertEquals("ba.unsa.etf.rpr.beans.GraphCardBean", pc.getComponent("graphcard").getClass().getName());
    }
    @Test
    void getComponentThrow(){
        PCBean pc = new PCBean(List.of(
                new ProcessorBean("", "", "", "", "", 5),
                new RamBean("", "", "", "", "", 3),
                new GraphCardBean("", "", "", "", "", 2)));
        assertThrows(PCBuilderException.class, () -> {
            pc.getComponent("motherboard");
        });
    }
}