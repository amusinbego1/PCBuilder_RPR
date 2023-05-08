package ba.unsa.etf.rpr.beans;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PCComponentTest {
    @Test
    void getIdTest() {
        PCComponent component = new ProcessorBean();
        assertEquals(0, component.getId());
    }
}