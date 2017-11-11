import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomizedQueueTest {

/*
    public class RandomizedQueue<Item> implements Iterable<Item> {
        public RandomizedQueue()                 // construct an empty randomized queue
        public boolean isEmpty()                 // is the randomized queue empty?
        public int size()                        // return the number of items on the randomized queue
        public void enqueue(Item item)           // add the item
        public Item dequeue()                    // remove and return a random item
        public Item sample()                     // return a random item (but do not remove it)
        public Iterator<Item> iterator()         // return an independent iterator over items in random order
        public static void main(String[] args)   // unit testing (optional)
 */

//
//    Throw a java.util.NoSuchElementException if the client calls either sample() or dequeue() when the randomized queue is empty.
//
//    Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
//    Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.


    @Test
    public void RandomizedQueueStartsOffEmpty() {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        assertTrue(rq.isEmpty(), "newly constructed queue starts off empty");
    }

    @Test
    public void RandomizedQueueStartsWithSize_0() {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        assertEquals(0, rq.size(), "newly constructed queue starts off with size zero");
    }

    @Test
    public void ValidateParamToEnqueue() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        Executable enqueueCalledWithNull = () -> {
            rq.enqueue(null);
        };
        assertThrows(IllegalArgumentException.class, enqueueCalledWithNull, "enqueue expects non-null argument");
    }


    // add item, check isEmpty false
    // add two items, check size is 2
    // add single item, call dequeue, confirm same item
    // add single item, call dequeue, confirm isEmpty
    // add signle item, call dequeue, confirm size 0
}
