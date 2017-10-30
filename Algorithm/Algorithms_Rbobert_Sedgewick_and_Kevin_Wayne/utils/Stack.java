package utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
    private Node<Item> top;   // top of the stack
    private int cnt;    // size of the stack

    public Stack() {
        top = null;
        cnt = 0;
    }

    /**
     * Return true if stack is empty.
     * @return return true if stack is empty
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Push an item into this stack.
     * @param item
     */
    public void push(Item item) {
        Node<Item> oldTop = top;
        top = new Node<Item>();
        top.item = item;
        top.next = oldTop;
        cnt++;
    }

    /**
     * Pop an item form this stack and return.
     * @return the item pop form this stack
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }

        Item res = top.item;
        top = top.next;
        cnt--;
        return res;
    }

    /**
     * Return (but not remove) the item most recently added to this stack.
     * @return Item most recently added to this stack
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }

        return top.item;
    }

    /**
     * Return the size of this stack.
     * @return size of this stack
     */
    public int size() {
        return cnt;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(top);
    }

    /**
     * Single linked list Node.
     * @param <Item>
     */
    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        ListIterator(Node<Item> top) {
            current = top;
        }

        @Override
        public boolean hasNext() {
            return current != null;
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

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String args[]) {
        Stack<String> stack = new Stack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("#")) {
                stack.push(item);
            } else {
                break;
            }
        }
        StdOut.println("Stack size:" + stack.size());

        for (String s : stack) {
            System.out.println(stack.pop());
        }

        StdOut.println("Stack size:" + stack.size());
    }
}
