package lifeCycle.panels;

import lifeCycle.*;
import lifeCycle.actions.*;
import lifeCycle.buttons.PauseButton;
import lifeCycle.buttons.RulesetDropdown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ControlPanel extends JPanel implements MouseListener, ActionListener {

    /** PROPERTIES ***********************************************************************************/
    // Controlling properties
    private boolean paused = true;
    private boolean eraser;

    // lifeCycle.Actions
    PauseAction pauseAction;
    ClearScreen clearAction;
    ToggleEraser toggleEraserAction;

    // Buttons
    PauseButton pauseButton = new PauseButton(this);
    JButton reset = new JButton("RESET");
    RulesetDropdown rulesetDropdown = new RulesetDropdown();

    // References to other Objects and Classes
    private static Grid grid; // Reference to the lifeCycle.Grid object

    /** CONSTRUCTOR **********************************************************************************/
    public ControlPanel(Grid grid) throws IOException {
        ControlPanel.grid = grid;
        addMouseListener(this);

        //this.setLayout();

        // Construct all abstract action objects
        pauseAction = new PauseAction(this);
        clearAction = new ClearScreen(this, grid);
        toggleEraserAction = new ToggleEraser(this);

        // Add buttons
        add(pauseButton);
        add(reset);
        add(rulesetDropdown);

        // JPanels for buttons
        JPanel actionPanel = new JPanel();
        actionPanel.add(pauseButton);
        actionPanel.add(reset);
        //actionPanel.setVisible(true);

        this.add(actionPanel, BorderLayout.WEST);

        // Button misc settings
        pauseButton.setPreferredSize(new Dimension(30, 30));
        reset.setPreferredSize(new Dimension(30, 30));

        pauseButton.setFocusable(false);
        reset.setFocusable(false);
        rulesetDropdown.setFocusable(false);


        // Button logic
        /*
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paused = !paused;
            }
        });

         */

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.clearGrid();
            }
        });

        //test

        // Add actions to input maps (certain keys trigger actions)
        this.getInputMap().put(KeyStroke.getKeyStroke(' '), "pauseAction");
        this.getActionMap().put("pauseAction", pauseAction);

        this.getInputMap().put(KeyStroke.getKeyStroke('c'), "clearAction");
        this.getActionMap().put("clearAction", clearAction);

        this.getInputMap().put(KeyStroke.getKeyStroke('e'), "toggleEraserAction");
        this.getActionMap().put("toggleEraserAction", toggleEraserAction);
    }

    /** MUTATORS **********************************************************************************/
    public void togglePause(){
        paused = !paused;
    }

    public void toggleEraser(){
        eraser = !eraser;
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
}
