package ba.unsa.etf.rpr.beans;

import java.util.Objects;

public final class RamBean extends PCComponent{

    public RamBean() {
        super();
    }

    public RamBean(int id, String name, String manufacturer, String desc, String buyUrl, String imgUrl, double price) {
        super(id, name, manufacturer, desc, buyUrl, imgUrl, price);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RamBean ramBean = (RamBean) o;
        return getId() == ramBean.getId() && getName().equals(ramBean.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
