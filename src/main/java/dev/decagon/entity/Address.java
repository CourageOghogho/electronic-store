package dev.decagon.entity;

public class Address {
    private String houseNumber;
    private String streetName;
    private String localGovernmentArea;
    private String city;
    private String state;
    private String country;


    public Address(String houseNumber, String streetName, String localGovernmentArea,
                   String city, String state, String country) {
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.localGovernmentArea = localGovernmentArea;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Address() {
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getLocalGovernmentArea() {
        return localGovernmentArea;
    }

    public void setLocalGovernmentArea(String localGovernmentArea) {
        this.localGovernmentArea = localGovernmentArea;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
