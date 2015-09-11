/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador_cache;

public interface LruCache<KEY, VALUE> {
    void put ( KEY key, VALUE value );

    VALUE get ( KEY key );

    VALUE getSilent ( KEY key );

    void remove ( KEY key );

    int size ();
}
