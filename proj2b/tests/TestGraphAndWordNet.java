import main.Graph;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestGraphAndWordNet {

    @Test
    public void testGraphCreateVertex() {
        Graph g = new Graph();
        String[] temp = {"test1", "test2", "test3", "test4"};
        g.createVertex(0, temp);
        assertThat(g.V()).isEqualTo(1);
    }
}
