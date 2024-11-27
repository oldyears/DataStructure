import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private int N;
    private int virtualTopSite;
    private int virtualBottomSite;
    private boolean[][] grid;
    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF wquFull;
    private int countOpenSite;
    private final int[][] directions = {
            {0, 1},     // right
            {1, 0},     // down
            {0, -1},    // left
            {-1, 0}     // up
    };

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        // check the N valid
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        // initializing variables
        grid = new boolean[N][N];
        wqu = new WeightedQuickUnionUF(N * N + 2);
        wquFull = new WeightedQuickUnionUF(N * N + 1);
        this.N = N;
        virtualTopSite = N * N;
        virtualBottomSite = N * N + 1;
        countOpenSite = 0;
    }

    // first check the index valid, then make the site of index true
    // check the up, down, left and right direction if it's connected
    public void open(int row, int col) {
        // TODO: Fill in this method.
        checkIndex(row, col);
        grid[row][col] = true;
        countOpenSite++;
        int index1DCenter = xyTo1D(row, col);

        // four directions
        for(int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            int index1DAround = xyTo1D(newRow, newCol);

            // check index valid
            if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
                continue;
            }

            // union the adjacent site
            if (grid[newRow][newCol]) {
                wqu.union(index1DCenter, index1DAround);
                wquFull.union(index1DCenter, index1DAround);
            }
        }

        // special site union
        if (row == 0) {
            wqu.union(virtualTopSite, index1DCenter);
            wquFull.union(virtualTopSite, index1DCenter);
        }
        if (row == N - 1) {
            wqu.union(virtualBottomSite, index1DCenter);
        }
    }


    /**
     * Is open boolean.
     *
     * @param row the row
     * @param col the col
     * @return the boolean
     */
    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        checkIndex(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return wquFull.connected(virtualTopSite, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return countOpenSite;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return wqu.connected(virtualTopSite, virtualBottomSite);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    // transform the xy index into 1D index
    private int xyTo1D(int row, int col) {
        return row * this.N + col;
    }

    // check the index valid
    private void checkIndex(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

}
