package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    private HashMap<Integer, Node> adjList;
    private int vSize;
    private int eSize;

    private class Node {
        String[] synsets;
        ArrayList<Node> adj;

        public Node(String[] synsets, ArrayList<Node> adj) {
            this.synsets = synsets;
            this.adj = adj;
        }
    }

    public Graph() {
        adjList = new HashMap<Integer, Node>();
        vSize = 0;
        eSize = 0;
    }

    public void createVertex(int id, String[] synsets) {
        adjList.put(id, new Node(synsets, null));
        vSize++;
    }

    public void addDirectedEdge(int v, int w) {
        if (!adjList.containsKey(v)) {
            return;
        }
        adjList.get(v).adj.add(adjList.get(w));
        vSize++;
    }

    public int E() {
        return eSize;
    }

    public int V() {
        return vSize;
    }
}
