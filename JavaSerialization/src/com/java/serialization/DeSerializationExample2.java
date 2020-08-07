package com.java.serialization;

import com.java.serialization.model.Student;

import java.io.*;

public class DeSerializationExample2 {

    /* Here in, Student object is de-serialized */

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Student.ser"));
        Student s2 = (Student) objectInputStream.readObject();
        System.out.println(s2);
    }
}
