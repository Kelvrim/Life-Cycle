/**
 * This class is currently UNIMPLEMENTED. It has certain properties and functions that
 * clash with existing ones in Main.
 */

public class Cell {

    /** PROPERTIES ********************************************************************/
    private boolean isLiving;
    private int livingNeighbors = 0;

    /** CONSTRUCTORS ******************************************************************/
    public Cell(){}

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

    /** MUTATORS **********************************************************************/
    public void setLiving(boolean living) {
        isLiving = living;
    }

    public void setLivingNeighbors(int livingNeighbors) {
        this.livingNeighbors = livingNeighbors;
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
