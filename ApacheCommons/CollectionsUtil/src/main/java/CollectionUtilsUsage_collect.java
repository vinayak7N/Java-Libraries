import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;

import java.util.Arrays;
import java.util.List;

public class CollectionUtilsUsage_collect {

    /* Here, we have demonstrated usage of collate() which helps to list of one type of object into another type.
    *   public static <I,O> Collection<O> collect(Iterable<I> inputCollection,
        Transformer<? super I,? extends O> transformer)
    * */

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("1", "2", "3");
        List<Integer> intList = (List<Integer>) CollectionUtils.collect(
                stringList, new Transformer<String, Integer>() {
                    @Override
                    public Integer transform(String input) {
                        return Integer.parseInt(input);
                    }
                }
        );
        System.out.println(intList);
    }
}
