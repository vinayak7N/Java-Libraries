package com.java.serialization.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Account implements Serializable {

    private final String name = "Mark";

    /* Since password is transient, this information will be lost during serialization, so to retrieve this
     * information to the trusted user we would need to encrypt this password manually during writeObject() callback
     * method and trusted user will retrieve the right value using readObject() call*/

    transient private String password = "Markpassword";

    //Encrypting password before serializing...
    //Method will be called automatically by JVM as it's a callback method
    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject(); //Default serialization, password is written as null only in file.
        String encryptPass = "$$" + password + "###";
        outputStream.writeObject(encryptPass);
    }

    //Decrypting password before reading from serialized file...
    //Method will be called automatically by JVM as it's a callback method
    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject(); //Default de-serialization, here password is null
        String encryptPass = (String) inputStream.readObject();
        password = encryptPass.replaceAll("#", "").replaceAll("\\$", "");
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
