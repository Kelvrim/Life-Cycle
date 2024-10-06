package lifeCycle.Customization;

import lifeCycle.Cell;
import lifeCycle.panels.ControlPanel;

import java.awt.*;
import java.util.Random;

public class GridColors {
    public static Color GRIDLINES = new Color(33,34,37,255);
    public static Color DEAD_CELL = new Color(30,31,34,255);

    //public static Color aliveCell = new Color(103,154,124);


    /**
     * Decides what color a cell should be based on how old it is. For now, goes from
     * green and purple
     *
     * TODO: This is good for now, but it can be a lot prettier
     *
     * @param cell
     * @return
     */
    public static Color aliveCellColorPicker(Cell cell, ControlPanel controlPanel){
        int age = cell.getAge(); // Half of the age of cell in question

        if (age > 100){
            age = 100;
        }
            // Green to purple
            return new Color(103 + age/2, 154 - age/4, 124 + age/2);

    }





}


