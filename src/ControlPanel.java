import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel implements MouseListener, ActionListener {

    /** PROPERTIES ***********************************************************************************/
    // Controlling properties
    private boolean paused = true;
    private boolean eraser;

    // Buttons
    JButton startStop = new JButton(new ImageIcon("resources/pauseLogo.png"));
    JButton reset = new JButton("RESET");

    // Actions
    PauseAction pauseAction = new PauseAction();
    ClearAction clearAction = new ClearAction();
    ToggleEraserAction toggleEraserAction = new ToggleEraserAction();

    // References to other Objects and Classes
    private static Grid grid; // Reference to the Grid object

    /** CONSTRUCTOR **********************************************************************************/
    public ControlPanel(Grid grid){
        this.grid = grid;
        addMouseListener(this);

        add(startStop);
        add(reset);

        startStop.setPreferredSize(new Dimension(300, 300));
        reset.setPreferredSize(new Dimension(30, 30));


        // Button logic
        startStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paused = !paused;
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClearAction.clearScreen();

            }
        });

        startStop.setFocusable(false);
        reset.setFocusable(false);

        this.getInputMap().put(KeyStroke.getKeyStroke(' '), "pauseAction");
        this.getActionMap().put("pauseAction", pauseAction);

        this.getInputMap().put(KeyStroke.getKeyStroke('c'), "clearAction");
        this.getActionMap().put("clearAction", clearAction);

        this.getInputMap().put(KeyStroke.getKeyStroke('e'), "toggleEraserAction");
        this.getActionMap().put("toggleEraserAction", toggleEraserAction);
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

    /** ACTION LISTENER METHODS *************************************************************************************/
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /** ACTION CLASSES *************************************************************************************/
    public class PauseAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            paused = !paused;
        }
    }

    public class ToggleEraserAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            eraser = !eraser;
        }
    }

    public class ClearAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearScreen();
        }

        public static void clearScreen(){
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
