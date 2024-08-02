public abstract class User {
    //instance data members
    protected String password;
    protected String email;
    protected String username;
    protected Address address;

    //constructor
    public User(String password, String email, String username, Address address) {
        setPassword(password);
        setEmail(email);
        setUsername(username);
        setAddress(address);
    }

    //getters
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public Address getAddress() {
        return address;
    }

    //setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Email: " + email + "\nAddress: " + address;
    }
}