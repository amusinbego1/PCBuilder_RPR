package ba.unsa.etf.rpr.beans;

import java.util.Objects;

/* TODO
    consider using other beans to fields instead of ids
    make better implementation of toString()
 */
public final class PCBean implements Idable{
    private int id, processorId, ramId, graphicCardId;
    private double price;


    public PCBean() {
        id = 0;
        processorId = 0;
        ramId = 0;
        graphicCardId = 0;
        price = 0;
    }

    public PCBean(int id, int processorId, int ramId, int graphicCardId, double price) {
        this.id = id;
        this.processorId = processorId;
        this.ramId = ramId;
        this.graphicCardId = graphicCardId;
        this.price = price;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getProcessorId() {
        return processorId;
    }

    public void setProcessorId(int processorId) {
        this.processorId = processorId;
    }

    public int getRamId() {
        return ramId;
    }

    public void setRamId(int ramId) {
        this.ramId = ramId;
    }

    public int getGraphicCardId() {
        return graphicCardId;
    }

    public void setGraphicCardId(int graphicCardId) {
        this.graphicCardId = graphicCardId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PCBean pcBean = (PCBean) o;
        return id == pcBean.id && processorId == pcBean.processorId && ramId == pcBean.ramId && graphicCardId == pcBean.graphicCardId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, processorId, ramId, graphicCardId);
    }


    @Override
    public String toString() {
        return "PCBean{" +
                "id=" + id +
                ", processorId=" + processorId +
                ", ramId=" + ramId +
                ", graphicCardId=" + graphicCardId +
                ", price=" + price +
                '}';
    }
}
