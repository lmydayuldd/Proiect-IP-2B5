package app;

/**
 *
 */
class Point {

    /**
     * The coordinates of a cell in matrix.
     */
    private int x, y, floor;//

    Point(int x, int y, int floor) {
        this.x = x;
        this.y = y;
        this.floor = floor;
    }

    int getX() {
        return x;
    }

    int getFloor() {
        return floor;
    }

    int getY() {
        return y;
    }

    boolean isValid(Integer lvl, Integer n, Integer m) {
        return !(floor < 0 || floor >= lvl) && !(x < 0 || x >= n) && !(y < 0 || y >= m);
    }

}
