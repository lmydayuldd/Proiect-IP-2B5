package app;

/**
 *
 */
public class Point {

    /**
     * The coordinates of a cell in matrix.
     */
    private int x, y, floor;//

    public Point(int x, int y, int floor) {
        this.x = x;
        this.y = y;
        this.floor = floor;
    }

    public int getX() {
        return x;
    }

    public int getFloor() {
        return floor;
    }

    public int getY() {
        return y;
    }

    boolean isValid(Integer lvl, Integer n, Integer m) {
        return !(floor < 0 || floor >= lvl) && !(x < 0 || x >= n) && !(y < 0 || y >= m);
    }

}
