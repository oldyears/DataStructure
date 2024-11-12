package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int front;
    private int last;
    private int maxSize;
    private int size;

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int current;
        private int wizPos;
        public ArrayDequeIterator() {
            current = front;
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T result = items[current];
            current = Math.floorMod(current + 1, maxSize);
            wizPos++;
            return result;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other instanceof ArrayDeque61B<?> otherArray) {
            if (otherArray.size() != this.size) {
                return false;
            }
            for (int s = 0, i = front; s < this.size; s++) {
                if (!this.items[i].equals(otherArray.get(i))) {
                    return false;
                }
                i = Math.floorMod(i + 1, maxSize);
            }
        }
        return true;
    }

    public ArrayDeque61B() {
        maxSize = 8;
        items = (T[]) new Object[maxSize];
        front = 0;
        last = 0;
        size = 0;
    }

    private void resize(int capacity) {
        // create a new item[]
        T[] newItems = (T[]) new Object[capacity];

        // arraycopy
        for (int i = 0; i < size; i++) {
            newItems[i] = items[front];
            front = Math.floorMod(front + 1, maxSize);
        }

        // update the data
        front = 0;
        last = size - 1;
        maxSize = capacity;
        items = newItems;
    }

    @Override
    public void addFirst(T x) {
        if (size == maxSize) {
            resize(size * 2);
        }
        if (items[front] == null) {
            items[front] = x;
            size++;
            return ;
        }
        front = Math.floorMod(front - 1, maxSize);
        items[front] = x;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == maxSize) {
            resize(size * 2);
        }
        if (items[last] == null) {
            items[last] = x;
            size++;
            return ;
        }
        last = Math.floorMod(last + 1, maxSize);
        items[last] = x;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int index = front;
        for (int i = 0; i < size; i++) {
            returnList.add(items[index]);
            index = Math.floorMod(index + 1, maxSize);
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

    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public T removeFirst() {
        if (size * 1.0 / maxSize < 0.25 && maxSize > 15) {
            resize(maxSize / 2);
        }
        if (size != 0) {
            size--;
            T returnValue = items[front];
            items[front] = null;
            front = Math.floorMod(front + 1, maxSize);
            return returnValue;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if (size * 1.0 / maxSize < 0.25 && maxSize > 15) {
            resize(maxSize / 2);
        }
        if (size != 0) {
            size--;
            T returnValue = items[last];
            items[last] = null;
            last = Math.floorMod(last - 1, maxSize);
            return returnValue;
        }
        return null;
    }

    @Override
    public T get(int index) {
        index = Math.floorMod(index + front, maxSize);
        if (index > maxSize || index < 0) {
            return null;
        }
        return items[index];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }
}
