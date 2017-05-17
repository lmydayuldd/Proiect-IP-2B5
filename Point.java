package modul3;

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

}
