public interface FilterableRestaurant {
    //Restaurant Filter class implements these methods which allows the filtering process of the restaurants to occur according to various of options indicated below
    public void includesGivenItemType(String type);
    public void sortByAlphabeticalOrderOfRestaurantName();
    public void includesGivenItem(String name);
    public void includesItemsBetweenSetPriceRange(double min, double max);
    public void sortByRating();
    public void sortByPopularity();
    public void isFoundInGivenCity(String city);
    public void isFoundInGivenDistrict(String district);
    public void isFoundInGivenStreet(String street);
}
