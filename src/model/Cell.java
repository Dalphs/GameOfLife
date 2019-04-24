package model;

/**
 * This class is responsible for the game logic concerning wether a cell is alive or dead
 * corresponding with the number of livingNeighbors which is set by the gameClass
 */
public class Cell {
    int livingNeighbors;
    boolean alive;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    /**
     * Update wether the Cell is alive og dead using the livingNeighbors variable
     */
    public void update(){
        //If the cell is alive and has 0-1 living neighbors or more than 3 neighbors the cell dies
        if ((livingNeighbors < 2 || livingNeighbors > 3) && alive){
            alive = false;
            //If the cell is dead and has 3 neighbors exactly i becomes alive
        } else if (livingNeighbors == 3 && !alive){
            alive = true;
        }
        //In all other cases the cell remains in the same state
    }

    public void setLivingNeighbors(int livingNeighbors) {
        this.livingNeighbors = livingNeighbors;
    }

    public boolean isAlive() {
        return alive;
    }
}
