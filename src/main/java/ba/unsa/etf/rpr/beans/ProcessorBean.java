package ba.unsa.etf.rpr.beans;

import java.util.Objects;

public final class ProcessorBean implements Idable{
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

    public String getBuy_url() {
        return buy_url;
    }

    public void setBuy_url(String buy_url) {
        this.buy_url = buy_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessorBean that = (ProcessorBean) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
