package ba.unsa.etf.rpr.beans.decorator.pc;

import ba.unsa.etf.rpr.beans.GraphCardBean;
import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.ProcessorBean;
import ba.unsa.etf.rpr.beans.RamBean;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.List;

public class PCBean extends PC{

    public PCBean(int id, List<PCComponent> components) {
        super(id, components);
    }

    public PCBean(List<PCComponent> components) {
        super(components);
    }

    public PCBean() {
        super();
    }

    @Override
    public PCComponent getComponent(String componentType) throws PCBuilderException {
        for (PCComponent component : getComponents())
            if (componentType.toLowerCase().contains("ram") && component instanceof RamBean)
                return component;
            else if (componentType.toLowerCase().contains("processor") && component instanceof ProcessorBean)
                return component;
            else if (componentType.toLowerCase().contains("graph") && component instanceof GraphCardBean)
                return component;
        throw new PCBuilderException("There is no such component");
    }
}
