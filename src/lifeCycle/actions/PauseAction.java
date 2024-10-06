package lifeCycle.actions;

import lifeCycle.panels.ControlPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PauseAction extends AbstractAction {

    /** PROPERTIES ******************************************************************************************/
    public static ControlPanel controlPanel; // Reference to the lifeCycle.Grid object

    /** CONSTRUCTOR ******************************************************************************************/
    public PauseAction(ControlPanel controlPanel){
        PauseAction.controlPanel = controlPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controlPanel.togglePause();

    }
}
