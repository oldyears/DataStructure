package main;

import java.util.*;

public class Graph {
    private TreeMap<String, ArrayList<Integer>> wordSynsets;
    private HashMap<Integer, Node> adjList;
    private int vSize;
    private int eSize;

    public class Node {
        String[] synsets;
        ArrayList<Node> adj;

        public Node(String[] synsets, ArrayList<Node> adj) {
            this.synsets = synsets;
            this.adj = adj;
        }
    }

    public Graph() {
        adjList = new HashMap<>();
        wordSynsets = new TreeMap<>();
        vSize = 0;
        eSize = 0;
    }

    public void createVertex(int id, String[] synsets) {
        adjList.put(id, new Node(synsets, new ArrayList<>()));
        for (String str : synsets) {
            if (!wordSynsets.containsKey(str)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(id);
                wordSynsets.put(str, list);
            } else {
                wordSynsets.get(str).add(id);
            }
        }
        vSize++;
    }

    public void addDirectedEdge(int v, int w) {
        if (!adjList.containsKey(v)) {
            return;
        }
        adjList.get(v).adj.add(adjList.get(w));
        vSize++;
    }

    // input id return node
    public Node getNode(int id) {
        if (!adjList.containsKey(id)) {
            return null;
        }
        return adjList.get(id);
    }

    // input string return nodes
    public ArrayList<Node> neighbors(String word) {
        ArrayList<Node> result = new ArrayList<>();
        for (Integer id : wordSynsets.get(word)) {
            result.addAll(getNode(id).adj);
        }
        return result;
    }

    public Set<String> wordSubSet(String word) {
        Set<String> result = new HashSet<>();
        for (Node node : neighbors(word)) {
            result.addAll(Arrays.asList(node.synsets));
        }
        return result;
    }

    public int E() {
        return eSize;
    }

    public int V() {
        return vSize;
    }
}
