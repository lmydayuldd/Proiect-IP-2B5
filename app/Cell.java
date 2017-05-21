package app;

/**
 * 5/18/2017.
 */
public class Cell {
    /**
     * This encodes the walls of the current cell.
     * This variable takes values from 0 to 63, first 4 bits encoding representing the
     * four walls (North, East, West and South)
     * If the variable equals 7, then it means that around this cell there are 3 walls
     * 7 in base 2 is 1110
     * The walls that exist are N, E, W.
     * The last 2 bits encode the up/down accessibility.
     */
    private int walls;

    /**
     * If free = 1, then this cell belongs to the building. Otherwise, it is a cell outside the building.
     */
    private int free;

    public Cell(int walls, int free)
    {
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