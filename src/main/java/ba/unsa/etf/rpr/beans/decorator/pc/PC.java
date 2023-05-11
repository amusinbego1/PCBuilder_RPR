package ba.unsa.etf.rpr.beans.decorator.pc;

import ba.unsa.etf.rpr.beans.*;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* TODO
    make better implementation of toString()
 */
public abstract class PC implements Idable {
    private int id;
    private List<PCComponent> components;


    public PC() {
        id = 0;
        components = new ArrayList<>();
    }

    public PC(int id, List<PCComponent> components) {
        this.id = id;
        this.components = components;
    }

    public PC(List<PCComponent> components) {
        this.id = 0;
        this.components = components;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public List<PCComponent> getComponents() {
        return components;
    }

    public void setComponents(List<PCComponent> components) {
        this.components = components;
    }

    public double getPrice() {
        double price = 0;
        for (PCComponent component : components)
            price += component.getPrice();
        return price;
    }

    public abstract  PCComponent getComponent(String componentType) throws PCBuilderException;
//    public PCComponent getComponent(String componentType) throws PCBuilderException {
//        for (PCComponent component : components)
//            if (componentType.toLowerCase().contains("ram") && component instanceof RamBean)
//                return component;
//            else if (componentType.toLowerCase().contains("processor") && component instanceof ProcessorBean)
//                return component;
//            else if (componentType.toLowerCase().contains("graph") && component instanceof GraphCardBean)
//                return component;
//        throw new PCBuilderException("There is no such component");
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PC pc = (PC) o;
        return id == pc.id && pc.getComponents().equals(components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, components);
    }


    @Override
    public String toString() {
        return "PCBean = " + components;
    }
}
