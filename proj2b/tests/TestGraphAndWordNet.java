import main.Graph;
import main.WordNet;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class TestGraphAndWordNet {

    @Test
    public void testGraphCreateVertex() {
        Graph g = new Graph();
        String[] temp = {"test1", "test2", "test3", "test4"};
        g.createVertex(0, temp);
        assertThat(g.V()).isEqualTo(1);

    }

    @Test
    public void testGraphCreateEdge() {
        WordNet wn = new WordNet("./data/wordnet/synsets16.txt","./data/wordnet/hyponyms16.txt");
        assertThat(wn.hyponyms("change")).isEqualTo(Set.of("alteration", "change", "demotion", "increase", "jump", "leap", "modification", "saltation", "transition", "variation"));
    }
}
