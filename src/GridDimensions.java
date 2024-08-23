/**
 * This class is currently UNIMPLEMENTED. It has certain properties and functions that
 * clash with existing ones in Main.
 */

public class GridDimensions {
    /** PROPERTIES ********************************************************************/
    private int width = 10;         // Not to be set less than 10
    private int height = 10;        // Not to be set less than 10
    private Cell[][] grid = new Cell[width][height];

    /** CONSTRUCTORS ******************************************************************/
    public GridDimensions(int width, int height) throws Exception {
        if (width < 10 || height < 10){
            throw new Exception("Neither width or height can be less than 10");
        }
        this.width = width;
        this.height = height;

        setToDefaultOrientation(grid);
    }

    /** ACCESSORS *********************************************************************/
    /** MUTATORS **********************************************************************/
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


}
