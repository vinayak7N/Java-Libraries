package com.java.serialization.model;

import java.io.Serializable;

public class Cat implements Serializable {

    String name = "Mikey";

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
