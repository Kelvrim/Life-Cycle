public class Cell {
    /** PROPERTIES ********************************************************************/
    private boolean isLiving;
    private int livingNeighbors;

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

    /** MUTATORS **********************************************************************/
    public void setLiving(boolean living) {
        isLiving = living;
    }

    public void setLivingNeighbors(int livingNeighbors) {
        this.livingNeighbors = livingNeighbors;
    }

    public void incrementLivingNeighbors(){
        livingNeighbors++;
    }

    public void decrementLivingNeighbors(){
        livingNeighbors--;
    }
}
