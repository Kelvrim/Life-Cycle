import javax.swing.*;
import java.awt.*;

public class GridDisplay extends JPanel {
    private Grid grid; // Reference to the Grid object

    public GridDisplay(Grid grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(grid.getColumns() * 10, grid.getRows() * 10)); // Adjust size based on grid dimensions
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        //repaint();
    }

    private void drawGrid(Graphics g) {
        int cellSize = 10; // Size of each cell

        // Loop through each cell in the grid
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns(); col++) {
                Cell cell = grid.getCell(row, col);

                // Set color based on cell state
                if (cell.isLiving()) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }

                // Draw the cell
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);

                // Draw cell borders
                g.setColor(Color.GRAY);
                g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }
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
