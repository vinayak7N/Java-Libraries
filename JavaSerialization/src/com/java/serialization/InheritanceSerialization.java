package com.java.serialization;

import com.java.serialization.model.Whale;

import java.io.*;

public class InheritanceSerialization {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Whale whale = new Whale();
        System.out.println("Serializing object...");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Whale.ser"));
        outputStream.writeObject(whale);
        System.out.println("Serialized object in Whale.ser...");

        System.out.println("De-serializing object...");
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Whale.ser"));
        Whale whale2 = (Whale) inputStream.readObject();
        System.out.println(whale2);
    }

}
