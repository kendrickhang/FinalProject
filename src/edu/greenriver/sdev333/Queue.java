package edu.greenriver.sdev333;

import java.util.Iterator;

/**
 * Ken Hang
 * FIFO queue, page 151 of the red book
 */
public class Queue<ItemType> implements Iterable<ItemType> {
    // private helper node class:
    private class Node {
        private ItemType data;
        private Node next;
    }

    // fields
    private Node first;
    private Node last;
    private int size;

    /**
     * Constructor. Creates an empty queue.
     */
    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Check if the queue is empty.
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Number of items in the queue.
     * @return the number of items in the queue
     */
    public int size() {
        return size;
    }

    /**
     * Adds a specified item to the rear of the queue.
     * @param item item to be added
     */
    public void enqueue(ItemType item) {
        Node oldlast = last;
        last = new Node();
        last.data = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }

        size++;
    }

    /**
     * Removes and returns the item at the front of the queue.
     * @return the item that was at the front of the queue before it was removed
     */
    public ItemType dequeue() {
        ItemType item = first.data;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     * @return an Iterator.
     */
    @Override
    public Iterator<ItemType> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<ItemType> {
        private Node current;

        public LinkedListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public ItemType next() {
            ItemType item = current.data;
            current = current.next;
            return item;
        }
    }
}
