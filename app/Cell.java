package app;

/**
 * 5/18/2017.
 */
public class Cell {
    /**
     * This encodes the walls of the current cell.
     * This variable takes values from 0 to 63, 
     * 2 ^ 0 -> if can't go to level - 1
     * 2 ^ 1 -> if can't go to level + 1
     * 2 ^ 2 -> if can't go to y - 1
     * 2 ^ 3 -> if can't go to x - 1
     * 2 ^ 4 -> if can't go to x + 1
     * 2 ^ 5 -> if can't go to y + 1
     */
    private int walls;

    /**
     * If free = 1, then this cell belongs to the building. Otherwise, it is a cell outside the building.
     */
    private int free;

    public Cell(int walls, int free) {
        this.walls = walls;
        this.free = free;
    }

    public int getFree() {
        return free;
    }

    public int getWalls() {
        return walls;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public void setWalls(int walls) {
        this.walls = walls;
    }

}