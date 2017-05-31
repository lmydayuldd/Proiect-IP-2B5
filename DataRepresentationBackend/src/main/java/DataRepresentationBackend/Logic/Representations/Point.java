package DataRepresentationBackend.Logic.Representations;

import DataRepresentationBackend.Logic.CustomExceptions.DataNotValidException;
import DataRepresentationBackend.Logic.TableRepresentation.DataNotValidExceptionLogger;

/**
 * @author Procop Vladimir
 */
public class Point {
    private int x;
    private int y;

    public Point(int initx, int inity) throws DataNotValidException {/**Construct Point with coordinates x=initx and y=inity*/
        if ((x < 0) || (y < 0) || (x > 100) || (y > 100))
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("Tried to initialize point with negative or larger than 100 coordinates");
        this.x = initx;
        this.y = inity;
    }

    public Point(Point toCopy) {/**Construct a copy of the toCopy point*/
        this.x = toCopy.x;
        this.y = toCopy.y;
    }

    public int getX() {/**Return x-coordinate*/
        return x;
    }

    public void setX(int x) throws DataNotValidException {/**Set x to new value*/
        if (x < 0)
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("Tried to set x-coordinate to negative value");
        this.x = x;
    }

    public int getY() {/**Return y-coordinate*/
        return y;
    }

    public void setY(int y) throws DataNotValidException {/**Set y to new value*/
        if (x < 0)
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("Tried to set y-coordinate to negative value");
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Point)) return false;
        Point p = (Point) o;
        return (this.x == p.x) && (this.y == p.y);
    }

    public String toString() {
        return ("Point: x = " + this.x + ", y = " + this.y);
    }
}