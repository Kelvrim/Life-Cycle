package lifeCycle;

import lifeCycle.Customization.Patterns;

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

    /** CONSTRUCTOR ******************************************************************/
    public Grid(int rows, int columns) {
        // Ensure that rows and columns are within proper bounds
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
        countLivingNeighborsForAllCells(grid);
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
     * Counts the neighbors of only the recently changed cells. Keeps track of this
     * by comparing a cell's previous state with its current state. Then, in two passes,
     * we set each cell's newState and update all oof them at once. If we were to update them
     * without preparation first, it there would be a discrepancy between a cells livingNeighbor count
     * and the actual alive neighbors surrounding it.
     *
     * @param grid Grid of Cell objects.
     */
    public void nextGeneration(Cell[][] grid){
        // Count the neighbors of only recently changed Cells
        countNeighborsOfAllUpdatedCells(grid);

        // Cells should be prepared first, then updated all at once
        prepareUpdateForAllCells();
        updateAllCells();
    }

    /**
     * Goes through each and every Cell, and applies the selected Ruleset to its 
     * newState. The Cell's age is also incremented if it's living
     */
    private void prepareUpdateForAllCells() {
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.columns; j++) {
                Cell currentCell = grid[i][j];

                currentCell.prepareUpdate();
                currentCell.determineAge();
            }
        }
    }

    /**
     * For all Cells, the current isLiving status is inherited by previousState
     * and isLiving inherits newState
     */
    private void updateAllCells() {
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.columns; j++) {
                Cell currentCell = grid[i][j];

                currentCell.update();
            }
        }
    }

    /**
     * Fills the grid with cells, all of them dead by default
     */
    private void initializeGrid(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                grid[i][j] = new Cell(false);
            }
        }
    }

    /**
     * sets up a few prebuilt patterns to get things started
     */
    private void setToDefaultOrientation() {
        // 2X2 Upside down L in the top left
        Patterns.upsideDownL(grid, 0, 0);

        // 2X2 backwards L in the top left. forms a blinker structure with the other L
        Patterns.backwardsL(grid, 3, 3);

        // 1X3 Horizontal line in the right middle
        Patterns.propeller(grid, 5, 7);

        // Travelling blip
        Patterns.travellingBlip(grid,7, 2);
    }

    /**
     * Set each Cell.isLiving to false
     */
    public void clearGrid(){
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Cell cell = getCell(row, col);
                cell.setLiving(false);
            }
        }
    }

    /** HELPERS ***********************************************************************/
    /**
     * counts the living neighbors for ALL cells. Only to be used once, in the constructor.
     * @param grid
     */
    private void countLivingNeighborsForAllCells(Cell[][] grid){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                countSurroundingLivingNeighbors(grid, i, j);
            }
        }
    }

    /**
     * Goes through each Cell in the grid, and if its recently updated (if its
     * previousState is not the same as its isLiving state) counts the neighbors
     * of that cell.
     * @param grid
     */
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

    /**
     * Not to be confused with countSurroundingLivingNeighbors! Goes to each neighbor of
     * a specific Cell, and counts the living neighbors of each of THOSE cells
     *
     * to demonstrate:
     *
     *                  O O O O O
     *                  O y y y O
     *                  O y x y O
     *                  O y y y O
     *                  O O O O O
     *
     * here, x is the cell at the ith and jth position of the cell. This function goes to all of
     * the y's (x'x neighbors). each y's living neighbors are counted and stored in each y's
     * livingNeighbors property (so for each y, the surrounding Os and the one x is counted)
     *
     * @param grid Cell[][] grid
     * @param i    i position of particular cell
     * @param j    j position of particular cell
     */
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
     * Counts the surrounding living neighbors of a specified Cell
     *
     * @param grid Cell[][] grid
     * @param i    i position of particular cell
     * @param j    j position of particular cell
     */
    public void countSurroundingLivingNeighbors(Cell[][] grid, int i, int j) {

        int count = 0;

        // Iterates through each surrounding cell including the current cell
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++){
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++){
                int newRow = i + rowOffset;
                int newColumn = j + columnOffset;

                // Check if new position is contained in the grid
                if (isValidPosition(newRow, newColumn) && grid[newRow][newColumn] != grid[i][j]){
                    // Then, if the cell at the new position is living
                    if (grid[newRow][newColumn].isLiving()){
                        count++;
                    }
                }
                grid[i][j].setLivingNeighbors(count);
            }
        }
    }

    /**
     * Verifies that the specified cell lies within the parameters of the grid
     *
     * @param i ith position of the Cell in question
     * @param j jth position of the Cell in question
     * @return
     */
    private boolean isValidPosition(int i, int j) {
        if ((i >= 0 && i < rows) && (j >= 0 && j < columns)) {
            return true;
        }
        return false;
    }

}