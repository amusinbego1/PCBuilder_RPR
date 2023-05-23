package ba.unsa.etf.rpr.beans;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Order(1)
class PCComponentTest {
    @Test
    public void idableImplementationTest(){
        PCComponent component = new ProcessorBean();
        component.setId(66);
        assertEquals(66, component.getId());
    }

}