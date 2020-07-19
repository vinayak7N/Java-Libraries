import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;

public class MapIteratorCollection {

    /* MapIterator helps to provide simple iteration over Map. No need to get entrySet and then retrieve key-value pair.
     */

    public static void main(String[] args) {
        MapIteratorCollection collection = new MapIteratorCollection();
        collection.mapIteratorUsage();
    }

    private void mapIteratorUsage() {

        IterableMap<String, Integer> iterableMap = new HashedMap<>();
        iterableMap.put("One", 1);
        iterableMap.put("Two", 2);
        iterableMap.put("Three", 3);
        iterableMap.put("Four", 4);
        iterableMap.put("Five", 5);
        iterableMap.put("Six", 6);
        System.out.println("Map: " + iterableMap);
        MapIterator<String, Integer> mapIterator = iterableMap.mapIterator();
        System.out.println("Easy iteration...");
        while (mapIterator.hasNext()) {
            System.out.println("Key: " + mapIterator.next() + "\t" + "Value: " + mapIterator.getValue());
        }

    }
}
