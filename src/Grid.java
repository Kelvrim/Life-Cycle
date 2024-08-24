/**
 * This class is currently UNIMPLEMENTED. It has certain properties and functions that
 * clash with existing ones in Main.
 */

public class Grid {
    /** PROPERTIES ********************************************************************/
    private int rows = 10;
    private int columns = 10;
    private final int MIN_ALLOWED_ROWS = 10;
    private final int MIN_ALLOWED_COLUMNS = 10;
    private Cell[][] grid;

    /** CONSTRUCTORS ******************************************************************/
    public Grid(int rows, int columns) {
        if (rows < MIN_ALLOWED_ROWS){
            rows = MIN_ALLOWED_ROWS;
        }
        if (columns < MIN_ALLOWED_COLUMNS){
            columns = MIN_ALLOWED_COLUMNS;
        }
        this.rows = rows;
        this.columns = columns;

        grid = new Cell[rows][columns];
        setToDefaultOrientation(grid);
        setLivingNeighborOfEachCell(grid);
    }

    /** ACCESSORS *********************************************************************/
    /** MUTATORS **********************************************************************/

    private void nextGeneration(Cell[][] grid){
        Cell[][] futureGrid = grid;

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){

                if (grid[i][j].isLiving()){
                    updateSurroundingCellsLivingCount(grid, i, j);
                }

                futureGrid[i][j] = applyRulesOfLife(grid, i, j, aliveNeighbors);
            }
        }

        System.out.println("Next generation:");
        printGrid(futureGrid);
    }

    private void setToDefaultOrientation(Cell[][] grid) {
        grid[3][3].setLiving(true);
        grid[3][4].setLiving(true);
        grid[2][3].setLiving(true);
        grid[5][6].setLiving(true);
        grid[6][5].setLiving(true);
        grid[6][6].setLiving(true);
        grid[7][4].setLiving(true);
        grid[0][0].setLiving(true);
        grid[0][1].setLiving(true);
        grid[1][0].setLiving(true);
    }

    /** HELPERS ***********************************************************************/
    /**
     * This should only be used for the first generation of the grid. After that,
     * we'll call updateSurroundingCellsLivingCount() on each pass when necessary
     * @param grid
     */
    private void setLivingNeighborOfEachCell(Cell[][] grid){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (grid[i][j].isLiving()){
                    updateSurroundingCellsLivingCount(grid, i, j);
                }
            }
        }
    }

    /**
     * Given the i and j location of a living Cell on grid, this updates the livingNeighbors
     * property of all surrounding cells
     * @param grid
     * @param i
     * @param j
     */
    private void updateSurroundingCellsLivingCount(Cell[][] grid, int i, int j) {
        // If the Cell isn't living, it shouldn't have been called
        if (!grid[i][j].isLiving()){
            return;
        }

        // Iterates through each surrounding cell including the current cell
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++){
            for (int columnOffset = -1; columnOffset < 2; columnOffset++){
                int newRow = i + rowOffset;
                int newColumn = j + columnOffset;

                // Check if new position is contained in the grid
                if ((newRow >= 0 && newRow < rows) && (newColumn >= 0 && newColumn < columns)){
                    grid[newRow][newColumn].incrementLivingNeighbors();
                }
            }
        }
        // Since the cell in question is hit too, we decrement it once
        grid[i][j].decrementLivingNeighbors();
    }

}
