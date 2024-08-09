import java.util.ArrayList;

public class RestaurantOwner extends User {
    @SuppressWarnings("unused")
    private static final double MAX_RATING = 5;

    private String description;
    private String restaurantName;
    private Menu menu;
    private double rating;
    private ArrayList<Integer> givenRatings;
    private ArrayList<User> ratingGivers;
    private Address address;

    public RestaurantOwner(String restaurantName, String password, String email, String username, Address address, String description) {
        super(password, email, username);
        setAddress(address);
        setDescription(description);
        setRestaurantName(restaurantName);
        rating = 0;
        menu = new Menu();
        givenRatings = new ArrayList<Integer>();
        ratingGivers = new ArrayList<User>();
    }

    public ArrayList<Integer> getGivenRatings() {
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
        return rating;
    }

    public String getDescription() {
        return description;
    }

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
} 