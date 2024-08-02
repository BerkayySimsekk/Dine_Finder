public class Item {
    //instance data members
    private String name;
    private String type;
    private double priceWithoutDiscount;
    private double priceWithDiscount;

    //constructor
    public Item(String name, String type, double priceWithoutDiscount, double priceWithDiscount) {
        setName(name);
        setType(type);
        setPriceWithoutDiscount(priceWithoutDiscount);
        setPriceWithDiscount(priceWithDiscount);
    }

    //getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPriceWithoutDiscount(double priceWithoutDiscount) {
        this.priceWithoutDiscount = priceWithoutDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    @Override
    public String toString() {
        return name + "  TL" + priceWithDiscount;
    }
}
