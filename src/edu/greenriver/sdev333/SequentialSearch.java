package edu.greenriver.sdev333;


import java.util.ArrayList;

/**
 * Sequential search (unordered linked list implementation)
 * Refer to p. 374-377 in Sedgewick and Wayne, Algorithms, 4th edition
 * @param <KeyType>
 */
public class SequentialSearch<KeyType> {
    private Node first;
    private int size = 0;
    private class Node{
        KeyType key;
        Node next;
        public Node(KeyType key, Node next){
            this.key = key;
            this.next = next;
        }
    }

    public void put(KeyType key) {
        for(Node x = first; x != null; x = x.next){
            if(key.equals(x.key)){
                return;
            }
        }
        first = new Node(key, first);
        size++;
    }

//    @Override
//    public ValueType get(KeyType key) {
//        for(Node x = first; x != null; x = x.next){
//            if(key.equals(x.key)){
//                return x.val;
//            }
//        }
//        return null;
//    }


    public int size() {
        return size;
    }

    public Iterable<KeyType> keys() {
        ArrayList<KeyType> keyList = new ArrayList<>();
        for(Node x = first; x != null; x = x.next){
            keyList.add(x.key);
        }
        return keyList;
    }
}
