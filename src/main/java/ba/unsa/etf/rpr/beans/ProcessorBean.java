package ba.unsa.etf.rpr.beans;

import java.util.Objects;

public final class ProcessorBean extends PCComponent implements Idable{
    private int id;

    public ProcessorBean() {
        super();
        id = 0;
    }

    public ProcessorBean(int id, String name, String manufacturer, String desc, String buyUrl, String imgUrl, double price) {
        super(name, manufacturer, desc, buyUrl, imgUrl, price);
        this.id = id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
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
        return id == that.id && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName());
    }
}
