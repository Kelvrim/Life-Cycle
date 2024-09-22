import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        final int ROWS = 50;         // rows and columns for the grid
        final int COLUMNS = 80;
        final int DELAY = 200;       // the higher the delay, the slower cells move | milliseconds


        Grid grid = new Grid(ROWS, COLUMNS);

        // Apply Darcula theme to JFrame and JPanels
        FlatDarculaLaf.setup();

        // Instantiate JPanels
        ControlPanel controlPanel = new ControlPanel();
        GridPanel gridPanel = new GridPanel(grid, controlPanel);

        // Instantiate JFrame
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gridPanel);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        /** MAIN GAME LOOP ***********************************************************************************/
        while (true) {
            // If un-paused, keep generating
            if (!controlPanel.isPaused()){
                grid.nextGeneration(grid.getGrid()); // Update the grid to the next generation
            }
            gridPanel.repaint(); // Refresh the display
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}