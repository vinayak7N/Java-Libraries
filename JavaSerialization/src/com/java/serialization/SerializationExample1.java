package com.java.serialization;

import com.java.serialization.model.Student;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationExample1 {

    /* Here in, Student object is serialized */

    public static void main(String[] args) throws IOException {

        Student s1 = new Student();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Student.ser"));
        out.writeObject(s1);
    }
}
