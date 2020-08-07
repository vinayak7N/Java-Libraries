package com.java.serialization.model;

import java.io.Serializable;

public class Student implements Serializable {

    //static won't participate in Serialization, it won't be serialized as it is not part of Object state
    static String email = "abc@gmail.com";

    //declaring a static variable as transient has no use bcoz it is already not part of Object state
    transient static String collegeName = "India College";

    //final variables are injected as value in compile time, so it is serialized
   private final int age = 23;

    //Even if it is transient, final values are serialized.
    // Bcoz final variable are replaced with their value at compile time which won't change at runtime,
    //so jvm insert its value in Serialized Object state.
    private transient final String courseField = "B.TECH";

    //These will be serialized as they are part ob Object state.
    private int id = 101;
    private String name = "Harry";

    //transient variable are not serialized. JVM will insert default value during serialization.
    transient String password = "password";

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", courseField='" + courseField + '\'' +
                '}';
    }
}
