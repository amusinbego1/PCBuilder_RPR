package ba.unsa.etf.rpr.beans;

public class ProcessorBean implements Idable{
    private int id;
    private String name, manufacturer, desc, buy_url, img_url;
    double price;

    public ProcessorBean(int id, String name, String manufacturer, String desc, String buy_url, String img_url, double price) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.desc = desc;
        this.buy_url = buy_url;
        this.img_url = img_url;
        this.price = price;
    }

    public ProcessorBean() {
        id = 0;
        price = 0;
        name = "";
        manufacturer = "";
        desc = "";
        buy_url = "";
        img_url = "";
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    

}
