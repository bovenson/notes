package utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 背包抽象数据类型
 */
public class Bag<Item> implements Iterable<Item> {

    private Node<Item> head;    // 头结点
    private int length;         // 背包内元素个数

    /**
     * Initializes a empty bag.
     */
    public Bag() {
        head = null;
        length = 0;
    }

    /**
     * Return true if bag is empty,
     * @return {@code true} if bag is empty
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of items in this bag.
     * @return the number of items in this bag
     */
    public int size() {
        return length;
    }

    /**
     * Adds the item to this bag.
     * @param item the item to add to this bag
     */
    public void add(Item item) {
        Node<Item> oldHead = head;
        head = new Node<Item>();
        head.item = item;
        head.next = oldHead;
        length++;
    }

    /**
     * Returns an iterator that iterates over in this bag in arbitrary order.
     * @return Returns an iterator that iterates over in this bag in arbitrary order
     */
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(head);
    }

    /**
     * Single linked list node.
     * @param <Item> a generic type of item
     */
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    private class ListIterator<Item> implements Iterator<Item>{
        private Node<Item> current;

        public ListIterator(Node<Item> head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String args[]) {
        Bag<String> bag = new Bag<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("Size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}
