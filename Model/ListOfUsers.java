import java.util.ArrayList;

public class ListOfUsers {
    private ArrayList<User> users;

    public ListOfUsers() {
        Item item1 = new Item("Margherita Pizza", "Main Course", 30.0);
        Item item2 = new Item("Cheeseburger", "Main Course", 35.0);
        Item item3 = new Item("Caesar Salad", "Appetizer", 20.0);
        Item item4 = new Item("Spaghetti Bolognese", "Main Course", 40.0);
        Item item5 = new Item("French Fries", "Side", 15.0);
        Item item6 = new Item("Grilled Chicken", "Main Course", 45.0);
        Item item7 = new Item("Mushroom Soup", "Appetizer", 25.0);
        Item item8 = new Item("Pancakes", "Dessert", 20.0);
        Item item9 = new Item("Chocolate Cake", "Dessert", 30.0);
        Item item10 = new Item("Chicken Caesar Wrap", "Main Course", 35.0);
        Item item11 = new Item("Garlic Bread", "Side", 10.0);
        Item item12 = new Item("Tiramisu", "Dessert", 35.0);
        Item item13 = new Item("Lemonade", "Beverage", 12.0);
        Item item14 = new Item("Iced Tea", "Beverage", 10.0);
        Item item15 = new Item("Espresso", "Beverage", 15.0);
        Item item16 = new Item("Cappuccino", "Beverage", 18.0);
        Item item17 = new Item("Fish and Chips", "Main Course", 50.0);
        Item item18 = new Item("Vegetable Stir Fry", "Main Course", 38.0);
        Item item19 = new Item("Greek Salad", "Appetizer", 22.0);
        Item item20 = new Item("Tomato Soup", "Appetizer", 20.0);
        Item item21 = new Item("Grilled Salmon", "Main Course", 60.0);
        Item item22 = new Item("Mashed Potatoes", "Side", 18.0);
        Item item23 = new Item("Roast Beef", "Main Course", 55.0);
        Item item24 = new Item("Apple Pie", "Dessert", 25.0);
        Item item25 = new Item("Ice Cream Sundae", "Dessert", 28.0);
        Item item26 = new Item("Coca-Cola", "Beverage", 8.0);
        Item item27 = new Item("Orange Juice", "Beverage", 10.0);
        Item item28 = new Item("Club Sandwich", "Main Course", 35.0);
        Item item29 = new Item("BBQ Ribs", "Main Course", 65.0);
        Item item30 = new Item("Cheese Platter", "Appetizer", 40.0);
        Item item31 = new Item("Margherita Pizza", "Main Course", 30.0);
        Item item32 = new Item("Pepperoni Pizza", "Main Course", 35.0);
        Item item33 = new Item("BBQ Chicken Pizza", "Main Course", 40.0);
        Item item34 = new Item("Veggie Pizza", "Main Course", 28.0);
        Item item35 = new Item("Hawaiian Pizza", "Main Course", 32.0);
        Item item36 = new Item("Cheese Pizza", "Main Course", 25.0);
        Item item37 = new Item("Buffalo Wings", "Appetizer", 15.0);
        Item item38 = new Item("Garlic Bread", "Appetizer", 8.0);
        Item item39 = new Item("Mozzarella Sticks", "Appetizer", 12.0);
        Item item40 = new Item("Caesar Salad", "Appetizer", 10.0);
        Item item41 = new Item("Grilled Chicken Sandwich", "Main Course", 20.0);
        Item item42 = new Item("BLT Sandwich", "Main Course", 18.0);
        Item item43 = new Item("Steak Sandwich", "Main Course", 25.0);
        Item item44 = new Item("Tuna Sandwich", "Main Course", 17.0);
        Item item45 = new Item("Club Sandwich", "Main Course", 22.0);
        Item item46 = new Item("French Fries", "Side", 6.0);
        Item item47 = new Item("Onion Rings", "Side", 7.0);
        Item item48 = new Item("Potato Wedges", "Side", 8.0);
        Item item49 = new Item("Mashed Potatoes", "Side", 9.0);
        Item item50 = new Item("Mac and Cheese", "Side", 10.0);
        Item item51 = new Item("Spaghetti Bolognese", "Main Course", 28.0);
        Item item52 = new Item("Lasagna", "Main Course", 30.0);
        Item item53 = new Item("Fettuccine Alfredo", "Main Course", 26.0);
        Item item54 = new Item("Penne Arrabbiata", "Main Course", 24.0);
        Item item55 = new Item("Ravioli", "Main Course", 29.0);
        Item item56 = new Item("Tiramisu", "Dessert", 12.0);
        Item item57 = new Item("Cheesecake", "Dessert", 14.0);
        Item item58 = new Item("Chocolate Cake", "Dessert", 13.0);
        Item item59 = new Item("Apple Pie", "Dessert", 11.0);
        Item item60 = new Item("Brownies", "Dessert", 10.0);

        Menu menu1 = new Menu();
        menu1.addItemToMenu(item1);
        menu1.addItemToMenu(item2);
        menu1.addItemToMenu(item3);
        menu1.addItemToMenu(item31);
        menu1.addItemToMenu(item32);
        menu1.addItemToMenu(item33);

        Menu menu2 = new Menu();
        menu2.addItemToMenu(item4);
        menu2.addItemToMenu(item5);
        menu2.addItemToMenu(item6);
        menu2.addItemToMenu(item34);
        menu2.addItemToMenu(item35);
        menu2.addItemToMenu(item36);

        Menu menu3 = new Menu();
        menu3.addItemToMenu(item7);
        menu3.addItemToMenu(item8);
        menu3.addItemToMenu(item9);
        menu3.addItemToMenu(item37);
        menu3.addItemToMenu(item38);
        menu3.addItemToMenu(item39);

        Menu menu4 = new Menu();
        menu4.addItemToMenu(item10);
        menu4.addItemToMenu(item11);
        menu4.addItemToMenu(item12);
        menu4.addItemToMenu(item40);
        menu4.addItemToMenu(item41);
        menu4.addItemToMenu(item42);

        Menu menu5 = new Menu();
        menu5.addItemToMenu(item13);
        menu5.addItemToMenu(item14);
        menu5.addItemToMenu(item15);
        menu5.addItemToMenu(item43);
        menu5.addItemToMenu(item44);
        menu5.addItemToMenu(item45);

        Menu menu6 = new Menu();
        menu6.addItemToMenu(item16);
        menu6.addItemToMenu(item17);
        menu6.addItemToMenu(item18);
        menu6.addItemToMenu(item46);
        menu6.addItemToMenu(item47);
        menu6.addItemToMenu(item48);

        Menu menu7 = new Menu();
        menu7.addItemToMenu(item19);
        menu7.addItemToMenu(item20);
        menu7.addItemToMenu(item21);
        menu7.addItemToMenu(item49);
        menu7.addItemToMenu(item50);
        menu7.addItemToMenu(item51);

        Menu menu8 = new Menu();
        menu8.addItemToMenu(item22);
        menu8.addItemToMenu(item23);
        menu8.addItemToMenu(item24);
        menu8.addItemToMenu(item52);
        menu8.addItemToMenu(item53);
        menu8.addItemToMenu(item54);

        Menu menu9 = new Menu();
        menu9.addItemToMenu(item25);
        menu9.addItemToMenu(item26);
        menu9.addItemToMenu(item27);
        menu9.addItemToMenu(item55);
        menu9.addItemToMenu(item56);
        menu9.addItemToMenu(item57);

        Menu menu10 = new Menu();
        menu10.addItemToMenu(item28);
        menu10.addItemToMenu(item29);
        menu10.addItemToMenu(item30);
        menu10.addItemToMenu(item58);
        menu10.addItemToMenu(item59);
        menu10.addItemToMenu(item60);


        Address address1 = new Address("123 Main St", "Cityville", "State");
        Address address2 = new Address("456 Elm St", "Townsville", "State");
        Address address3 = new Address("789 Oak St", "Villageville", "State");
        Address address4 = new Address("101 Pine St", "Hamletville", "State");
        Address address5 = new Address("202 Maple St", "Burgville", "State");
        Address address6 = new Address("303 Birch St", "Metropolis", "State");
        Address address7 = new Address("404 Cedar St", "Countryside", "State");
        Address address8 = new Address("505 Walnut St", "Baytown", "State");
        Address address9 = new Address("606 Cherry St", "Lakewood", "State");
        Address address10 = new Address("707 Spruce St", "Rivertown", "State");

        RestaurantOwner restaurant1 = new RestaurantOwner("The Food Place", "password1", "email1@example.com", "user1", address1, "A cozy place with delicious meals.");
        RestaurantOwner restaurant2 = new RestaurantOwner("Gourmet Bites", "password2", "email2@example.com", "user2", address2, "High-end dining experience with gourmet food.");
        RestaurantOwner restaurant3 = new RestaurantOwner("Family Diner", "password3", "email3@example.com", "user3", address3, "Family-friendly diner with a wide menu.");
        RestaurantOwner restaurant4 = new RestaurantOwner("Seafood Shack", "password4", "email4@example.com", "user4", address4, "Fresh seafood served daily.");
        RestaurantOwner restaurant5 = new RestaurantOwner("Veggie Delight", "password5", "email5@example.com", "user5", address5, "Healthy vegetarian and vegan options.");
        RestaurantOwner restaurant6 = new RestaurantOwner("Steakhouse Grill", "password6", "email6@example.com", "user6", address6, "Top-quality steaks cooked to perfection.");
        RestaurantOwner restaurant7 = new RestaurantOwner("Pasta Paradise", "password7", "email7@example.com", "user7", address7, "Authentic Italian pasta dishes.");
        RestaurantOwner restaurant8 = new RestaurantOwner("Burger Joint", "password8", "email8@example.com", "user8", address8, "Best burgers in town with a variety of toppings.");
        RestaurantOwner restaurant9 = new RestaurantOwner("Sushi World", "password9", "email9@example.com", "user9", address9, "Fresh sushi and Japanese cuisine.");
        RestaurantOwner restaurant10 = new RestaurantOwner("Pizza Haven", "password10", "email10@example.com", "user10", address10, "Delicious pizzas with a variety of toppings.");

        restaurant1.setMenu(menu1);
        restaurant2.setMenu(menu2);
        restaurant3.setMenu(menu3);
        restaurant4.setMenu(menu4);
        restaurant5.setMenu(menu5);
        restaurant6.setMenu(menu6);
        restaurant7.setMenu(menu7);
        restaurant8.setMenu(menu8);
        restaurant9.setMenu(menu9);
        restaurant10.setMenu(menu10);

        users = new ArrayList<User>();

        Customer customer = new Customer("12345", "berkay@gmail.com", "berkay", address8);

        users.add(restaurant1);
        users.add(restaurant2);
        users.add(restaurant3);
        users.add(restaurant4);
        users.add(restaurant5);
        users.add(restaurant6);
        users.add(restaurant7);
        users.add(restaurant8);
        users.add(restaurant9);
        users.add(restaurant10);
        users.add(customer);
    }

    public ArrayList<User> getUserList() {
        return users;
    }

    public void addUserToList(User user) {
        users.add(user);
    }

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
