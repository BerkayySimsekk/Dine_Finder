import java.util.ArrayList;

public class RestaurantFilter implements FilterableRestaurant{
    private ListOfUsers listOfUsers;
    private ArrayList<RestaurantOwner> restaurantOwners;
    private ArrayList<RestaurantOwner> filteredRestaurantOwners;

    public RestaurantFilter() {
        this.listOfUsers = DineFinderApplication.listOfUsers;
        this.restaurantOwners = listOfUsers.getRestaurantOwnersFromListOfUsers();
        this.filteredRestaurantOwners = new ArrayList<RestaurantOwner>();
        cloneRestaurantsListToFilteredRestaurantsList();
    }

    public ArrayList<RestaurantOwner> getRestaurants() {
        return restaurantOwners;
    }

    public ArrayList<RestaurantOwner> getFilteredRestaurants() {
        return filteredRestaurantOwners;
    }

    public void cloneRestaurantsListToFilteredRestaurantsList() {
        for(int n = 0; n < restaurantOwners.size(); n++) {
            filteredRestaurantOwners.add(restaurantOwners.get(n));
        }
    }

    public void resetFilter() {
        filteredRestaurantOwners.clear();
        cloneRestaurantsListToFilteredRestaurantsList();
    }

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
                n--;
            }
        }    
    }

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
                n--;
            }
        }           
    }

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
                n--;
            }
        }      
    }

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
                n--;
            }
        }    
    }

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
                n--;
            }
        }
    }   
}