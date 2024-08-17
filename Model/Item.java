public class Item {
    //instance data members
    private String name;
    private String type;
    private double price;

    //constructs an Item object with the given parameters to the instance data members
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

    //returns a String object that shows the information related to the instance data members of the Item object
    @Override
    public String toString() {
        return "Name: " + name + "    Type: " + type + "    Price: "  + price + " TL";
    }
}
