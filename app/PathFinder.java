package app;

import java.util.ArrayList;
import java.util.Vector;


/**
 * An abstract class that models the class that calculates the path between
 * two points in the building.
 */
public abstract class PathFinder {

    Matrix matrix;

    PathFinder(Matrix m) {
        matrix = m;
    }

    public void setMatrix(Matrix m) {
        matrix = m;
    }

    /**
     * This class calculates the route in the building that travels through the
     * points described by the @param path list.
     * @param source source point
     * @param dest dest point
     * @return Array list of Points
     */
    public abstract ArrayList<Point> execute(Point source, Point dest);

}