package com.java.serialization;

import java.io.*;

/*  Externalization is the process of taking control of Serialization of Object from JVM to programmer,
 *  It helps to save only those instance fields in a file which are required(partial object) as compared to total object
 *  being saved in memory during Serialization. So, before de-externalizing object JVM will create a default object
 *  using deafult object and then updates its value as per the fields saved in file.
 *  Transient keyword is not required in Externalization, bcoz programmer is saving data which he wants to save.
 *  If we declare any variable as transient it won't work differently i.e. transient keyword would be ignored.*/

public class ExternalizationDemo implements Externalizable {

    String s;
    int i, j;

    public ExternalizationDemo() {
        System.out.println("No-arg constructor called...");
    }

    public ExternalizationDemo(String s, int i, int j) {

        this.s = s;
        this.i = i;
        this.j = j;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ExternalizationDemo externalizationDemo = new ExternalizationDemo("Hello", 20, 56);
        System.out.println("Before writing object: " + externalizationDemo);

        System.out.println("Externalizing object in a file...");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Externalize.ser"));
        outputStream.writeObject(externalizationDemo);
        System.out.println("Externalizing object completed...");

        System.out.println("De-Externalizing object from the file...");
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Externalize.ser"));
        ExternalizationDemo externalizationDemo2 = (ExternalizationDemo) inputStream.readObject();
        System.out.println("After retrieving object: " + externalizationDemo2);
    }

    //Executed automatically during serialization
    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {

        objectOutput.writeObject(s);
        objectOutput.writeInt(i);
    }

    //Executed automatically during de-serialization
    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        s = (String) objectInput.readObject();
        i = objectInput.readInt();
    }

    @Override
    public String toString() {
        return "ExternalizationDemo{" +
                "s='" + s + '\'' +
                ", i=" + i +
                ", j=" + j +
                '}';
    }
}
