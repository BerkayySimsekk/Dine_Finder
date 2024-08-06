public class Item {
    //instance data members
    private String name;
    private String type;
    private double price;

    //constructor
    public Item(String name, String type, double price) {
        setName(name);
        setType(type);
        setPrice(price);
    }

    //getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + "  TL" + price;
    }
}
