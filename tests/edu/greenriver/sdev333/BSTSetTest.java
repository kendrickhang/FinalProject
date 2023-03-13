package edu.greenriver.sdev333;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.IterableResult;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BSTSetTest {

    @org.junit.jupiter.api.Test
    void add() {
        MathSet<String> set = new BSTSet<>();
        set.add("one");
        set.add("two");
        Iterable<String> keys = set.keys();
        Iterator itr = keys.iterator();
        assertEquals("one", itr.next());  //o before t
        assertEquals("two", itr.next());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        MathSet<String> set = new BSTSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");
        assertEquals(true, set.contains("three"));
        assertEquals(false, set.contains("five"));
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        MathSet<String> set = new BSTSet<>();
        assertEquals(true, set.isEmpty());
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");
        assertEquals(false, set.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void size() {
        MathSet<String> set = new BSTSet<>();
        assertEquals(0, set.size());
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");
        assertEquals(4, set.size());
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");
        assertEquals(4, set.size());
        set.add("five");
        set.add("six");
        set.add("seven");
        set.add("eight");
        assertEquals(8, set.size());
    }

    @org.junit.jupiter.api.Test
    void union() {
        MathSet<String> set = new BSTSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");
        MathSet<String> other = new BSTSet<>();
        other.add("five");
        other.add("six");
        other.add("seven");
        other.add("eight");
        MathSet<String> union = set.union(other);
        Iterable<String> keys = union.keys();
        Iterator itr = keys.iterator();
        assertEquals("eight", itr.next());    // eight five four one seven six three two
        assertEquals("five", itr.next());
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        itr.next();
        assertEquals("two", itr.next());
    }

    @org.junit.jupiter.api.Test
    void intersection() {
        MathSet<String> set = new BSTSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");
        MathSet<String> other = new BSTSet<>();
        other.add("three");
        other.add("four");
        other.add("seven");
        other.add("eight");
        MathSet<String> intersection = set.intersection(other);
        Iterable<String> keys = intersection.keys();
        Iterator itr = keys.iterator();
        assertEquals("four", itr.next());
        assertEquals("three", itr.next());
    }

    @org.junit.jupiter.api.Test
    void difference() {
        MathSet<String> set = new BSTSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");
        MathSet<String> other = new BSTSet<>();
        other.add("three");
        other.add("four");
        other.add("seven");
        other.add("eight");
        MathSet<String> diff = set.difference(other);
        Iterable<String> keys = diff.keys();
        Iterator itr = keys.iterator();
        assertEquals("one", itr.next());
        assertEquals("two", itr.next());
    }

    @org.junit.jupiter.api.Test
    void keys() {
    }
}