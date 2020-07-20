import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

public class CollectionUtilsUsage_addIgnoreNull {

    /* Here, we have demonstrated usage of addIgnoreNull() that ensures only non-null Objects to be added in a collection.
     *   public static<T> boolean addIgnoreNull(Collection<T> collection, T object)
     * */

    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("a");
        CollectionUtils.addIgnoreNull(list, "b");
        System.out.println(list);

        //null won't be added
        CollectionUtils.addIgnoreNull(list, null);
        System.out.println(list);
        list = null;

        //Throws exception as list is null
        CollectionUtils.addIgnoreNull(list, null);
    }
}
