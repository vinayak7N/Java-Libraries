package com.java.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SenderClass {

    public static void main(String[] args) throws IOException {

        SerialVersionUIDExample serialVersionUIDExample = new SerialVersionUIDExample();
        System.out.println("Before serializing its id is: " + SerialVersionUIDExample.getSerialVersionUID());
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("SerialVersionUIDFile.ser"));
        outputStream.writeObject(serialVersionUIDExample);
    }
}
