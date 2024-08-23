/**
 * This class is currently UNIMPLEMENTED. It has certain properties and functions that
 * clash with existing ones in Main.
 */

public class Grid {
    /** PROPERTIES ********************************************************************/
    private int width = 10;
    private int height = 10;
    private final int MIN_ALLOWED_WIDTH = 10;
    private final int MIN_ALLOWED_HEIGHT = 10;
    private Cell[][] grid;

    /** CONSTRUCTORS ******************************************************************/
    public Grid(int width, int height) {
        if (width < MIN_ALLOWED_WIDTH){
            width = MIN_ALLOWED_WIDTH;
        }
        if (height < MIN_ALLOWED_HEIGHT){
            height = MIN_ALLOWED_HEIGHT;
        }
        this.width = width;
        this.height = height;

        grid = new Cell[width][height];

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
