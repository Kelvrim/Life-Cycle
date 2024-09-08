public class Main {
    public static void main(String[] args) {
        int width = 10;     // Width and height for the grid
        int height = 10;
        int time = 5;       // How many 'turns' we play for

        // TODO: find a better way to name this monstrosity
        Grid grid = new Grid(width, height);
        Cell[][] gridArray = grid.getGrid();

        System.out.println("GRID First generation:");
        GridDisplay.printGrid(gridArray);

        while (time != 0){
            grid.nextGeneration(gridArray);
            time--;
        }
    }
}