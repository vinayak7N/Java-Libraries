import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.collections4.map.ListOrderedMap;

import java.util.List;

public class OrderedMapCollection {

    /* OrderedMap interface helps to retain order in which elements are added in Map.
        LinkedMap and ListOrderedMap are two available implementations.
        This interface allows iteration from both direction.
     */

    public static void main(String[] args) {
        OrderedMapCollection collection = new OrderedMapCollection();
        collection.orderedMapUsage();
    }

    private void orderedMapUsage() {

        OrderedMap<String, Integer> linkedMap = new LinkedMap<>();
        linkedMap.put("One", 1);
        linkedMap.put("Two", 2);
        linkedMap.put("Three", 3);
        linkedMap.put("Seven", 7);
        linkedMap.put("Six", 6);

        //Insertion order is maintained...
        System.out.println("LinkedMap: " + linkedMap);
        System.out.println("First Key: " + linkedMap.firstKey() + "\t" + "Last Key: " + linkedMap.lastKey());
        System.out.println("Next key: " + linkedMap.nextKey("Three") + "\t" + "Previous Key: " + linkedMap.previousKey("Three"));

        //ListOrderMap...
        OrderedMap<String, Integer> listOrderedMap = new ListOrderedMap<>();
        listOrderedMap.putAll(linkedMap);
        System.out.println("ListOrderedMap: " + listOrderedMap);
        //System.out.println();
        OrderedMapIterator<String, Integer> orderedMapIterator = listOrderedMap.mapIterator();

        //ListOrderMap specific functionalities...
        ListOrderedMap<String, Integer> listOrderedMap1 = new ListOrderedMap<>();
        listOrderedMap1.putAll(listOrderedMap);

        //List of keys..
        List<String> keyList = listOrderedMap1.asList();
        System.out.println("Keys list: " + keyList);

        //fetch element at specific index...
        System.out.println("Element at second index: " + listOrderedMap1.get(2));

    }
}
