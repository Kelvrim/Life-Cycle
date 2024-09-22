import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    /** PROPERTIES ****************************************************************************************/
    private final Grid grid; // Reference to the Grid object
    private final int CELL_SIZE = 10;

    /** CONSTRUCTOR **************************************************************************************/
    public GridPanel(Grid grid) {
        this.grid = grid;
        // Adjust size based on grid dimensions
        setPreferredSize(new Dimension(grid.getColumns() * CELL_SIZE, grid.getRows() * CELL_SIZE));

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {

        // loop through each cell in the grid
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns(); col++) {
                Cell cell = grid.getCell(row, col);

                // Set color based on cell state
                if (cell.isLiving()) {
                    g.setColor(GridColors.ALIVE_CELL);
                } else {
                    g.setColor(GridColors.DEAD_CELL);
                }

                // Draw the cell
                g.fillRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                // Draw cell borders
                g.setColor(GridColors.GRIDLINES);
                g.drawRect(col * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
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

    /** MOUSE LISTENER METHODS **********************************************************************************/
    @Override
    public void mouseClicked(MouseEvent e) {
        Cell cellUnderMouse;
        cellUnderMouse = grid.getCell(e.getY() / CELL_SIZE , e.getX() / CELL_SIZE);

        cellUnderMouse.switchLiving();
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /** MOUSE MOTION LISTENER METHODS **********************************************************************************/
    @Override
    public void mouseDragged(MouseEvent e) {
        Cell cellUnderMouse;
        cellUnderMouse = grid.getCell(e.getY() / CELL_SIZE , e.getX() / CELL_SIZE);

        cellUnderMouse.switchLiving();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /** KEY LISTENER METHODS **********************************************************************************/
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
