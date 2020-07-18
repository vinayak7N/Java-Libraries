import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

public class BidiMapCollection {

    /* BidiMap extends the functionality of Map by providing Bidirectional support.
        Which means key can be lookup using values and values can be lookup using key easily.

        public interface BidiMap<K,V> extends IterableMap<K,V>
     */

    private void bidiMapUsage(){

        BidiMap<String, String> bidiMap = new TreeBidiMap<String, String>();
        bidiMap.put("One","1");
        bidiMap.put("Two","2");
        bidiMap.put("Three","3");
        System.out.println("Value of One is "+bidiMap.get("One"));
        System.out.println("Key associated with 3 is "+bidiMap.getKey("3"));
        System.out.println("BidiMap: "+bidiMap);

        //Overrides key associated with value 3
        bidiMap.put("ThreeDup","3");
        System.out.println("Key associated with 3 is "+bidiMap.getKey("3"));

        //Overrides value associated with One
        bidiMap.put("One","5");
        System.out.println("BidiMap: "+bidiMap);

        //Removes key-value pair associated with 2
        bidiMap.removeValue("2");
        System.out.println("BidiMap: "+bidiMap);

        //Inverse BidiMap
        System.out.println(bidiMap.inverseBidiMap());

    }

    public static void main(String[] args) {
        BidiMapCollection bidiMapCollection = new BidiMapCollection();
        bidiMapCollection.bidiMapUsage();
    }
}
