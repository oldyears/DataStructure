import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
    @Test
    public void addFristAndaddLast() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);

        assertThat(deque.size()).isEqualTo(4);

        deque.addLast(5);
        deque.addFirst(6);
        deque.addFirst(7);
        deque.addFirst(8);

        assertThat(deque.size()).isEqualTo(8);

        deque.addFirst(9);  // need to resize
        deque.addFirst(10);

        assertThat(deque.size()).isGreaterThan(8);
    }



    @Test
    public void getTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(1);
        deque.addLast(2);

        assertThat(deque.get(0)).isEqualTo(1);
        assertThat(deque.get(1)).isEqualTo(2);

        deque.addFirst(3);
        assertThat(deque.get(deque.getMaxSize() - 1)).isEqualTo(3);

        deque.addFirst(4);
        assertThat(deque.get(deque.getMaxSize() - 2)).isEqualTo(4);
    }

    @Test
    public void isEmptyAndSizeTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();

        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.size()).isEqualTo(0);

        deque.addLast(1);

        assertThat(deque.isEmpty()).isFalse();
        assertThat(deque.size()).isEqualTo(1);
    }

    @Test
    public void toListTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);

        assertThat(deque.toList()).isInstanceOf(List.class);
        assertThat(deque.toList()).isInstanceOf(ArrayList.class);

        List<Integer> list = deque.toList();
        assertThat(list.size()).isEqualTo(4);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    public void removeFirstAndremoveLast() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        assertThat(deque.removeFirst()).isEqualTo(1);
        assertThat(deque.removeLast()).isEqualTo(4);

        deque.addFirst(5);
        assertThat(deque.removeFirst()).isEqualTo(5);

        deque.addFirst(6);
        assertThat(deque.removeFirst()).isEqualTo(6);

        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
            deque.addFirst(i);
        }

        for (int i = 0; i < 16; i++) {
            deque.removeLast();
        }

        assertThat(deque.size() * 1.0 / deque.getMaxSize() * 100).isAtLeast(25);
    }

}
