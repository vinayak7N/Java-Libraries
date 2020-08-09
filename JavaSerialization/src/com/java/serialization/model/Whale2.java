package com.java.serialization.model;

//This class implements Serializable interface but parent class doesn't...

import java.io.Serializable;

public class Whale2 extends Animal2 implements Serializable {

    private String name = "Whale";

    public Whale2() {
        System.out.println("Whale2 constructor is called...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeOfAnimal(String typeOfAnimal, String name) {
        this.setTypeOfAnimal(typeOfAnimal);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Whale{" +
                "typeOfAnimal='" + this.getTypeOfAnimal() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}