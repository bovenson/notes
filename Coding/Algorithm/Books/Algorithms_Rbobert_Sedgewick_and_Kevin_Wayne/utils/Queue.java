package utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 抽象数据类型 - 队列
 * 单链表实现
 * 在尾部插入, 头部移除
 * @author  bovenson
 */
public class Queue<Item> implements Iterable<Item> {
    Node<Item> head;    // head of this queue
    Node<Item> tail;    // tail of this queue
    int size;

    /**
     * 初始化队列
     */
    public Queue() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Return true if this queue is empty.
     * @return return true if this queue is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Add an item into this queue.
     * @param item item to be added
     */
    public void enqueue(Item item) {
        Node<Item> newNode = new Node<Item>();
        newNode.item = item;
        newNode.next = null;

        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
        size++;
    }

    /**
     * Return the item least recently added into this queue.
     * @return item least recently added into this queue
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return head.item;
        }
    }

    /**
     * Removes and return the item least recently added into this queue.
     * @return the item least recently added into this queue
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Item res = head.item;
            head = head.next;
            size--;
            if (isEmpty()) {
                tail = null;
            }
            return res;
        }
    }

    /**
     * Return size of this queue.
     * @return size of this queue
     */
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(head);
    }

    private class ListIterator<Item> implements Iterator {
        private Node<Item> current;

        public ListIterator(Node<Item> head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item res = current.item;
            current = current.next;
            return res;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * 单链表节点
     * @param <Item> 链表储存元素类型
     */
    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    public static void main(String args[]) {
        Queue<String> queue = new Queue<String>();
        String[] str = {"a", "b", "v", "草"};

        for (String s : str) {
            queue.enqueue(s);
        }

        System.out.println(queue.size());

        for (String s : queue) {
            System.out.println(s);
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}
