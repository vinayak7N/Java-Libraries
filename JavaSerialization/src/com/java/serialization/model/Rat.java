package com.java.serialization.model;

import java.io.Serializable;

public class Rat implements Serializable {

    String name = "Rambo";

    @Override
    public String toString() {
        return "Rambo{" +
                "name='" + name + '\'' +
                '}';
    }
}
