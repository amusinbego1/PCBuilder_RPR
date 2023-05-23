package ba.unsa.etf.rpr.beans;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Order(1)
class PCComponentTest {
    @Test
    @DisplayName("Idable interface is implemented (success)")
    public void testIdableImplementation_ChecksCorrectIdableInterfaceImplementation_SetAndGetIdShouldBeEqual(){
        PCComponent component = new ProcessorBean();
        component.setId(66);
        assertEquals(66, component.getId());
    }

}