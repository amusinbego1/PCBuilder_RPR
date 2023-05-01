package ba.unsa.etf.rpr.beans;

public final class PCBean implements Idable{
    private int id, processorId, ramId, graphicCardId;
    private double price;

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
    
}
