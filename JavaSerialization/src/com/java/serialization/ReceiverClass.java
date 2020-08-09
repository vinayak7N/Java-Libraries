package com.java.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReceiverClass {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("SerialVersionUIDFile.ser"));
        SerialVersionUIDExample serialVersionUIDExample = (SerialVersionUIDExample) inputStream.readObject();
        System.out.println(serialVersionUIDExample);
        System.out.println("After serializing its id is: " + SerialVersionUIDExample.getSerialVersionUID());
    }

}
