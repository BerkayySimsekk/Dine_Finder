public class Item {
    private String name;
    private String type;
    private double price;

    public Item(String name, String type, double price) {
        setName(name);
        setType(type);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

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
        return "Name: " + name + "    Type: " + type + "    Price: "  + price + " TL";
    }
}
