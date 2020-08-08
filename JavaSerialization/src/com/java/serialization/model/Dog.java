package com.java.serialization.model;

import java.io.Serializable;

public class Dog implements Serializable {

    String name = "Scooby";

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}
