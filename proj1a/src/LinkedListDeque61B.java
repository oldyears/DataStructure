import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private class Node {
        public T data;
        public Node next;
        public Node prev;
        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }


    public LinkedListDeque61B() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        Size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node node = new Node(x);
        node.next = sentinel.next;
        sentinel.next.prev = node;
        sentinel.next = node;
        node.prev = sentinel;
        Size++;
    }

    @Override
    public void addLast(T x) {
        Node node = new Node(x);
        sentinel.prev.next = node;
        node.prev = sentinel.prev;
        node.next = sentinel;
        sentinel.prev = node;
        Size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node node = sentinel.next;
        while (node != sentinel) {
            list.add(node.data);
            node = node.next;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return this.Size;
    }

    @Override
    public T removeFirst() {
        if (Size == 0) {
            return null;
        }
        Node result = sentinel.next;
        sentinel.next = result.next;
        result.next.prev = sentinel;
        return result.data;
    }

    @Override
    public T removeLast() {
        if (Size == 0) {
            return null;
        }
        Node result = sentinel.prev;
        sentinel.prev = result.prev;
        result.prev.next = sentinel;
        return result.data;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= Size) {
            return null;
        }
        Node p = sentinel.next;
        while (index-- > 0) {
            p = p.next;
        }
        return p.data;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= Size) {
            return null;
        }
        return getRecursive_(sentinel.next, index).data;
    }

    private Node getRecursive_(Node current, int index) {
        if (index == 0) {
            return current;
        }
        return getRecursive_(current.next, --index);
    }

    private final Node sentinel;
    private int Size;
}
