public class Customer extends User {
    Address address;

    public Customer(String password, String email, String username, Address address) {
        super(password, email, username);
        setAddress(address);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
