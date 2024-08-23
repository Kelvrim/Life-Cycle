public class Main {
    public static void main(String[] args) {
        int width = 10;     // Width and height for the grid
        int height = 10;
        int time = 5;       // How many 'turns' we play for

        boolean[][] grid = new boolean[width][height];
        defaultOrientation(grid);

        System.out.println("First generation:");
        printGrid(grid);

        while (time != 0){
            nextGeneration(grid);
            time--;
        }

        Cell testCell = new Cell();
        System.out.println(testCell.isLiving());
    }

    /**
     * prints as text to system
     * @param grid
     */
    private static void printGrid(boolean[][] grid) {
        int width = grid.length;
        int height = grid[0].length;

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if (!grid[i][j]){
                    System.out.print("` ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    /**
     * sets the 'default orientation' of the grid. This is totally arbitrary and can be
     * changed
     * @param grid
     */
    private static void defaultOrientation(boolean[][] grid) {
        grid[3][3] = true;
        grid[3][4] = true;
        grid[2][3] = true;
        grid[5][6] = true;
        grid[6][5] = true;
        grid[6][6] = true;
        grid[7][4] = true;
        grid[0][0] = true;
        grid[0][1] = true;
        grid[1][0] = true;
        grid[9][0] = true;
    }

    /**
     * Bread and butter. Iterates through the grid and updates the futureGrid
     * based on the rules of life (in applyRulesOfLife)
     * @param grid
     */
    private static void nextGeneration(boolean[][] grid){
        int width = grid.length;
        int height = grid[0].length;
        boolean[][] futureGrid = grid;

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                int aliveNeighbors = countAliveNeighbors(grid, i, j);

                // Cell must be subtracted from neighbors as it was counted before
                if (grid[i][j]){
                    aliveNeighbors--;
                }

                futureGrid[i][j] = applyRulesOfLife(grid, i, j, aliveNeighbors);
            }
        }

        System.out.println("Next generation:");
        printGrid(futureGrid);
    }

    private static boolean applyRulesOfLife(boolean[][] grid, int i, int j, int aliveNeighbors) {
        // Underpopulated: cell dies
        if (grid[i][j] && aliveNeighbors < 2){
            return false;
        }
        // Overpopulated: cell dies
        else if (grid[i][j] && aliveNeighbors > 3){
            return false;
        }
        // The cell is empty and population is right: cell born
        else if (!grid[i][j] && aliveNeighbors == 3){
            return true;
        }
        else {
            return grid[i][j];
        }
    }

    private static int countAliveNeighbors(boolean[][] grid, int i, int j) {
        int aliveNeighbors = 0;
        for (int i2 = -1; i2 < 2; i2++){
            for (int j2 = -1; j2 < 2; j2++){
                if ((i + i2 >= 0 && i + i2 < grid.length) &&
                        (j + j2 >= 0 && j + j2 < grid[0].length)){
                    if (grid[i + i2][j + j2]){
                         aliveNeighbors++;
                    }
                }
            }
        }
        return aliveNeighbors;
    }
}