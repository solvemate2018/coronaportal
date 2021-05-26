package com.coronaportal.modelViews;

public class adminViewVaccinesModel {
    private int id;
    private String brand;
    private int count;
    private int vaccine_center_id;
    private String city;
    private int zip_code;
    private String street;
    private int house_number;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getVaccine_center_id() {
        return vaccine_center_id;
    }

    public void setVaccine_center_id(int vaccine_center_id) {
        this.vaccine_center_id = vaccine_center_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse_number() {
        return house_number;
    }

    public void setHouse_number(int house_number) {
        this.house_number = house_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public adminViewVaccinesModel(int id, String brand, int count, int vaccine_center_id, String city, int zip_code, String street, int house_number, String name) {
        this.id = id;
        this.brand = brand;
        this.count = count;
        this.vaccine_center_id = vaccine_center_id;
        this.city = city;
        this.zip_code = zip_code;
        this.street = street;
        this.house_number = house_number;
        this.name = name;
    }
}
