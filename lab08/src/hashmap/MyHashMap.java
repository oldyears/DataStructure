package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int capacity = 16;
    private double loadFactor = 0.75;
    private int size;

    /** Constructors */
    public MyHashMap() {
        this.size = 0;
        buckets = new Collection[capacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        this.capacity = initialCapacity;
        this.size = 0;
        buckets = new Collection[capacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }

    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        buckets = new Collection[capacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if ((double) (size + 1) / capacity >= loadFactor) {
            resize();
        }

        if (containsKey(key)) {
            for (Node node : buckets[bucketKey(key)]) {
                node.value = value;
            }
        } else {
            buckets[bucketKey(key)].add(new Node(key, value));
            size++;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if (containsKey(key)) {
            for (Node node : buckets[bucketKey(key)]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        for (Node node : buckets[bucketKey(key)]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        Collection<Node>[] tab;
        if ((tab = buckets) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; i++) {
                tab[i] = null;
            }
        }
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }


    private void resize() {
        capacity *= 2;
        Collection<Node>[] newBuckets = new Collection[capacity];
        for (int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = createBucket();
        }
        for (int i = 0; i < buckets.length; i++) {
            for (Node node : buckets[i]) {
                newBuckets[bucketKey(node.key)].add(node);
            }
        }
    }

    private int bucketKey(K key) {
        return Math.floorMod(key.hashCode(), capacity);
    }
}
