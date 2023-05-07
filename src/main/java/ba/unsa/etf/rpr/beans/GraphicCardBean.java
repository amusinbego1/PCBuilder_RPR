package ba.unsa.etf.rpr.beans;

import java.util.Objects;

public final class GraphicCardBean extends PCComponent{

    public GraphicCardBean() {
        super();
    }

    public GraphicCardBean(int id, String name, String manufacturer, String desc, String buyUrl, String imgUrl, double price) {
        super(id, name, manufacturer, desc, buyUrl, imgUrl, price);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphicCardBean that = (GraphicCardBean) o;
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
