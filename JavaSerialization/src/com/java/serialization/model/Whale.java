package com.java.serialization.model;

//This class doesn't implements Serializable interface but parent class does, so this
//class is automatically serializable...

public class Whale extends Animal{

    private String name = "Whale";

    @Override
    public String toString() {
        return "Whale{" +
                "typeOfAnimal='" + typeOfAnimal + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}