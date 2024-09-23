import javax.swing.*;
import java.awt.event.*;

public class ControlPanel extends JPanel implements MouseListener {

    /** PROPERTIES ***********************************************************************************/
    // Controlling properties
    private boolean paused = true;
    private boolean eraser;

    // Buttons
    JButton startStop = new JButton("START");
    JButton reset = new JButton("RESET");

    // Actions
    PauseAction pauseAction = new PauseAction();
    ClearAction clearAction = new ClearAction();

    // References to other Objects and Classes
    private final Grid grid; // Reference to the Grid object

    /** CONSTRUCTOR **********************************************************************************/
    public ControlPanel(Grid grid){
        this.grid = grid;
        addMouseListener(this);

        add(startStop);
        add(reset);

        startStop.setFocusable(false);
        reset.setFocusable(false);

        this.getInputMap().put(KeyStroke.getKeyStroke(' '), "pauseAction");
        this.getActionMap().put("pauseAction", pauseAction);

        this.getInputMap().put(KeyStroke.getKeyStroke('c'), "clearAction");
        this.getActionMap().put("clearAction", clearAction);
    }

    /** ACCESSORS **********************************************************************************/
    public boolean isPaused(){
        return paused;
    }

    public boolean getEraserStatus(){
        return eraser;
    }
    /** MOUSE LISTENER *********************************************************************************/
    @Override
    public void mouseClicked(MouseEvent e) {

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

    /** ACTION CLASSES *************************************************************************************/
    public class PauseAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            paused = !paused;
        }
    }

    public class ClearAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            clearScreen();
        }

        public void clearScreen(){
            // loop through each cell in the grid
            for (int row = 0; row < grid.getRows(); row++) {
                for (int col = 0; col < grid.getColumns(); col++) {
                    Cell cell = grid.getCell(row, col);
                    cell.setLiving(false);
                }
            }
        }
    }
}
