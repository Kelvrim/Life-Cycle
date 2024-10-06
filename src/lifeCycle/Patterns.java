package lifeCycle;

/**
 * This class contains methods that set cells in a certain pattern to be alive.
 * There are several well known patterns in Conways Game of Life, such as patterns
 * that travel and that form a (presumably) infinite loop.
 *
 * TODO: More patterns, cut this class into categories (travellers, replicators, etc.)
 *
 * TODO: Implement nullPointer Exceptions
 */

public class Patterns {

    /**
     * 3x3 blip that will travel south-east
     *
     * @param grid A grid of Cells
     * @param i i position of Cell
     * @param j j position of Cell
     */
    public static void travellingBlip(Cell[][] grid, int i, int j) {
            grid[i - 1][j - 1].setLiving(true);
            grid[i - 1][j + 1].setLiving(true);
            grid[i][j].setLiving(true);
            grid[i][j + 1].setLiving(true);
            grid[i + 1][j].setLiving(true);
    }

    /**
     * 1x3 that rotates
     *
     * @param grid A grid of Cells
     * @param i i position of Cell
     * @param j j position of Cell
     */
    public static void propeller(Cell[][] grid, int i, int j) {
        grid[i][j - 1].setLiving(true);
        grid[i][j].setLiving(true);
        grid[i][j + 1].setLiving(true);
    }

    /**
     * 2x2 backwards L that will stabilize as a 2x2 square unless paired with upsideDownL
     *
     * @param grid A grid of Cells
     * @param i i position of Cell
     * @param j j position of Cell
     */
    public static void backwardsL(Cell[][] grid, int i, int j) {
        grid[i][j].setLiving(true);
        grid[i - 1][j].setLiving(true);
        grid[j][i - 1].setLiving(true);
    }

    /**
     * 2x2 upside-down L that will stabilize as a 2x2 square unless paired with backwardsL
     *
     * @param grid A grid of Cells
     * @param i i position of Cell
     * @param j j position of Cell
     */
    public static void upsideDownL(Cell[][] grid, int i, int j) {
        grid[i][j].setLiving(true);
        grid[i][j + 1].setLiving(true);
        grid[i + 1][j].setLiving(true);
    }
}
