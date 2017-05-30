package app;


import java.util.Vector;


/**
 * This class represents the building as a 3D-Matrix for calculating
 * the path between two points in the building.
 * The first dimension of the 3D table represents the floor
 * The last two dimensions represent the coordinates of the cell in the floor
 * Each floor is mapped in a Cartesian system xOy.
 * matrix[k][i][j] represents the cell located at floor k
 * with coordinates (i, j).
 */
public class Matrix {

    static final int DIMENSION = 1000;
    static final int LEVEL_COUNT = 10;

    private Cell[][][] matrix;

    private PathFinder pathFinder;

    public Matrix() {
        pathFinder = new SimplePath(this);
        matrix = new Cell[LEVEL_COUNT][DIMENSION][DIMENSION];
    }

    public void setCell(Cell cell, int x, int y, int z) {
        matrix[z][x][y] = cell;
    }

    public Cell getCell(int x, int y, int z) {
        return matrix[z][x][y];
    }

    public Cell getCell(Point p) {
        return matrix[p.getFloor()][p.getX()][p.getY()];
    }

    /**
     * Sets the pathfinder.
     *
     * @param pf
     */
    public void setPathFinder(PathFinder pf) {
        pathFinder = pf;
    }


}
