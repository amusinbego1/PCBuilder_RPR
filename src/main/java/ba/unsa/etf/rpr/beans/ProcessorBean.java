package ba.unsa.etf.rpr.beans;

public class ProcessorBean implements Idable{
    private int id;
    private String name, manufacturer, desc, buy_url, img_url;
    double price;

    @Override
    public void setId(int id) {

    }

    @Override
    public int getId() {
        return 0;
    }
}
