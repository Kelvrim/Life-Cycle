import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final int ROWS = 50;     // rows and columns for the grid
        final int COLUMNS = 79;
        final int DELAY = 200;       // the higher the delay, the slower cells move | milliseconds

        FlatDarculaLaf.setup();

        // TODO: find a better way to name this monstrosity
        Grid grid = new Grid(ROWS, COLUMNS);
        GridDisplay gridDisplay = new GridDisplay(grid);
        Cell[][] gridArray = grid.getGrid();

        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gridDisplay);
        frame.pack();
        frame.setVisible(true);



        /* MAIN GAME LOOP ***********************************************************************************/
        while (true) {
            grid.nextGeneration(gridArray); // Update the grid to the next generation
            gridDisplay.repaint(); // Refresh the display
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}