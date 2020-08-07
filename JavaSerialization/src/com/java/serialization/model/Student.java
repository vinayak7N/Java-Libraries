package com.java.serialization.model;

import java.io.Serializable;

public class Student implements Serializable {

    int id = 101;
    String name = "Harry";

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
