package simulador_cache;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LruCacheNormal<KEY, VALUE> implements LruCache<KEY,VALUE> {

    Map<KEY, VALUE> map = new HashMap<KEY, VALUE> ();
    Deque<KEY> queue = new LinkedList<KEY> ();
    final int limit;
    public float miss=0;
    public float totalproccess=0;

    public LruCacheNormal ( int limit ) {
        this.limit = limit;
    }

    public void put ( KEY key, VALUE value ) {
        VALUE oldValue = map.put ( key, value );
        totalproccess++;
        /*If there was already an object under this key,
         then remove it before adding to queue
         Frequently used keys will be at the top so the search could be fast.
         */
        if ( oldValue != null ) {
            queue.removeFirstOccurrence ( key );
        }else{
            miss++;
            //System.out.println(miss);
        }
        queue.addFirst ( key );

        if ( map.size () > limit ) {
            final KEY removedKey = queue.removeLast ();
            map.remove ( removedKey );
        }

    }

    public float getMiss(){
        return miss;
    }

    public float getTotalproccess(){
        return totalproccess;
    }

    public VALUE get ( KEY key ) {

        /* Frequently used keys will be at the top so the search could be fast.*/
        queue.removeFirstOccurrence ( key );
        queue.addFirst ( key );
        return map.get ( key );
    }


    public VALUE getSilent ( KEY key ) {

        return map.get ( key );
    }

    public void remove ( KEY key ) {

        /* Frequently used keys will be at the top so the search could be fast.*/
        queue.removeFirstOccurrence ( key );
        map.remove ( key );
    }

    public int size () {
        return map.size ();
    }

    public String toString() {
        return map.toString ();
    }

    public interface LruCache<KEY, VALUE> {
    void put ( KEY key, VALUE value );

    VALUE get ( KEY key );

    VALUE getSilent ( KEY key );

    void remove ( KEY key );

    int size ();
}

}

