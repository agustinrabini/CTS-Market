package com.example.ctsmarket05.entities;

public class Location {

    public Integer id_location ;

    public Integer id_user;

    private String province;

    private String city;

    private String district;

    private String street;

    private Integer street_number;

    private String floor;

    private Integer postal_code;

    public static Integer idLocation;

    public Location(Integer id_user, String province, String city, String district, String street, Integer street_number, String floor, Integer postal_code) {
        this.id_user = id_user;
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
        this.street_number = street_number;
        this.floor = floor;
        this.postal_code = postal_code;
    }

    public Location(String province, String city, String district, String street, Integer street_number, String floor, Integer postal_code) {
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
        this.street_number = street_number;
        this.floor = floor;
        this.postal_code = postal_code;
    }

    public Integer getId_location() {
        return id_location;
    }

    public void setId_location(Integer id_location) {
        this.id_location = id_location;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreet_number() {
        return street_number;
    }

    public void setStreet_number(Integer street_number) {
        this.street_number = street_number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(Integer postal_code) {
        this.postal_code = postal_code;
    }
}
