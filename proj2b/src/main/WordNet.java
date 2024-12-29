package main;

import edu.princeton.cs.algs4.In;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    // use a graph to store the data
    private Graph graph;

    // constructor
    public WordNet(String synsetsFile, String hyponymsFile) {
        graph = new Graph();

        In sysIn = new In(synsetsFile);
        In hyponymsIn = new In(hyponymsFile);

        // create vertex in graph
        while (!sysIn.isEmpty()) {
            String data= sysIn.readLine();
            String[] sysData = data.split(",");
            int id = Integer.parseInt(sysData[0]);
            String[] synsets = sysData[1].split(" ");
            graph.createVertex(id, synsets);
        }

        // add directed Edge between the vertex in graph
        while (!hyponymsIn.isEmpty()) {
            String data= hyponymsIn.readLine();
            String[] hyponyms = data.split(",");
            int id = Integer.parseInt(hyponyms[0]);
            for (int i = 1; i < hyponyms.length; i++) {
                graph.addDirectedEdge(id, Integer.parseInt(hyponyms[i]));
            }
        }
    }

    public Set<String> hyponyms(String word) {
        return null;
    }


}
