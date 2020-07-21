import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CollectionUtilsUsage_filter {

    /* Here, we have demonstrated usage of filter() which helps to filter object from list which do not specific a specific condition.
    *   public static <T> boolean filter(Iterable<T> collection,
        Predicate<? super T> predicate)
    * */

    public static void main(String[] args) {
        filterMethod();
        filterInverseMethod();
    }

    private static void filterMethod() {
        System.out.println("filter() method...");
        List<Integer> intList = new ArrayList<>();
        intList.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Original List: " + intList);

        CollectionUtils.filter(intList, new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer input) {
                return input.intValue() % 2 == 0;
            }
        });
        System.out.println("Filtered List: " + intList);
    }

    private static void filterInverseMethod() {
        System.out.println("filterInverse() method...");
        List<Integer> intList = new ArrayList<>();
        intList.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Original List: " + intList);

        CollectionUtils.filterInverse(intList, new Predicate<Integer>() {
            @Override
            public boolean evaluate(Integer input) {
                return input.intValue() % 2 == 0;
            }
        });
        System.out.println("Filtered List: " + intList);
    }
}
