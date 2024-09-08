/**
 * A class for creating the Cell grid. Handles dimensions and some logic (such as
 * counting the surrounding living neighbors of a Cell.
 *
 *
 * emily wuz here
 */
public class Grid {
    /** PROPERTIES ********************************************************************/
    private static int rows = 10;
    private static int columns = 10;
    private Cell[][] grid = new Cell[rows][columns];

    public final int MIN_ALLOWED_ROWS = 10;
    public final int MIN_ALLOWED_COLUMNS = 10;

    /** CONSTRUCTORS ******************************************************************/
    public Grid(int rows, int columns) {
        if (rows < MIN_ALLOWED_ROWS){
            rows = MIN_ALLOWED_ROWS;
        }
        if (columns < MIN_ALLOWED_COLUMNS){
            columns = MIN_ALLOWED_COLUMNS;
        }
        Grid.rows = rows;
        Grid.columns = columns;

        fillGridWithCells();
        setToDefaultOrientation();
        countAllLivingCells(grid);
    }

    /** ACCESSORS *********************************************************************/
    public Cell[][] getGrid() {
        return grid;
    }

    /** MUTATORS **********************************************************************/
    /**
     * Handles the next iteration of the grid. Uses a second grid as a buffer
     * to prevent mistakes with livingNeighbors.
     * @param grid
     */
    public void nextGeneration(Cell[][] grid){
        // First reset livingNeighborCount of every cell to 0, then recount for this iteration
        resetLivingNeighborsCountOfAllCells(grid);
        countAllLivingCells(grid);

        // It's not really redundant I promise
        Cell[][] futureGrid = grid;

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                Cell currentCell = grid[i][j];
                Cell futureCell = futureGrid[i][j];

                // Judges currentCell with the classic ruleset, and applies the result to futureCell
                futureCell.setLiving(RuleSet.classicLife(currentCell));
            }
        }

        System.out.println("Next generation:");
        GridDisplay.printGrid(futureGrid);
    }

    /**
     * Fills the grid with cells, all of them dead by default
     */
    private void fillGridWithCells(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                grid[i][j] = new Cell(false);
            }
        }
    }

    /**
     * sets the grid to default orientation, which is determined in this function
     */
    private void setToDefaultOrientation() {
        // 2X2 Upside down L in the top left
        grid[0][0].setLiving(true);
        grid[0][1].setLiving(true);
        grid[1][0].setLiving(true);

        // 1X3 Horizontal line in the right middle
        grid[5][6].setLiving(true);
        grid[5][7].setLiving(true);
        grid[5][8].setLiving(true);
    }

    /** HELPERS ***********************************************************************/
    /**
     * Iterates through the grid and calls countSurroundingLivingCells, which updates
     * the livingNeighbors count of the cell in question
     * @param grid i rows and j columns
     */
    public static void countAllLivingCells(Cell[][] grid){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                countSurroundingLivingCells(grid, i, j);
            }
        }
    }

    /**
     * Given the i and j location of a living Cell on grid, this counts its surrounding
     * living Neighbors and updates the livingNeighbors property of the Cell at [i][j]
     * @param grid  i rows and j columns
     * @param i     outer array coordinate of the cell in question
     * @param j     inner array coordinate of the cell in question
     */
    public static void countSurroundingLivingCells(Cell[][] grid, int i, int j) {
        // Iterates through each surrounding cell including the current cell
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++){
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++){
                int newRow = i + rowOffset;
                int newColumn = j + columnOffset;

                // Check if new position is contained in the grid
                if ((newRow >= 0 && newRow < rows) && (newColumn >= 0 && newColumn < columns)){
                    // Then, if the cell at the new position is living
                    if (grid[newRow][newColumn].isLiving()){
                        grid[i][j].incrementLivingNeighbors();
                    }
                }
            }
        }
        if (grid[i][j].isLiving()){
            // Since the cell in question is hit too, we decrement it once (if living)
            grid[i][j].decrementLivingNeighbors();
        }
    }

    /**
     * When recounting the livingNeighbors of a cell on future generations, we need to start at zero.
     * This iterates through each cel and resets the livingNeighbor count to zero
     * @param grid i rows and j columns
     */
    public static void resetLivingNeighborsCountOfAllCells(Cell[][] grid){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                grid[i][j].setLivingNeighbors(0);
            }
        }
    }

}
