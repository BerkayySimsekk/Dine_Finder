public interface FilterableRestaurant {
    public void includesGivenItemType(String type);
    public void sortByAlphabeticalOrderOfRestaurantName();
    public void includesGivenItem(String name);
    public void includesItemsBetweenSetPriceRange(double min, double max);
    public void sortByRating();
    public void sortByPopularity();
    public void sortByTotalDiscountOfItems();
    public void isFoundInGivenCity(String city);
    public void isFoundInGivenDistrict(String district);
    public void isFoundInGivenStreet(String street);
    public void includesGivenRestaurantName(String restaurantName);
}
