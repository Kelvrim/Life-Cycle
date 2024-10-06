package lifeCycle;

import lifeCycle.Customization.Ruleset;

/**
 * Class for each cell within the grid. They only have two states: alive or dead. These are decided
 * by how many living neighbors the individual cell has, which depends on which rule from Ruleset is
 * being used.
 */

public class Cell {

    /** PROPERTIES ********************************************************************/
    private boolean isLiving;
    private int livingNeighbors = 0;    // Each cell (except the border cells) have 8 neighbors

    private boolean previousState;      // The cells previous isLiving state. Used to track recently changed cells
    private boolean newState;           // The cells state on the next generation

    private int age = 0;                // How many iterations the cell has been alive for

    /** CONSTRUCTORS ******************************************************************/
    public Cell(boolean isLiving){
        this.isLiving = isLiving;
    }

    /** ACCESSORS *********************************************************************/
    public boolean isLiving() {
        return isLiving;
    }

    public int getLivingNeighbors(){
        return livingNeighbors;
    }

    public boolean getNewState() {
        return newState;
    }

    public boolean getPreviousState() {
        return previousState;
    }

    public int getAge(){
        return age;
    }

    /** MUTATORS **********************************************************************/
    public void setLiving(boolean living) {
        isLiving = living;
    }

    public void switchLiving(){
        isLiving = !isLiving;
    }

    public void setLivingNeighbors(int livingNeighbors) {
        this.livingNeighbors = livingNeighbors;
    }

    public void setNewState(boolean newState){
        this.newState = newState;
    }

    public void setPreviousState(boolean previousState){
        this.previousState = previousState;
    }

    public void prepareUpdate() {
        newState = Ruleset.applyCurrentRuleset(this);
    }

    public void update() {
        this.previousState = this.isLiving;
        this.isLiving = this.newState;
    }

    /**
     * If the current cell isLiving, we increment by one. Otherwise, the age is 0. This should be
     * called on each new generation of the grid
     */
    public void determineAge() {
        if (isLiving){
            age++;
        } else {
            age = 0;
        }
    }
}
