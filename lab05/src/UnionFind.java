public class UnionFind {
    // TODO: Instance variables
    int size;
    int[] vertices;


    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        // check the N valid
        if (N <= 0) {
            throw new IllegalArgumentException("please enter a positive integer");
        }

        size = N;
        vertices = new int[N];

        for (int i = 0; i < N; i++) {
            vertices[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        checkValid(v);
        return Math.abs(vertices[find(v)]);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        checkValid(v);
        return vertices[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        checkValid(v1);
        checkValid(v2);
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        checkValid(v);

        // find the root node
        int node = v;
        while (vertices[node] > -1) {
            node = vertices[node];
        }

        // path-compression to fasting search-time
        int start = v;
        while (vertices[start] > -1) {
            int temp = start;
            start = vertices[start];
            vertices[temp] = node;
        }

        return node;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        checkValid(v1);
        checkValid(v2);
        int root1 = find(v1);
        int root2 = find(v2);

        if (root1 == root2) {
            return;
        }
        else if (vertices[root1] < vertices[root2]) {
            vertices[root1] += vertices[root2];
            vertices[root2] = root1;
        }
        else if (vertices[root2] <= vertices[root1]) {
            vertices[root2] += vertices[root1];
            vertices[root1] = root2;
        }
    }

    private void checkValid(int v) {
        if (v < 0 || v >= size) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (size - 1));
        }
    }

}
