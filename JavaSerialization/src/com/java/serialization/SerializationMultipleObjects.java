package com.java.serialization;

import com.java.serialization.model.Cat;
import com.java.serialization.model.Dog;
import com.java.serialization.model.Rat;

import java.io.*;

public class SerializationMultipleObjects {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Dog dog = new Dog();
        Cat cat = new Cat();
        Rat rat = new Rat();

        System.out.println("Serialization of multiple objects...");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Animals.ser"));
        objectOutputStream.writeObject(dog);
        objectOutputStream.writeObject(cat);
        objectOutputStream.writeObject(rat);

        System.out.println("Deserialization of objects...");

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Animals.ser"));

        /*System.out.println("Objects must be read in same way as they are inserted.Otherwise class cast exception is encountered");

        Dog dog2 = (Dog) objectInputStream.readObject();
        Cat cat2 = (Cat) objectInputStream.readObject();
        Rat rat2 = (Rat) objectInputStream.readObject();
        Dog dog3 = (Dog) objectInputStream.readObject();
        System.out.println(dog2+"\n"+cat2+"\n"+rat2);*/

        System.out.println("If we don't know order of objects in serialized file...");

        int count = 0;
        while (count < 3) {
            Object obj = objectInputStream.readObject();
            if (obj instanceof Dog) {
                Dog dog2 = (Dog) obj;
                System.out.println(dog2);
            } else if (obj instanceof Cat) {
                Cat cat2 = (Cat) obj;
                System.out.println(cat2);
            } else {
                Rat rat2 = (Rat) obj;
                System.out.println(rat2);
            }
            count++;
        }
    }
}


