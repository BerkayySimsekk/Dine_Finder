import java.util.ArrayList;

public class RestaurantFilter implements FilterableRestaurant{
    //instance data members
    private ListOfUsers listOfUsers;
    private ArrayList<RestaurantOwner> restaurantOwners;
    private ArrayList<RestaurantOwner> filteredRestaurantOwners = new ArrayList<>();

    //constructor
    public RestaurantFilter() {
        this.listOfUsers = MealReelApplication.listOfUsers;
        this.restaurantOwners = listOfUsers.getRestaurantOwnersFromListOfUsers();
        cloneRestaurantsListToFilteredRestaurantsList();
    }

    //getters
    public ArrayList<RestaurantOwner> getRestaurants() {
        return restaurantOwners;
    }

    public ArrayList<RestaurantOwner> getFilteredRestaurants() {
        return filteredRestaurantOwners;
    }

    //clone the restaurant owner list to be filtered later
    public void cloneRestaurantsListToFilteredRestaurantsList() {
        for(int n = 0; n < restaurantOwners.size(); n++) {
            filteredRestaurantOwners.add(restaurantOwners.get(n));
        }
    }

    //reset filter
    public void resetFilter() {
        filteredRestaurantOwners.clear();
        cloneRestaurantsListToFilteredRestaurantsList();
    }

