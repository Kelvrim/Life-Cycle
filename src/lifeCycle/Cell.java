package lifeCycle;

/**
 * This class is currently UNIMPLEMENTED. It has certain properties and functions that
 * clash with existing ones in lifeCycle.Main.
 */

public class Cell {

    /** PROPERTIES ********************************************************************/
    private boolean isLiving;
    private int livingNeighbors = 0;

    private boolean previousState;
    private boolean newState;

    private int iPos;
    private int jPos;

    /** CONSTRUCTORS ******************************************************************/
    public Cell(){}

    public Cell(boolean isLiving, int i, int j){
        this.isLiving = isLiving;
        iPos = i;
        jPos = j;
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

    public int getiPos(){
        return  iPos;
    }

    public int getjPos(){
        return  jPos;
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
    /*
     * The following two functions are to be used when generating the next grid:
     * incrementing the livingNeighbors property when a cell is detected to be
     * alive.
     */
    public void incrementLivingNeighbors(){
        livingNeighbors++;
    }

    public void decrementLivingNeighbors(){
        livingNeighbors--;
    }
}
