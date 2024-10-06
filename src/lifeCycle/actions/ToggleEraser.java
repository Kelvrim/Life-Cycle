package lifeCycle.actions;

import lifeCycle.panels.ControlPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ToggleEraser extends AbstractAction {
    /** PROPERTIES ******************************************************************************************/
    public static ControlPanel controlPanel; // Reference to the lifeCycle.Grid object

    /** CONSTRUCTOR ******************************************************************************************/
    public ToggleEraser(ControlPanel controlPanel){
        ToggleEraser.controlPanel = controlPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controlPanel.toggleEraser();
    }
}