public class ClassLoadingProgram2 {

    public static void main(String[] args) {

        Student s1 = new Student();

        Class<Student> c1 = (Class<Student>) s1.getClass();

        System.out.println("c1: "+c1);

        Student s2 = new Student();

        Class<Student> c2 = (Class<Student>) s2.getClass();

        System.out.println("c2: "+c2);

        System.out.println("IS c1 and c2 both referring to same object " + (c1 == c2) +
                "\n" + "c1 hashcode: "+c1.hashCode() + "\t" + "c2 hashcode: "+c2.hashCode());


    }
}
