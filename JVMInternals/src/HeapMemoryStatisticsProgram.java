public class HeapMemoryStatisticsProgram {

    static final long MB = 1024 * 1024;

    /* We can increase/decrease the heap size by passing arguments during run as;
     *   java -Xmx512m --- will set Heap size as 512 MB in maxMemory()
     *   java -Xms64m --- will set minimum hea size as 64 MB in totalMemory()
     *   java -Xmx512m -Xms64m will set both max and min heap size
     * */

    public static void main(String[] args) {

        /*To communicate with JVM we need Runtime object
         * It is a singleton class
         * */
        Runtime runtime = Runtime.getRuntime();

        // Max memory allocated to heap
        System.out.println("Max memory: " + runtime.maxMemory() / MB + " MB");

        //free memory in heap
        System.out.println("Free memory: " + runtime.freeMemory() / MB + " MB");

        //Total memory allocated to heap or initial memory in heap
        System.out.println("Total memory: " + runtime.totalMemory() / MB + " MB");

        System.out.println("Consumed memory: " + (runtime.totalMemory() - runtime.freeMemory()) / MB + " MB");

        //No. of processors
        System.out.println("Total processors: " + runtime.availableProcessors());

    }
}
