package modul3;

import java.util.Vector;


/**
 * This is the app controller. Every request comming from ModelBuilder is comming here.
 */
public class Engine extends RequestHandler {

    public PathFinder pf;
    public ModelGenerator mg;
    public Matrix mat;

    public Vector myModelGenerator;
    public Vector myMatrix;
    public Vector myPathFinder;

}