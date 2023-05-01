package ba.unsa.etf.rpr.beans;

public final class GraphicCardBean implements Idable{
    private int id;
    private String name, manufacturer, desc, buyUrl, imgUrl;
    double price;

    public GraphicCardBean() {
        id = 0;
        name = "";
        manufacturer = "";
        desc = "";
        buyUrl = "";
        imgUrl = "";
        price = 0;
    }

    public GraphicCardBean(int id, String name, String manufacturer, String desc, String buyUrl, String imgUrl, double price) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.desc = desc;
        this.buyUrl = buyUrl;
        this.imgUrl = imgUrl;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBuyUrl() {
        return buyUrl;
    }

    public void setBuyUrl(String buyUrl) {
        this.buyUrl = buyUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
