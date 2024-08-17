import java.util.ArrayList;

public class RestaurantFilter implements FilterableRestaurant{
    //instance data members
    private ListOfUsers listOfUsers;
    private ArrayList<RestaurantOwner> restaurantOwners;
    private ArrayList<RestaurantOwner> filteredRestaurantOwners;

    //constructor that instantiates each instance data member and then fills the Array List for filtered restaurant owners with the objects found in the Array List for all of the
    //restaurant owners
    public RestaurantFilter() {
        this.listOfUsers = DineFinderApplication.listOfUsers;
        this.restaurantOwners = listOfUsers.getRestaurantOwnersFromListOfUsers();
        this.filteredRestaurantOwners = new ArrayList<RestaurantOwner>();
        cloneRestaurantsListToFilteredRestaurantsList();
    }

    //getters
    public ArrayList<RestaurantOwner> getRestaurants() {
        return restaurantOwners;
    }

    public ArrayList<RestaurantOwner> getFilteredRestaurants() {
        return filteredRestaurantOwners;
    }

    //clones the contents of the Array List for all of the restaurant owners to the Array List for the filtered restaurant owners
    public void cloneRestaurantsListToFilteredRestaurantsList() {
        for(int n = 0; n < restaurantOwners.size(); n++) {
            filteredRestaurantOwners.add(restaurantOwners.get(n));
        }
    }

    //resetting the filter means that after emptying the list for filtered restaurant owners, it is filled once again with the contents of the Array List for all of the restaurant 
    //owners
    public void resetFilter() {
        filteredRestaurantOwners.clear();
        cloneRestaurantsListToFilteredRestaurantsList();
    }

