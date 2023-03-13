package edu.greenriver.sdev333;

import java.util.Iterator;

public class BSTSet<KeyType extends Comparable<KeyType>> implements MathSet<KeyType>{

    private Node root;

    //this does not have value / ValueType.  Just the Key.  That is the difference from other BST we already did.
    private class Node{
        private KeyType key;
        private Node left;
        private Node right;
        private int n;

        public Node(KeyType key, int n){
            this.key = key;
            this.n = n;
        }
    }


    /**
     * Puts the specified key into the set.
     *
     * @param key key to be added into the set
     */
    @Override
    public void add(KeyType key) {
        put(key);
    }

    private void put(KeyType key) {
        root = put(root, key);
    }

    // helper method for put for recursion
    private Node put(Node x, KeyType key){
        // current is the root of the subtree we are looking at


        if(x == null){
            return new Node(key, 1);
        }
        int cmp = key.compareTo(x.key);
        // go left
        if(cmp < 0) x.left = put(x.left, key);

            //go right
        else if (cmp >  0) x.right = put(x.right, key);

        //already exists
        //else x.key = key;  //already exists
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Is the key in the set?
     *
     * @param key key to check
     * @return true if key is in the set, false otherwise
     */
    @Override
    public boolean contains(KeyType key) {
        return get(key) != null;
    }

    private KeyType get(KeyType key) {
        return get(root, key);
    }

    private KeyType get(Node x, KeyType key){
        if(x == null){return null;}
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.key;
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
        return size(root);
    }

    private int size(Node x){
        if(x == null){ return 0;}
        else{return x.n;}
    }

    /**
     * Determine the union of this set with another specified set.
     * Returns A union B, where A is this set, B is other set.
     * A union B = {key | A.contains(key) OR B.contains(key)}.
     * Does not change the contents of this set or the other set.
     *
     * @param other specified set to union
     * @return the union of this set with other
     */
    @Override
    public MathSet<KeyType> union(MathSet<KeyType> other) {
        // create an empty set that will hold the result
        MathSet<KeyType> result = new BSTSet<KeyType>();
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
        MathSet<KeyType> result = new BSTSet<KeyType>();
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
     *
     * @param other specified set to difference
     * @return the difference of this set with other
     */
    @Override
    public MathSet<KeyType> difference(MathSet<KeyType> other) {
        // create an empty set that will hold the result
        MathSet<KeyType> result = new BSTSet<KeyType>();

        //iterate through all items in this
        Iterable<KeyType> keys = this.keys();
        Iterator<KeyType> itr = keys.iterator();
        while (itr.hasNext()){
            KeyType currentKey = itr.next();
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
        Queue<KeyType> queue = new Queue<>();
        //start the recursion, collecting results in the queue
        inorder(root, queue);
        // when done return the queue
        return queue;
    }

    private void inorder(Node current, Queue<KeyType> q){
        if(current == null){
            // do nothing - intentionally blank
            return;
        }
        inorder(current.left, q);
        q.enqueue(current.key);
        inorder(current.right, q);
    }
}
