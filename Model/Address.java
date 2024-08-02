public class Address {
    //instance data members
    private String streetName;
    private String cityName;
    private String districtName;

    //constructor
    public Address(String streetName, String districtName, String cityName) {
        setStreetName(streetName);
        setDistrictName(districtName);
        setCityName(cityName);
    }

    //getters
    public String getStreetName() {
        return streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    //setters
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return cityName + ", " + districtName + ", " + streetName;
    }
        
}
