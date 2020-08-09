package com.java.serialization;

import com.java.serialization.model.Whale;
import com.java.serialization.model.Whale2;

import java.io.*;

public class InheritanceSerializationCase2 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Whale2 whale2 = new Whale2();
        whale2.setTypeOfAnimal("Mammals2","Whale2");
        System.out.println("Whale obj after updating:"+whale2);

        System.out.println("Serializing object...");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Whale2.ser"));
        outputStream.writeObject(whale2);
        System.out.println("Serialized object in Whale2.ser...");

        System.out.println("De-serializing object...");
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Whale2.ser"));
        Whale2 whale22 = (Whale2) inputStream.readObject();
        System.out.println(whale22);
    }
}
