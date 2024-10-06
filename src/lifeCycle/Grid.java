package lifeCycle;

/**
 * A class for creating the lifeCycle.Cell grid. Handles dimensions and some logic (such as
 * counting the surrounding living neighbors of a lifeCycle.Cell.
 */
public class Grid {
    /** PROPERTIES ********************************************************************/
    private int rows;
    private int columns;
    private Cell[][] grid;

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
        grid = new Cell[rows][columns];

        initializeGrid();
        setToDefaultOrientation();
        countAllLivingCells(grid);
    }

    /** ACCESSORS *********************************************************************/
    public Cell[][] getGrid() {
        return grid;
    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    /** MUTATORS **********************************************************************/
    /**
     * O(9^n) time complexity. Fine for now, needs to be improved
     *
     * Handles the next iteration of the grid. Uses a second grid as a buffer
     * to prevent mistakes with livingNeighbors.
     *
     * Goes through each cell in the grid, and for each cell checks all 9 of its neighbors
     *
     * @param grid
     */
    public void nextGeneration(Cell[][] grid){
        countNeighborsOfAllUpdatedCells(grid);


        // First prepare each cell (give it a next state)
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.columns; j++) {
                this.grid[i][j].prepareUpdate();
            }
        }

        // Then actually advance them, once all the new states are computed
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.columns; j++) {
                this.grid[i][j].update();
            }
        }

    }

    /**
     * Fills the grid with cells, all of them dead by default
     */
    private void initializeGrid(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                grid[i][j] = new Cell(false, i, j);
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

        // 2X2 backwards L in the top left. forms a blinker structure with the other L
        grid[3][3].setLiving(true);
        grid[2][3].setLiving(true);
        grid[3][2].setLiving(true);

        // 1X3 Horizontal line in the right middle
        grid[5][6].setLiving(true);
        grid[5][7].setLiving(true);
        grid[5][8].setLiving(true);

        // Travelling blip
        grid[6][1].setLiving(true);
        grid[6][3].setLiving(true);
        grid[7][2].setLiving(true);
        grid[7][3].setLiving(true);
        grid[8][2].setLiving(true);
    }

    public void clearScreen(){
        // loop through each cell in the grid
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Cell cell = getCell(row, col);
                cell.setLiving(false);
            }
        }
    }

    /** HELPERS ***********************************************************************/
    /**
     * Iterates through the grid and calls countSurroundingLivingCells, which updates
     * the livingNeighbors count of the cell in question
     */
    private void countAllLivingCells(Cell[][] grid){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                countSurroundingLivingNeighbors(grid, i, j);
            }
        }
    }

    public void countNeighborsOfAllUpdatedCells(Cell[][] grid) {
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.columns; j++) {
                if (grid[i][j].getPreviousState() != grid[i][j].isLiving()){
                    countSurroundingLivingNeighbors(grid, i, j);
                    countNeighborsOfSurroundingCells(grid, i, j);
                }
            }
        }
    }

    private void countNeighborsOfSurroundingCells(Cell[][] grid, int i, int j) {
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                int newRow = i + rowOffset;
                int newColumn = j + columnOffset;

                // Check if new position is contained in the grid
                if (isValidPosition(newRow, newColumn)) {
                    countSurroundingLivingNeighbors(grid, newRow, newColumn);
                }
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
    public void countSurroundingLivingNeighbors(Cell[][] grid, int i, int j) {

        int count = 0;

        // Iterates through each surrounding cell including the current cell
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++){
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++){
                int newRow = i + rowOffset;
                int newColumn = j + columnOffset;

                // Check if new position is contained in the grid
                if (isValidPosition(newRow, newColumn)){
                    // Then, if the cell at the new position is living
                    if (grid[newRow][newColumn].isLiving()){
                        count++;
                    }
                }
                grid[i][j].setLivingNeighbors(count);
            }
        }
        if (grid[i][j].isLiving()){
            // Since the cell in question is hit too, we decrement it once (if living)
            grid[i][j].decrementLivingNeighbors();
        }
    }

    private boolean isValidPosition(int newRow, int newColumn) {
        if ((newRow >= 0 && newRow < rows) && (newColumn >= 0 && newColumn < columns)) {
            return true;
        }
        return false;
    }

}