    //filters the restaurants in a way that only the restaurants which include the given item type are shown
    @Override
    public void includesGivenItemType(String type) {
        int amountOfItemsInCurrentMenu;
        ArrayList<Item> currentMenuItems;
        boolean isFound;

        //the first loop iterates through the list of filtered restaurant owners
        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;
            currentMenuItems = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList();
            amountOfItemsInCurrentMenu = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList().size();

            //the second loop checks the menu items of the restaurant owner found by the first loop
            for(int k = 0; k < amountOfItemsInCurrentMenu; k++) {
                if(currentMenuItems.get(k).getType().equalsIgnoreCase(type)) {
                    isFound = true;
                }
            }

            //if the given type is not found in anywhere of the menu, this restaurant is removed from the filtered restaurants list
            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //if an element is removed we need to adjust the index of the cursor to avoid skipping an element
                n--;
            }
        }    
    }

    @Override
    public void includesGivenRestaurantName(String restaurantName) {
        String name;
        boolean isFound;

        //the first loop iterates through the list of filtered restaurant owners
        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;
            name = filteredRestaurantOwners.get(n).getRestaurantName();

            if(name.equalsIgnoreCase(restaurantName)) {
                isFound = true;
            }

            //if the given type is not found in anywhere of the menu, this restaurant is removed from the filtered restaurants list
            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //if an element is removed we need to adjust the index of the cursor to avoid skipping an element
                n--;
            }
        }  
        
        for (int i = 0; i < restaurantOwners.size() - 1; i++) {
            if(!filteredRestaurantOwners.get(0).getRestaurantName().equalsIgnoreCase(restaurantOwners.get(i).getRestaurantName()))
            {
                filteredRestaurantOwners.add(restaurantOwners.get(i));
            }
        }
    }

    //filters the restaurants in a way that only the restaurants which include the given item are shown
    @Override
    public void includesGivenItem(String name) {
        int amountOfItemsInCurrentMenu;
        ArrayList<Item> currentMenuItems;
        boolean isFound;

        //the first loop iterates through the list of filtered restaurant owners
        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;
            currentMenuItems = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList();
            amountOfItemsInCurrentMenu = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList().size();

            //the second loop checks the menu items of the restaurant owner found by the first loop
            for(int k = 0; k < amountOfItemsInCurrentMenu; k++) {
                if(currentMenuItems.get(k).getName().equalsIgnoreCase(name)) {
                    isFound = true;
                }
            }

            //if the given item is not found in anywhere of the menu, this restaurant is removed from the filtered restaurants list
            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //if an element is removed we need to adjust the index of the cursor to avoid skipping an element
                n--;
            }
        }           
    }

    //filters the restaurants in a way that only the restaurants which include the given price range for the items they have are shown
    @Override
    public void includesItemsBetweenSetPriceRange(double min, double max) {
        int amountOfItemsInCurrentMenu;
        ArrayList<Item> currentMenuItems;
        boolean isFound;

        //the first loop iterates through the list of filtered restaurant owners
        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;
            currentMenuItems = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList();
            amountOfItemsInCurrentMenu = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList().size();

            //the second loop checks the menu items of the restaurant owner found by the first loop
            for(int k = 0; k < amountOfItemsInCurrentMenu; k++) {
                if(currentMenuItems.get(k).getPriceWithDiscount() <= max && currentMenuItems.get(k).getPriceWithDiscount() >= min) {
                    isFound = true;
                }
            }

            //if the given price range for their items is not found in anywhere of the menu, this restaurant is removed from the filtered restaurants list
            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //if an element is removed we need to adjust the index of the cursor to avoid skipping an element
                n--;
            }
        }      
    }

    //sorts the restaurants by the alphabetical order according to their name
    @Override
    public void sortByAlphabeticalOrderOfRestaurantName() {
        RestaurantOwner temporary;

        for(int n = 0; n < filteredRestaurantOwners.size() - 1; n++) {
            for(int j = 0; j < filteredRestaurantOwners.size() - n - 1; j++) {
                if(filteredRestaurantOwners.get(j + 1).getRestaurantName().compareTo(filteredRestaurantOwners.get(j).getRestaurantName()) < 0) {
                    //swapping the found restaurants
                    temporary = filteredRestaurantOwners.get(j);
                    filteredRestaurantOwners.set(j, filteredRestaurantOwners.get(j + 1));
                    filteredRestaurantOwners.set(j + 1, temporary);
                }
            }
        } 
    }

    //sorts the restaurants according to their ratings, the rating is done in a way that the restaurants with higher ratings are at the beginning of the list
    @Override
    public void sortByRating() {
        RestaurantOwner temporary;

        for(int n = 0; n < filteredRestaurantOwners.size() - 1; n++) {
            for(int j = 0; j < filteredRestaurantOwners.size() - n - 1; j++) {
                if(filteredRestaurantOwners.get(j + 1).getRating() > filteredRestaurantOwners.get(j).getRating()) {
                    //swapping the found restaurants
                    temporary = filteredRestaurantOwners.get(j);
                    filteredRestaurantOwners.set(j, filteredRestaurantOwners.get(j + 1));
                    filteredRestaurantOwners.set(j + 1, temporary);
                }
            }
        } 
    }

    //sorts the restaurants according to the amount of ratings they have, he rating is done in a way that the restaurants with more amount of ratings are at the beginning of the list
    @Override
    public void sortByPopularity() {
        RestaurantOwner temporary;

        for(int n = 0; n < filteredRestaurantOwners.size() - 1; n++) {
            for(int j = 0; j < filteredRestaurantOwners.size() - n - 1; j++) {
                if(filteredRestaurantOwners.get(j + 1).getGivenRatings().size() > filteredRestaurantOwners.get(j).getGivenRatings().size()) {
                    //swapping the found restaurants
                    temporary = filteredRestaurantOwners.get(j);
                    filteredRestaurantOwners.set(j, filteredRestaurantOwners.get(j + 1));
                    filteredRestaurantOwners.set(j + 1, temporary);
                }
            }
        }     
    }

    //sorts the restaurants according the total discount they added for their items
    @Override
    public void sortByTotalDiscountOfItems() {
        RestaurantOwner temporary;

        for(int n = 0; n < filteredRestaurantOwners.size() - 1; n++) {
            for(int j = 0; j < filteredRestaurantOwners.size() - n - 1; j++) { 
                if(filteredRestaurantOwners.get(j + 1).calculateTotalDiscounts() > filteredRestaurantOwners.get(j).calculateTotalDiscounts()) {
                    //swapping the found restaurants
                    temporary = filteredRestaurantOwners.get(j);
                    filteredRestaurantOwners.set(j, filteredRestaurantOwners.get(j + 1));
                    filteredRestaurantOwners.set(j + 1, temporary);
                }
            }
        }      
    }

    //shows the restaurants that are found in the given city
    @Override
    public void isFoundInGivenCity(String city) {
        boolean isFound;

        //the restaurants currently found in the filtered restaurants list are checked whether they are found in the same street with the given city
        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;

            if(filteredRestaurantOwners.get(n).getAddress().getCityName().equalsIgnoreCase(city)) {
                isFound = true;
            }

            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //if an element is removed we need to adjust the index of the cursor to avoid skipping an element
                n--;
            }
        }
    }

    //shows the restaurants that are found in the given district
    @Override
    public void isFoundInGivenDistrict(String district) {
        boolean isFound;

        //the restaurants currently found in the filtered restaurants list are checked whether they are found in the same street with the given district
        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;

            if(filteredRestaurantOwners.get(n).getAddress().getDistrictName().equalsIgnoreCase(district)) {
                isFound = true;
            }

            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //if an element is removed we need to adjust the index of the cursor to avoid skipping an element
                n--;
            }
        }    
    }

    //shows the restaurants that are found in the given street
    @Override
    public void isFoundInGivenStreet(String street) {
        boolean isFound;

        //the restaurants currently found in the filtered restaurants list are checked whether they are found in the same street with the given street
        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;

            if(filteredRestaurantOwners.get(n).getAddress().getStreetName().equalsIgnoreCase(street)) {
                isFound = true;
            }

            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //if an element is removed we need to adjust the index of the cursor to avoid skipping an element
                n--;
            }
        }
    }   
}