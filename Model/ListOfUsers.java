import java.util.ArrayList;

public class ListOfUsers {
    private ArrayList<User> users;

    public ListOfUsers() {
        users = new ArrayList<User>();
    }

    //getter
    public ArrayList<User> getUserList() {
        return users;
    }

    //add user
    public void addUserToList(User user) {
        users.add(user);
    }

    //create an Array List that only includes the restaurant owners in the list of users
    public ArrayList<RestaurantOwner> getRestaurantOwnersFromListOfUsers() {
        RestaurantOwner restaurantOwner;

        ArrayList<RestaurantOwner> restaurantOwners = new ArrayList<RestaurantOwner>();

        for(int n = 0; n < users.size(); n++) {
            if(users.get(n) instanceof RestaurantOwner) {
                restaurantOwner = (RestaurantOwner)users.get(n);
                restaurantOwners.add(restaurantOwner);
            }
        }

        return restaurantOwners;
    }

    public ArrayList<Customer> getCustomersFromListOfUsers() {
        Customer customer;

        ArrayList<Customer> customers = new ArrayList<Customer>();

        for(int n = 0; n < users.size(); n++) {
            if(users.get(n) instanceof Customer) {
                customer = (Customer)users.get(n);
                customers.add(customer);
            }
        }

        return customers;
    }
}
