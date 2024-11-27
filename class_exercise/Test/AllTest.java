package Test;

import org.junit.Test;

public class AllTest {
    static int binarySearch(String[] sorted, String x, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = (lo + hi) / 2;
        int cmp = x.compareTo(sorted[mid]);
        if (cmp < 0) return binarySearch(sorted, x, lo, mid - 1);
        else if (cmp > 0) return binarySearch(sorted, x, mid + 1, hi);
        else return mid;
    }


    @Test
    public void AllTempTest() {

    }
}
