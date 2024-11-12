package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel;
    private int size;

    private class Node {
        T item;
        Node next;
        Node prev;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        sentinel.next = new Node(x, null, sentinel.next);
        // empty linkedList
        if (sentinel.next.next == null) {
            sentinel.next.next = sentinel.next;
            sentinel.next.prev = sentinel.next;
        } else {
            sentinel.next.prev = sentinel.next.next.prev;
            sentinel.next.prev.next = sentinel.next;
            sentinel.next.next.prev = sentinel.next;
        }
        size += 1;
    }

    @Override
    public void addLast(T x) {
        addFirst(x);
        sentinel.next = sentinel.next.next;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node n = sentinel.next;
        int index = 0;
        while (index < size) {
            returnList.add(n.item);
            n = n.next;
            index++;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T result = sentinel.next.item;
        sentinel.next.next.prev = sentinel.next.prev;
        sentinel.next.prev.next = sentinel.next.next;
        sentinel.next = sentinel.next.next;
        return result;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T result = sentinel.next.prev.item;
        sentinel.next.prev = sentinel.next.prev.prev;
        sentinel.next.prev.prev.next = sentinel.next;
        return result;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node n = sentinel.next;
        while (index-- != 0) {
            n = n.next;
        }
        return n.item;
    }

    private T recursiveHelper(Node cur, int index) {
        if (index == 0) {
            return cur.item;
        }
        return recursiveHelper(cur.next, index - 1);
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return recursiveHelper(sentinel.next, index);
    }

    // Iterator for linkedListDeque
    private class LinkedListDequeIterator implements Iterator<T> {
        Node current;
        int wizPos;
        public LinkedListDequeIterator() {
            current = sentinel.next;
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T result = current.item;
            current = current.next;
            wizPos++;
            return result;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other instanceof LinkedListDeque61B<?> otherArray) {
            if (otherArray.size != this.size) {
                return false;
            }

            LinkedListDequeIterator it_this = new LinkedListDequeIterator();
            LinkedListDequeIterator it_other = new LinkedListDequeIterator();

            while (it_this.hasNext()) {
                if (!it_this.next().equals(it_other.next())) {
                    return false;
                }
            }

            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }
}

