import java.util.ArrayList;

public class RestaurantOwner extends User {
    private static final double MAX_RATING = 5;

    //instance data members
    private String description;
    private String restaurantName;
    private Menu menu;
    private double rating;
    private ArrayList<Integer> givenRatings;
    private ArrayList<String> commentsOfRestaurant;
    private Address address;

    //constructor
    public RestaurantOwner(String restaurantName, String password, String email, String username, Address address, String description) {
        super(password, email, username);
        setAddress(address);
        setDescription(description);
        setRestaurantName(restaurantName);
        rating = 0;
        givenRatings = new ArrayList<Integer>();
        commentsOfRestaurant = new ArrayList<String>();
    }

    //getters
    public ArrayList<Integer> getGivenRatings() {
        return givenRatings;
    }

    public Address getAddress() {
        return address;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Menu getMenu() {
        return menu;
    }

    public double getRating() {
        return rating;
    }

    public ArrayList<String> getCommentsOfRestaurant() {
        return commentsOfRestaurant;
    }

    public String getDescription() {
        return description;
    }

    //setters
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setAddress(Address address) {
       this.address = address;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //add a comment to the restaurant given by the customer
    public void addCommentsToRestaurant (String comment) {
        commentsOfRestaurant.add(comment);
    }

    //calculate the rating of the restaurant by getting the average of the ratings given by the customers
    public void calculateRating() {
        double TotalOfRatings;
        double numbersOfRatingsGiven;

        TotalOfRatings = 0;
        numbersOfRatingsGiven = givenRatings.size();

        for(int n = 0; n < numbersOfRatingsGiven; n++) {
            TotalOfRatings += givenRatings.get(n);
        }

        this.rating = TotalOfRatings / numbersOfRatingsGiven; 
    }

    @Override
    public String toString() {
        return "Restaurant's Name: " + restaurantName + "\nAddress: " + address + "\nRating: " + rating + "/" + MAX_RATING + "\nMenu: " + menu;
    }
} 