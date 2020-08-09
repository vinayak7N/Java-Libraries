package com.java.serialization;

import java.io.Serializable;

/*  With every class being Serialized, JVM will by default generate unique serialVersionUID to the class
    which is static and final. This value is being checked at receiver end during de-serialization and if
    there is a mismatch IllegalClassException is thrown.
    The default generation of serialVersionUID has many issues:
        1. the Sender and receiver must have same java version and same machine.
        2. If there is modification of .class at receiver end then object won't be serialized.
        3. JVM uses complex algorithm to generate this id, which will result in performance issue.
   Its better to generate own serialVersionUID and it solves above problems as well.
* */
public class SerialVersionUIDExample implements Serializable {

    private static final long serialVersionUID = 1L;

    int i = 24;
    int j = 45;
    int k = 34;

    //This variable is added after serialization and still no exception is thrown bcoz of declaring serialVersionUID
    int m = 78;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "SerialVersionUIDExample{" +
                "i=" + i +
                ", j=" + j +
                ", k=" + k +
                ", m=" + m +
                '}';
    }
}
