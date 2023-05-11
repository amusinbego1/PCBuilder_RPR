package ba.unsa.etf.rpr.beans.decorator.pc;

import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

public abstract class PCDecorator extends PC {
    protected PC pc;

    public PCDecorator(PC pc) {
        this.pc = pc;
    }

    @Override
    public PCComponent getComponent(String componentType) throws PCBuilderException {
        return this.pc.getComponent(componentType);
    }
}
