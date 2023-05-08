package ba.unsa.etf.rpr.beans;

import java.util.Objects;

public final class GraphCardBean extends PCComponent{

    public GraphCardBean() {
        super();
    }

    public GraphCardBean(int id, String name, String manufacturer, String desc, String buyUrl, String imgUrl, double price) {
        super(id, name, manufacturer, desc, buyUrl, imgUrl, price);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphCardBean that = (GraphCardBean) o;
        return getId() == that.getId() && getName().equals(that.getName());
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
