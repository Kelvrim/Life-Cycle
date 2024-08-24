public class GridDisplay {
    /** PROPERTIES ********************************************************************/
    /** CONSTRUCTORS ******************************************************************/
    /** ACCESSORS *********************************************************************/
    /** MUTATORS **********************************************************************/
    /** HELPERS ***********************************************************************/
    /**
     * prints as text to system. Mainly used for testing
     * @param grid 2D Grid of Cell objects
     */
    public static void printGrid(Cell[][] grid) {
        int width = grid.length;
        int height = grid[0].length;

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                Cell currentCell = grid[i][j];

                if (currentCell.isLiving()){
                    System.out.print("* ");
                } else {
                    System.out.print("` ");
                }
            }
            System.out.println();
        }
    }

}
