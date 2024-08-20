public class Main {
    public static void main(String[] args) {
        int width = 10;
        int height = 10;

        boolean[][] grid = new boolean[width][height];
        defaultOrientation(grid);

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                if (grid[i][j] == false){
                    System.out.print("` ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    private static void defaultOrientation(boolean[][] grid) {
        grid[3][3] = true;
        grid[3][4] = true;
        grid[2][3] = true;
        grid[5][6] = true;
        grid[6][5] = true;
        grid[6][6] = true;
        grid[7][4] = true;
    }

    private static void nextGeneration(boolean[][] grid, int width, int height){
        boolean[][] futureGrid = grid;

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                int aliveNeighbors = 0;
                for (int i2 = -1; i2 < 2; i2++){
                    for (int j2 = -1; j2 < 2; j2++){

                    }
                }
            }
        }
    }




}