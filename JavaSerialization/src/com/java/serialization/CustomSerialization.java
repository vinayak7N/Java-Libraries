package com.java.serialization;

import com.java.serialization.model.Account;

import java.io.*;

public class CustomSerialization {

    /* Custom serialization by adding readObject() and writeObject() method in Account class.*/

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Account account = new Account();

        System.out.println("Serializing object...");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Account.ser"));
        outputStream.writeObject(account);
        System.out.println("Serialized object in Account.ser...");

        System.out.println("De-serializing object...");
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Account.ser"));
        Account account2 = (Account) inputStream.readObject();
        System.out.println(account2);
    }
}
