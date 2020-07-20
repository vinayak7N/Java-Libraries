import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public class CollectionUtilsUsage_collate {

    /* Here, we have demonstrated usage of collate() which helps to merge two sorted lists.
    *   public static<O extends Comparable<? super O>>
    List<O> collate(Iterable<? extends O> a, Iterable<? extends O> b)
    * */

    public static void main(String[] args) {
        List<String> sortedList1 = Arrays.asList("A", "B", "C");
        List<String> sortedList2 = Arrays.asList("D", "E", "F");
        List<String> mergedList = CollectionUtils.collate(sortedList1, sortedList2);
        System.out.println(mergedList);
    }
}
