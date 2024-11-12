import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    private static class AbsSizeComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return Math.abs(a) - Math.abs(b);
        }
    }



    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    @Test
    public void NaturalTest() {
        MaxArrayDeque61B<Integer> m = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());
        m.addFirst(1);
        m.addFirst(2);
        m.addFirst(99);
        m.addFirst(-59);
        assertThat(m.max()).isEqualTo(99);
    }

    @Test
    public void AbsSizeTest() {
        MaxArrayDeque61B<Integer> m = new MaxArrayDeque61B<Integer>(new AbsSizeComparator());
        m.addFirst(1);
        m.addFirst(2);
        m.addFirst(99);
        m.addFirst(-129);
        assertThat(m.max()).isEqualTo(-129);
    }
}
