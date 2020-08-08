package com.java.serialization;

import com.java.serialization.model.Person;

import java.io.*;

public class ObjectGraphSerialization {

     /*  Here, we are serializing Person object only but internally Address will also be serialized
         bcoz when an object is serialized, all objects associated with it in Object graph are serialized
         automatically.And if we remove "implements Serializable from Address class then exception is thrown
         as all objects associated with Person class in Object graph must be serializable"
      */

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person person = new Person();
        System.out.println("Serializing person object...");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Person.ser"));
        objectOutputStream.writeObject(person);

        System.out.println("De-Serializing person object...");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Person.ser"));
        Person person2 = (Person) objectInputStream.readObject();
        System.out.println(person);
    }
}
