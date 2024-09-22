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

    /** CONSTRUCTOR **********************************************************************************/
    public ControlPanel(){
        addMouseListener(this);

        add(startStop);
        add(reset);

        startStop.setFocusable(false);
        reset.setFocusable(false);

        this.getInputMap().put(KeyStroke.getKeyStroke(' '), "pauseAction");
        this.getActionMap().put("pauseAction", pauseAction);
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
}
