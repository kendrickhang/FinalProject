package edu.greenriver.sdev333;

import java.util.Arrays;
import java.util.Iterator;

/** Separate Chaining Hash Set
 * Uses Sequential Search linked list for side chains
 * Size of array is hardcoded for constructor without this parameter passed
 *
 * @author Adam Winter
 * @param <KeyType>
 */

public class SeparateChainingHashSet<KeyType extends Comparable<KeyType>> implements MathSet<KeyType> {

    private SequentialSearch[] st;

    private int M;  // M is the number of buckets

    public SeparateChainingHashSet(int M){
        // take their number of buckets, save it  into my field
        this.M = M;

        // create the array with integrity
        st = new SequentialSearch[M];

        for (int i = 0; i < M; i++){
            st[i] = new SequentialSearch<>();
        }
    }

    public SeparateChainingHashSet(){
        this(997);
    }

    private int hash(KeyType key){
        // has function = they give me a key, I return an int, is what was done in class
        return ( key.hashCode() & 0x7fffffff ) % M;
    }


    /**
     * Puts the specified key into the set.
     *
     * @param key key to be added into the set
     */
    @Override
    public void add(KeyType key) {
        int index = hash(key);    //find the bucket number
        //put the key into that bucket
        st[index].put(key);
    }

    /**
     * Is the key in the set?
     *
     * @param key key to check
     * @return true if key is in the set, false otherwise
     */
    @Override
    public boolean contains(KeyType key) {
        int hash = hash(key);
        for (Object other : st[hash].keys()){
            if(key.equals(other)){
                return true;
            }
        }
        return false;
    }

    /**
     * Is the set empty?
     *
     * @return true if the set is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Number of keys in the set
     *
     * @return number of keys in the set.
     */
    @Override
    public int size() {
        // go through each bucket and add up each individual size
        int sum = 0;
        for (int i = 0; i < M; i++){
            sum += st[i].size();
        }
        return sum;
    }

    /**
     * Determine the union of this set with another specified set.
     * Returns A union B, where A is this set, B is other set.
     * A union B = {key | A.contains(key) OR B.contains(key)}.
     * Does not change the contents of this set or the other set.
     * 7a43c5948aa8
     *
     * @param other specified set to union
     * @return the union of this set with other
     */
    @Override
    public MathSet<KeyType> union(MathSet<KeyType> other) {
        // create an empty set that will hold the result
        MathSet<KeyType> result = new SeparateChainingHashSet<>();
        for(KeyType currentKey : this.keys()){
            result.add(currentKey);
        }
        for(KeyType currentKey : other.keys()){
            result.add(currentKey);
        }
        return result;
    }

    /**
     * Determine the intersection of this set with another specified set.
     * Returns A intersect B, where A is this set, B is other set.
     * A intersect B = {key | A.contains(key) AND B.contains(key)}.
     * Does not change the contents of this set or the other set.
     *
     * @param other specified set to intersect
     * @return the intersection of this set with other
     */
    @Override
    public MathSet<KeyType> intersection(MathSet<KeyType> other) {
        // create an empty set that will hold the result
        MathSet<KeyType> result = new SeparateChainingHashSet<>();
        for(KeyType currentKey : this.keys()){
            if(other.contains(currentKey)){
                result.add(currentKey);
            }
        }
        return result;
    }

    /**
     * Determine the difference of this set with another specified set.
     * Returns A difference B, where A is this set, B is other set.
     * A difference B = {key | A.contains(key) AND !B.contains(key)}.
     * Does not change the contents of this set or the other set.
     * 7a43c5948aa8
     *
     * @param other specified set to difference
     * @return the difference of this set with other
     */
    @Override
    public MathSet<KeyType> difference(MathSet<KeyType> other) {
        // create an empty set that will hold the result
        MathSet<KeyType> result = new SeparateChainingHashSet<>();
        for(KeyType currentKey : this.keys()){
            if(!other.contains(currentKey)){
                result.add(currentKey);
            }
        }
        return result;
    }

    /**
     * Retrieves a collection of all the keys in this set.
     *
     * @return a collection of all keys in this set
     */
    @Override
    public Iterable<KeyType> keys() {
        Queue<KeyType> collector = new Queue<>();
        for (int i = 0; i < M; i++){
            for (Object key : st[i].keys()){
                collector.enqueue((KeyType) key);
            }
        }
        return collector;
    }


    public String toString(){
        int size = size();
        Iterable keys = keys();
        Iterator itr = keys.iterator();
        String[] stringArray = new String[size];
        for(int i = 0; i < size; i++){
            stringArray[i] = itr.next().toString();
        }
        return Arrays.toString(stringArray);
    }

}
