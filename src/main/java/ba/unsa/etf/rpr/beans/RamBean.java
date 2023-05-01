package ba.unsa.etf.rpr.beans;

public final class RamBean  implements Idable{
    private int id;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
