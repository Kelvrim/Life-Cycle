package lifeCycle.actions;

import lifeCycle.Grid;
import lifeCycle.panels.ControlPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ClearScreen extends AbstractAction {

    /** PROPERTIES ******************************************************************************************/
    public static ControlPanel controlPanel; // Reference to the lifeCycle.Grid object
    public static Grid grid;

    /** CONSTRUCTOR ******************************************************************************************/
    public ClearScreen(ControlPanel controlPanel, Grid grid){
        ClearScreen.controlPanel = controlPanel;
        ClearScreen.grid = grid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        grid.clearGrid();
    }
}