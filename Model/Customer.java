public class Customer extends User {
    //instance data member
    Address address;

    //constructs a Customer object with the given parameters to the instance data members
    public Customer(String password, String email, String username, Address address) {
        super(password, email, username);
        setAddress(address);
    }

    //getter
    public Address getAddress() {
        return address;
    }

    //setter
    public void setAddress(Address address) {
        this.address = address;
    }
}