    //since this class implements Filterable Restaurant interface, it needs to inherit this method that removes the Restaurant Owner objects from the Array List for the filtered 
    //restaurant owners if the Restaurant Owner objects do not contain the given item type at their menu
    @Override
    public void includesGivenItemType(String type) {
        int amountOfItemsInCurrentMenu;
        ArrayList<Item> currentMenuItems;
        boolean isFound;

        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;
            currentMenuItems = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList();
            amountOfItemsInCurrentMenu = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList().size();

            for(int k = 0; k < amountOfItemsInCurrentMenu; k++) {
                if(currentMenuItems.get(k).getType().toLowerCase().contains(type.toLowerCase())) {
                    isFound = true;
                }
            }

            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //after removing the item when it is found, the indexes of every element found in the Array List shifts by one, the "n" value is decreased by one 
                //to prevent the incident of skipping an element
                n--;
            }
        }    
    }

    //since this class implements Filterable Restaurant interface, it needs to inherit this method that removes the Restaurant Owner objects from the Array List for the filtered 
    //restaurant owners if the Restaurant Owner objects do not contain the given item name at their menu
    @Override
    public void includesGivenItem(String name) {
        int amountOfItemsInCurrentMenu;
        ArrayList<Item> currentMenuItems;
        boolean isFound;

        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;
            currentMenuItems = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList();
            amountOfItemsInCurrentMenu = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList().size();

            for(int k = 0; k < amountOfItemsInCurrentMenu; k++) {
                if(currentMenuItems.get(k).getName().toLowerCase().contains(name.toLowerCase())) {
                    isFound = true;
                }
            }

            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //after removing the item when it is found, the indexes of every element found in the Array List shifts by one, the "n" value is decreased by one 
                //to prevent the incident of skipping an element
                n--;
            }
        }           
    }

    //since this class implements Filterable Restaurant interface, it needs to inherit this method that removes the Restaurant Owner objects from the Array List for the filtered 
    //restaurant owners if the Restaurant Owner objects do not contain at least one item with the given price range at their menu
    @Override
    public void includesItemsBetweenSetPriceRange(double min, double max) {
        int amountOfItemsInCurrentMenu;
        ArrayList<Item> currentMenuItems;
        boolean isFound;

        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;
            currentMenuItems = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList();
            amountOfItemsInCurrentMenu = filteredRestaurantOwners.get(n).getMenu().getMenuAsArrayList().size();

            for(int k = 0; k < amountOfItemsInCurrentMenu; k++) {
                if(currentMenuItems.get(k).getPrice() <= max && currentMenuItems.get(k).getPrice() >= min) {
                    isFound = true;
                }
            }

            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //after removing the item when it is found, the indexes of every element found in the Array List shifts by one, the "n" value is decreased by one 
                //to prevent the incident of skipping an element
                n--;
            }
        }      
    }

    //since this class implements Filterable Restaurant interface, it needs to inherit this method that sorts the Restaurant Owner objects found in the Array List for filtered
    //restaurant owners according to the alphabetical order of their restaurant names with Bubble Sort algorithm
    @Override
    public void sortByAlphabeticalOrderOfRestaurantName() {
        RestaurantOwner temporary;

        for(int n = 0; n < filteredRestaurantOwners.size() - 1; n++) {
            for(int j = 0; j < filteredRestaurantOwners.size() - n - 1; j++) {
                if(filteredRestaurantOwners.get(j + 1).getRestaurantName().compareTo(filteredRestaurantOwners.get(j).getRestaurantName()) < 0) {
                    temporary = filteredRestaurantOwners.get(j);
                    filteredRestaurantOwners.set(j, filteredRestaurantOwners.get(j + 1));
                    filteredRestaurantOwners.set(j + 1, temporary);
                }
            }
        } 
    }

    //since this class implements Filterable Restaurant interface, it needs to inherit this method that sorts the Restaurant Owner objects found in the Array List for filtered
    //restaurant owners according to the ratings of the Restaurant Owner objects with Bubble Sort algorithm
    @Override
    public void sortByRating() {
        RestaurantOwner temporary;

        for(int n = 0; n < filteredRestaurantOwners.size() - 1; n++) {
            for(int j = 0; j < filteredRestaurantOwners.size() - n - 1; j++) {
                if(filteredRestaurantOwners.get(j + 1).getRating() > filteredRestaurantOwners.get(j).getRating()) {
                    temporary = filteredRestaurantOwners.get(j);
                    filteredRestaurantOwners.set(j, filteredRestaurantOwners.get(j + 1));
                    filteredRestaurantOwners.set(j + 1, temporary);
                }
            }
        } 
    }

    //since this class implements Filterable Restaurant interface, it needs to inherit this method that sorts the Restaurant Owner objects found in the Array List for filtered
    //restaurant owners according to number of ratings given to the Restaurant Owner objects with Bubble Sort algorithm
    @Override
    public void sortByPopularity() {
        RestaurantOwner temporary;

        for(int n = 0; n < filteredRestaurantOwners.size() - 1; n++) {
            for(int j = 0; j < filteredRestaurantOwners.size() - n - 1; j++) {
                if(filteredRestaurantOwners.get(j + 1).getGivenRatings().size() > filteredRestaurantOwners.get(j).getGivenRatings().size()) {
                    temporary = filteredRestaurantOwners.get(j);
                    filteredRestaurantOwners.set(j, filteredRestaurantOwners.get(j + 1));
                    filteredRestaurantOwners.set(j + 1, temporary);
                }
            }
        }     
    }

    //since this class implements Filterable Restaurant interface, it needs to inherit this method that removes the Restaurant Owner objects from the Array List for the filtered 
    //restaurant owners if the city of the address of Restaurant Owner objects does not match with the taken String object as parameter
    @Override
    public void isFoundInGivenCity(String city) {
        boolean isFound;

        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;

            if(filteredRestaurantOwners.get(n).getAddress().getCityName().equalsIgnoreCase(city)) {
                isFound = true;
            }

            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                n--;
            }
        }
    }

    //since this class implements Filterable Restaurant interface, it needs to inherit this method that removes the Restaurant Owner objects from the Array List for the filtered 
    //restaurant owners if the district of the address of Restaurant Owner objects does not match with the taken String object as parameter
    @Override
    public void isFoundInGivenDistrict(String district) {
        boolean isFound;

        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;

            if(filteredRestaurantOwners.get(n).getAddress().getDistrictName().equalsIgnoreCase(district)) {
                isFound = true;
            }

            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //after removing the item when it is found, the indexes of every element found in the Array List shifts by one, the "n" value is decreased by one 
                //to prevent the incident of skipping an element
                n--;
            }
        }    
    }

    //since this class implements Filterable Restaurant interface, it needs to inherit this method that removes the Restaurant Owner objects from the Array List for the filtered 
    //restaurant owners if the street of the address of Restaurant Owner objects does not match with the taken String object as parameter
    @Override
    public void isFoundInGivenStreet(String street) {
        boolean isFound;

        for(int n = 0; n < filteredRestaurantOwners.size(); n++) {
            isFound = false;

            if(filteredRestaurantOwners.get(n).getAddress().getStreetName().equalsIgnoreCase(street)) {
                isFound = true;
            }

            if(!isFound) {
                filteredRestaurantOwners.remove(n);
                //after removing the item when it is found, the indexes of every element found in the Array List shifts by one, the "n" value is decreased by one 
                //to prevent the incident of skipping an element
                n--;
            }
        }
    }   
}