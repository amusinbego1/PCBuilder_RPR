package ba.unsa.etf.rpr.beans;

import ba.unsa.etf.rpr.beans.decorator.pc.PCBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Order(2)
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
    @DisplayName("Idable interface is implemented (success)")
    public void testIdableImplementation_ChecksCorrectIdableInterfaceImplementation_SetAndGetIdShouldBeEqual(){
        PCBean component = new PCBean();
        component.setId(66);
        assertEquals(66, component.getId());
    }
    @Test
    @DisplayName("Correct price calculation of PCBean object (success)")
    void testGetPrice_ChecksCorrectPriceCalculation_ShouldReturn10() {
        assertEquals(10, pc.getPrice());
    }

    @ParameterizedTest
    @CsvSource({"ram", "graphcard"})
    @DisplayName("Getting component from component's type (success)")
    void testGetComponent_ChecksForGettingRightComponent_ShouldReturnPCComponentObject(String type) throws PCBuilderException {
        assertEquals("ba.unsa.etf.rpr.beans." + (type.equals("ram") ? "RamBean" : "GraphCardBean")
                , pc.getComponent(type).getClass().getName());
    }

    @Test
    @DisplayName("Getting component from component's type (fail)")
    void testGetComponent_ChecksForGettingRightComponent_ShouldThrowBecauseOfUnexistingType(){
        assertThrows(PCBuilderException.class, () -> {
            pc.getComponent("motherboard");
        });
    }
}
