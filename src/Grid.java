/**
 * This class is currently UNIMPLEMENTED. It has certain properties and functions that
 * clash with existing ones in Main.
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
        this.rows = rows;
        this.columns = columns;

        //grid = new Cell[rows][columns];
        fillGridWithCells();
        setToDefaultOrientation(grid);
        //setLivingNeighborOfEachCell(grid);
    }

    /** ACCESSORS *********************************************************************/
    public Cell[][] getGrid() {
        return grid;
    }

    /** MUTATORS **********************************************************************/
    public void nextGeneration(Cell[][] grid){
        Cell[][] futureGrid = grid;

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                Cell currentCell = grid[i][j];
                Cell futureCell = futureGrid[i][j];

                /*
                if (currentCell.isLiving()){
                    updateSurroundingCellsLivingCount(grid, i, j);
                }

                 */
                countSurroundingLivingCells(grid, i, j);

                futureGrid[i][j].setLiving(RuleSet.classicLife(grid[i][j]));
            }
        }

        System.out.println("Next generation:");
        //setLivingNeighborOfEachCell(futureGrid);
        GridDisplay.printGrid(futureGrid);
    }

    private void fillGridWithCells(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                grid[i][j] = new Cell(false);
            }
        }
    }

    private void setToDefaultOrientation(Cell[][] grid) {
        /*
        grid[3][3].setLiving(true);
        grid[3][4].setLiving(true);
        grid[2][3].setLiving(true);
        grid[5][6].setLiving(true);
        grid[6][5].setLiving(true);
        grid[6][6].setLiving(true);
        grid[7][4].setLiving(true);

         */
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
                    countSurroundingLivingCells(grid, i, j);
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


}
