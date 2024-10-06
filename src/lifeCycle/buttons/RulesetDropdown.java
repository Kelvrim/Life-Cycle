package lifeCycle.buttons;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RulesetDropdown extends JComboBox {
    String[] rules = {"Conway's Game of Life", "Maze", "Assimilation", "Vote"};
    static String selectedRule;

    // Constructor
    public RulesetDropdown(){
        super();  // call the parent constructor

        // Add rule options to JComboBox
        for (String rule : rules) {
            this.addItem(rule);
        }

        this.addActionListener(createSelectionListener());

        // Set default selected rule
        selectedRule = rules[0];
    }

    public static String getSelectedRule() {
        return selectedRule;
    }

    // creates an action listener for selection changes
    private ActionListener createSelectionListener() {
        return e -> {
            // Update selectedRule whenever a new rule is selected
            selectedRule = (String) this.getSelectedItem();
        };
    }
}
