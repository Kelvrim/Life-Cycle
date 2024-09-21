import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int width = 10;     // Width and height for the grid
        int height = 10;
        int time = 5;       // How many 'turns' we play for

        // TODO: find a better way to name this monstrosity
        Grid grid = new Grid(width, height);
        GridDisplay gridDisplay = new GridDisplay(grid);
        Cell[][] gridArray = grid.getGrid();

        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gridDisplay);
        frame.pack();
        frame.setVisible(true);

        while (true) {
            grid.nextGeneration(gridArray); // Update the grid to the next generation
            gridDisplay.repaint(); // Refresh the display
            try {
                Thread.sleep(500); // Delay for 500ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
        System.out.println("GRID First generation:");
        GridDisplay.printGrid(gridArray);

        while (time != 0){
            grid.nextGeneration(gridArray);
            time--;
        }

         */
    }
}