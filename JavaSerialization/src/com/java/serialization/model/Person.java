package com.java.serialization.model;

import java.io.Serializable;

public class Person implements Serializable {

    private final String name = "Bill Clinton";
    private final Address address = new Address("Delhi", "India");

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class Address implements Serializable {

    private final String city;
    private final String country;

    Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
