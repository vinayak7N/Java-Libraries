package com.java.serialization.model;

/*  Inheritance in serialization case 2:
    1. Here, parent is not serializable but still we can serialize child
       that is to serialize child class object parent may no be serializable.
    2. If there is any instance variable of non-serialized parent class available in child class
       then JVM will ignore the original value of parent's instance variable and save the default value
       in that instance to child object.
    3. At the time of deserialization, if there is any parent instance available which is non-serialized
       then JVM will execute first "Instance Control FLow".
    4. Instance Control Flow:
            a. Identification of instance member.
            b. Execution of instance variable assignment and instance block
            c. Execution of constructor.
    5. After executing above flow, JVM will assign instance variable value to child object.
    6. Its mandatory to have a non-arg constructor in parent class to execute Instance Control Flow by JVM
        otherwise user will get InvalidClassException.
* */
public class Animal2 {

    private String typeOfAnimal = "Mammals";

    public Animal2() {
        System.out.println("Animal2 default constructor called...");
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }
}
