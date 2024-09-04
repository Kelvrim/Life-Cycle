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

        fillGridWithCells();
        setToDefaultOrientation(grid);
        countAllLivingCells(grid);
    }

    /** ACCESSORS *********************************************************************/
    public Cell[][] getGrid() {
        return grid;
    }

    /** MUTATORS **********************************************************************/
    public void nextGeneration(Cell[][] grid){
        resetLivingNeighborsCountOfAllCells(grid);
        countAllLivingCells(grid);
        Cell[][] futureGrid = grid;

        System.out.println("test");
        GridDisplay.printGrid(futureGrid);
        GridDisplay.printGrid(grid);

        System.out.println(futureGrid[0][0].isLiving());
        System.out.println(futureGrid[0][0].getLivingNeighbors());


        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                Cell currentCell = grid[i][j];
                Cell futureCell = futureGrid[i][j];

                futureCell.setLiving(RuleSet.classicLife(currentCell));
            }
        }

        System.out.println("Next generation:");
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

        grid[5][6].setLiving(true);
        grid[5][7].setLiving(true);
        grid[5][8].setLiving(true);
    }

    /** HELPERS ***********************************************************************/

    public static void countAllLivingCells(Cell[][] grid){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                countSurroundingLivingCells(grid, i, j);
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

    public static void resetLivingNeighborsCountOfAllCells(Cell[][] grid){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                grid[i][j].setLivingNeighbors(0);
            }
        }
    }

}
