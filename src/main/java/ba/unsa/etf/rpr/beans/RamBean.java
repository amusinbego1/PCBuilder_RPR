package ba.unsa.etf.rpr.beans;

import java.util.Objects;

public final class RamBean extends PCComponent{

    public RamBean() {
        super();
    }

    public RamBean(int id, String name, String manufacturer, String buyUrl, String imgUrl, String desc, double price) {
        super(id, name, manufacturer, buyUrl, imgUrl, desc, price);
    }
    public RamBean(String name, String manufacturer, String buyUrl, String imgUrl, String desc, double price) {
        super(name, manufacturer, buyUrl, imgUrl, desc, price);
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
