package ba.unsa.etf.rpr.beans;

import ba.unsa.etf.rpr.beans.decorator.pc.PCBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PCBeanTest {

    private PCBean pc;

    @BeforeEach
    public void setupEach() throws PCBuilderException{
        pc = new PCBean(List.of(
                new ProcessorBean("", "", "", "", "", 5),
                new RamBean("", "", "", "", "", 3),
                new GraphCardBean("", "", "", "", "", 2)));
    }
    @Test
    public void idableImplementationTest(){
        PCBean component = new PCBean();
        component.setId(66);
        assertEquals(66, component.getId());
    }
    @Test
    void getPrice() {
        assertEquals(10, pc.getPrice());
    }

    @Test
    void getComponentSuccess() throws PCBuilderException {
        assertEquals("ba.unsa.etf.rpr.beans.RamBean", pc.getComponent("ram").getClass().getName());
    }

    @Test
    void getComponentSuccess2() throws PCBuilderException {
        assertEquals("ba.unsa.etf.rpr.beans.GraphCardBean", pc.getComponent("graphcard").getClass().getName());
    }
    @Test
    void getComponentThrow(){
        assertThrows(PCBuilderException.class, () -> {
            pc.getComponent("motherboard");
        });
    }
}