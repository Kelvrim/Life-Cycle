package lifeCycle;

import com.formdev.flatlaf.FlatDarculaLaf;
import lifeCycle.panels.ControlPanel;
import lifeCycle.panels.GridPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final int ROWS = 75;         // rows and columns for the grid
        final int COLUMNS = 125;
        final int DELAY = 20;       // the higher the delay, the slower cells move | milliseconds


        Grid grid = new Grid(ROWS, COLUMNS);

        // Apply Darcula theme to JFrame and JPanels
        FlatDarculaLaf.setup();

        // Instantiate JPanels
        ControlPanel controlPanel = new ControlPanel(grid);
        GridPanel gridPanel = new GridPanel(grid, controlPanel);

        // Instantiate JFrame
        JFrame frame = new JFrame("LIFE CYCLE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gridPanel);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        /** MAIN GAME LOOP ***********************************************************************************/
        while (true) {
            grid.countNeighborsOfAllUpdatedCells(grid.getGrid());
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