public abstract class User {
    //instance data members
    protected String password;
    protected String email;
    protected String username;

    //constructor
    public User(String password, String email, String username) {
        setPassword(password);
        setEmail(email);
        setUsername(username);
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
}