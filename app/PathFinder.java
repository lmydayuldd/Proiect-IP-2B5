package app;

import java.util.ArrayList;


/**
 * An abstract class that models the class that calculates the path between
 * two points in the building.
 */
public abstract class PathFinder {

    Matrix matrix;

    public PathFinder(Matrix m) {
        matrix = m;
    }

    public void setMatrix(Matrix m) {
        matrix = m;
    }

    public abstract Integer execute(Point source, Point dest);


    /**
     * This class calculates the route in the building that travels through the
     * points described by the @param path list.
     */
    public abstract void execute(ArrayList<Point> path);

}