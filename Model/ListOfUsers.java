import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Random;

public class ListOfUsers {
    private ArrayList<User> users;
    public static MongoClient mongoClient;
    public static String uri = "mongodb+srv://aegoksal:886yKSzMTARwXE92@mealreel.jv59fhy.mongodb.net/";// Connection string

    public ListOfUsers() {
        users = new ArrayList<User>();

        Random rnd = new Random();

        // Create a new MongoClient
        mongoClient = MongoClients.create(uri);

        // Gets the database
        MongoDatabase  database = mongoClient.getDatabase("Users");

        // Gets the collection
        MongoCollection<Document> collection = database.getCollection("RestaurantOwners");
        MongoCollection<Document> collection2 = database.getCollection("Customers");

        Menu menu = new Menu();
        ArrayList<Address> addressCus = new ArrayList<>();
           
        MongoCollection<Document> menus = database.getCollection("Menus");
        MongoCollection<Document> adresses = database.getCollection("Adresses");

        for (int j = 0; j < adresses.countDocuments(); j++) 
        {
                
            addressCus.add(new Address(adresses.find().skip(j).first().getString("streetName"),
                            adresses.find().skip(j).first().getString("districtName"), 
                            adresses.find().skip(j).first().getString("cityName")));
        }

        for (int j = 0; j < menus.countDocuments(); j++) 
        {
            menu.addItemToMenu(new Item(menus.find().skip(j).first().getString("itemName"),
                                        menus.find().skip(j).first().getString("itemType"),
                                        menus.find().skip(j).first().getInteger("priceWithDiscount"),
                                        menus.find().skip(j).first().getInteger("priceWithoutDiscount")));
        }

        for (int i = 0; i < collection.countDocuments(); i++) 
        {
            int random = rnd.nextInt(1,6);
            Address address = new Address(collection.find().skip(i).first().getString("streetName"),
                                        collection.find().skip(i).first().getString("districtName"), 
                                        collection.find().skip(i).first().getString("cityName"));
            users.add(new RestaurantOwner(collection.find().skip(i).first().getString("restaurantName"),
                                        menu,
                                        collection.find().skip(i).first().getString("password"), 
                                        collection.find().skip(i).first().getString("email"), 
                                        collection.find().skip(i).first().getString("username"), 
                                        address, 
                                        collection.find().skip(i).first().getString("phoneNumber"), 
                                        collection.find().skip(i).first().getString("description")));  
            this.getRestaurantOwnersFromListOfUsers().get(i).setRating(random);                             
        }
        for (int i = 0; i < collection2.countDocuments(); i++) 
        {
            
            users.add(new Customer(collection2.find().skip(i).first().getString("custName"),
                                        collection2.find().skip(i).first().getString("custSurame"), 
                                        collection2.find().skip(i).first().getString("password"), 
                                        collection2.find().skip(i).first().getString("email"), 
                                        collection2.find().skip(i).first().getString("username"),
                                        addressCus.get(i)));   
        }
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
