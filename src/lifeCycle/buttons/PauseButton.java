package lifeCycle.buttons;

import lifeCycle.panels.ControlPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PauseButton extends JButton implements ActionListener {
    /** PROPERTIES ********************************************************************************************/
    // Dimensions
    private final Dimension PAUSE_BUTTON_SIZE = (new Dimension(25, 25));

    // Class references
    PauseButton pauseButtonAction;
    ControlPanel controlPanel;

    // Pause icon setup TODO: these icons suck and need to be changed. See Github issues
    BufferedImage bufferedPauseImage = ImageIO.read(new File("resources/pauseLogo.png"));
    Image pauseImage = bufferedPauseImage.getScaledInstance(PAUSE_BUTTON_SIZE.width, PAUSE_BUTTON_SIZE.height, Image.SCALE_DEFAULT);
    ImageIcon pauseImageIcon = new ImageIcon(pauseImage);

    // Play icon setup
    BufferedImage bufferedPlayImage = ImageIO.read(new File("resources/playLogo.png"));
    Image playImage = bufferedPlayImage.getScaledInstance(PAUSE_BUTTON_SIZE.width, PAUSE_BUTTON_SIZE.height, Image.SCALE_DEFAULT);
    ImageIcon playImageIcon = new ImageIcon(playImage);


    /** CONSTRUCTOR ********************************************************************************************/
    public PauseButton(ControlPanel controlPanel) throws IOException {
        this.controlPanel = controlPanel;

        setFocusable(false);
        setIcon(playImageIcon);

        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controlPanel.togglePause();

        if (controlPanel.isPaused()){
            this.setIcon(playImageIcon);
        } else {
            this.setIcon(pauseImageIcon);
        }
    }
}
