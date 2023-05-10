package ba.unsa.etf.rpr.beans;

import java.util.Objects;

public final class ProcessorBean extends PCComponent{


    public ProcessorBean() {
        super();
    }

    public ProcessorBean(int id, String name, String manufacturer, String buyUrl, String imgUrl, String desc, double price) {
        super(id, name, manufacturer, buyUrl, imgUrl, desc, price);
    }
    public ProcessorBean(String name, String manufacturer, String buyUrl, String imgUrl, String desc, double price) {
        super(name, manufacturer, buyUrl, imgUrl, desc, price);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessorBean that = (ProcessorBean) o;
        return getId() == that.getId() && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
