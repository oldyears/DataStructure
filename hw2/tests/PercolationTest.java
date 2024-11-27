import org.junit.jupiter.api.Test;
import ucb.util.Stopwatch;


import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class PercolationTest {

    /**
     * Enum to represent the state of a cell in the grid. Use this enum to help you write tests.
     * <p>
     * (0) CLOSED: isOpen() returns true, isFull() return false
     * <p>
     * (1) OPEN: isOpen() returns true, isFull() returns false
     * <p>
     * (2) INVALID: isOpen() returns false, isFull() returns true
     *              (This should not happen! Only open cells should be full.)
     * <p>
     * (3) FULL: isOpen() returns true, isFull() returns true
     * <p>
     */
    private enum Cell {
        CLOSED, OPEN, INVALID, FULL
    }

    /**
     * Creates a Cell[][] based off of what Percolation p returns.
     * Use this method in your tests to see if isOpen and isFull are returning the
     * correct things.
     */
    private static Cell[][] getState(int N, Percolation p) {
        Cell[][] state = new Cell[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int open = p.isOpen(r, c) ? 1 : 0;
                int full = p.isFull(r, c) ? 2 : 0;
                state[r][c] = Cell.values()[open + full];
            }
        }
        return state;
    }

    @Test
    public void basicTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        // open sites at (r, c) = (0, 1), (2, 0), (3, 1), etc. (0, 0) is top-left
        int[][] openSites = {
                {0, 1},
                {2, 0},
                {3, 1},
                {4, 1},
                {1, 0},
                {1, 1}
        };
        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED}
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }

    @Test
    public void oneByOneTest() {
        int N = 1;
        Percolation p = new Percolation(N);
        p.open(0, 0);
        Cell[][] expectedState = {
                {Cell.FULL}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isTrue();
    }

    // TODO: Using the given tests above as a template,
    //       write some more tests and delete the fail() line
    @Test
    public void OpenTest() {
        int N = 5;
        Percolation p = new Percolation(N);

        p.open(0, 0);
        assertThat(p.isOpen(0 , 0)).isEqualTo(true);

        p.open(1, 0);
        p.open(2, 0);
        p.open(3, 0);
        p.open(4, 0);
        p.open(4, 2);
        p.open(3, 2);

        assertThat(p.isFull(4, 0)).isEqualTo(true);
        assertThat(p.isFull(1, 2)).isEqualTo(false);
        assertThat(p.isFull(4, 2)).isEqualTo(true);
        assertThat(p.percolates()).isEqualTo(true);
    }

    @Test
    public void runtimeTest() {
        int trials = 100, gridSize = 50;

        Stopwatch timer1 = new Stopwatch();
        timer1.start();
        PercolationStats ps1 = new PercolationStats(gridSize, trials);
        double time1 = timer1.getElapsed();
        timer1.stop();
        System.out.println("Time for percolation_100_50: " + time1);

        trials = 200;
        gridSize = 50;
        timer1.start();
        PercolationStats ps2 = new PercolationStats(gridSize, trials);
        double time2 = timer1.getElapsed();
        timer1.stop();
        System.out.println("Time for percolation_200_50: " + time2);

        trials = 100;
        gridSize = 100;
        timer1.start();
        PercolationStats ps3 = new PercolationStats(gridSize, trials);
        double time3 = timer1.getElapsed();
        timer1.stop();
        System.out.println("Time for percolation_100_100: " + time3);

    }
}
