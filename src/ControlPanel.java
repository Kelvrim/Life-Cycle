import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControlPanel extends JPanel implements KeyListener, MouseListener {

    /** PROPERTIES ***********************************************************************************/
    // Controlling properties
    private boolean paused = false;

    // Buttons
    JButton startStop = new JButton("START");
    JButton reset = new JButton("RESET");

    /** CONSTRUCTOR **********************************************************************************/
    public ControlPanel(){
        add(startStop);
        add(reset);
    }

    /** ACCESSORS **********************************************************************************/
    public boolean getPaused(){
        return paused;
    }

    /** KEY LISTENER *********************************************************************************/
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == 32 /*|| e.getKeyChar() == ' '*/ || e.getKeyChar() == 'a'){
            paused = !paused;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32 /*|| e.getKeyChar() == ' '*/ || e.getKeyChar() == 'a'){
            paused = !paused;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
}
