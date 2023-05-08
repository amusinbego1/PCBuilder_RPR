package ba.unsa.etf.rpr.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* TODO
    make better implementation of toString()
 */
public class PCBean implements Idable{
    private int id;
    private List<PCComponent> components;
    private double price;


    public PCBean() {
        id = 0;
        components = new ArrayList<>();
        price = 0;
    }

    public PCBean(int id, List<PCComponent> components) {
        this.id = id;
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
        return price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PCBean pcBean = (PCBean) o;
        return id == pcBean.id && pcBean.getComponents().equals(components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, components);
    }


    @Override
    public String toString() {
        return "PCBean{" +
                "id=" + id +
                ", components=" + components;
    }
}
