package deque;


import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    // private variables
    private Comparator<T> comparator;

    // constructor
    public MaxArrayDeque61B(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }

        T maxValue = this.get(0);
        for (T x : this) {
            if (c.compare(x, maxValue) > 0) {
                maxValue = x;
            }
        }

        return maxValue;
    }

}

