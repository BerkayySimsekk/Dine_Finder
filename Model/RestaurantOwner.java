import java.util.ArrayList;

public class RestaurantOwner extends User {
    //a primitive data type to determine the maximum rating of the Restaurant Owner objects that can be reached from other classes
    public static final double MAX_RATING = 5;

    //instance data members
    private String description;
    private String restaurantName;
    private Menu menu;
    private ArrayList<Double> givenRatings;
    private ArrayList<User> ratingGivers;
    private Address address;

    //constructs a Restaurant Owner object with the given parameters to the instance data members
    public RestaurantOwner(String restaurantName, String password, String email, String username, Address address, String description) {
        super(password, email, username);
        setAddress(address);
        setDescription(description);
        setRestaurantName(restaurantName);
        menu = new Menu();
        givenRatings = new ArrayList<Double>();
        ratingGivers = new ArrayList<User>();
    }

    //getters
    public ArrayList<Double> getGivenRatings() {
        return givenRatings;
    }

    public ArrayList<User> getRatingGivers() {
        return ratingGivers;
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
        return calculateRating();
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

    public void setDescription(String description) {
        this.description = description;
    }

    //a method to calculate the rating of the restaurants with the help of the Array List that stores the given ratings
    public double calculateRating() {
        double TotalOfRatings;
        double numbersOfRatingsGiven;

        TotalOfRatings = 0;
        numbersOfRatingsGiven = givenRatings.size();

        if(numbersOfRatingsGiven == 0) {
            return 0;
        }

        for(int n = 0; n < numbersOfRatingsGiven; n++) {
            TotalOfRatings += givenRatings.get(n);
        }

        return TotalOfRatings / numbersOfRatingsGiven; 
    }
} 