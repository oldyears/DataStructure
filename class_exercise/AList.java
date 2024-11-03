public class AList {
    private int[] items;
    private int size;

    public AList() {
        items = new int[100];
        size = 0;
    }

    public void addLast(int x) {
        if (size == items.length) {
            resize(size + 1);
        }
        items[size] = x;
        size += 1;
    }

    public int getLast() {
        return items[size - 1];
    }

    public int get(int i) {
        if (i >= items.length) {
            throw new IndexOutOfBoundsException();
        }
        return items[i];
    }

    public int removeLast() {
        int x = items[size - 1];
        items[size - 1] = 0;
        size -= 1;
        return x;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        int[] a = new int[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

}
