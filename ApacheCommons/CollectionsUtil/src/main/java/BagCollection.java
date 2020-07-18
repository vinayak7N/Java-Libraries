import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

import java.util.Set;

public class BagCollection {

    /* A Bag defines a collection which counts the number of times an Object appears in a collection
    *  public interface Bag<E> extends java.util.Collection<E>
    * */

    private void bagUsage(){
        Bag<String> bag = new HashBag<String>();
        bag.add("a");

        // b is added 3 times
        bag.add("b",3);
        bag.add("c",4);
        bag.add("d");
        System.out.println("c is present "+bag.getCount("c")+" times");
        System.out.println("Bag elements: "+bag);
        System.out.println("Total elements in bag are: "+bag.size());
        Set<String> uniqueBag = bag.uniqueSet();
        System.out.println("Unique set of elements in bag: "+uniqueBag);

        //remove 2 occurrence of c
        bag.remove("c",2);
        System.out.println("Bag elements: "+bag);

    }
    public static void main(String[] args) {

        BagCollection bagCollection = new BagCollection();
        bagCollection.bagUsage();

    }
}
