import java.lang.reflect.Method;

public class ClassLoadingProgram {

    public static void main(String[] args) throws ClassNotFoundException {

        /*  Retrieved Class type of object representing student and stored in Heap Area.
        * */
        Class<Student> studentClass = (Class<Student>) Class.forName("Student");

        System.out.println("studentClass is an object of type " + studentClass);

        Method[] methods = studentClass.getDeclaredMethods();
        System.out.println("Student class methods:");
        for (Method m : methods) {
            System.out.println(m.getName());
        }
    }
}
