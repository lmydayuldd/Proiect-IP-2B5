package DataRepresentationBackend.Logic.TableRepresentation;

import DataRepresentationBackend.Logic.CustomExceptions.DataNotValidException;
import DataRepresentationBackend.Logic.Representations.Floor;
import DataRepresentationBackend.Logic.Representations.Room;
import DataRepresentationBackend.Logic.Representations.Stairs3D;
import DataRepresentationBackend.Logic.Representations.Wall;

import java.util.ArrayList;

/**
 * @author Procop Vladimir
 */
public class ElementManager {
    /**
     * Class that manages the elements in the TEMPORARY_DATA table. Contains methods for validating or rejecting the building representation from the table
     */
    ArrayList<TableElement> elements = null;
    ArrayList<Wall> walls = null;
    ArrayList<Room> rooms = null;
    ArrayList<Floor> floors = null;
    ArrayList<Stairs3D> stairs3D = null;
    ArrayList<Room> stairs = null;
    ElementFilter filter = null;

    /**
     * Constructor. No elements and no exterior walls.
     */
    public ElementManager() {
        this.elements = new ArrayList<>();
        this.filter = new ElementFilter(this.elements);
    }

    /**
     * Constructor. The parameter becomes the manager's set of TableElements.
     */
    public ElementManager(ArrayList<TableElement> elements) {
        this.elements = elements;
        this.filter = new ElementFilter(this.elements);
    }

    public void addElement(TableElement te) {/**Adds an element to the set of TableElements*/
        this.elements.add(te);
    }

    /**
     * Return true if the specified set of TableElements constitutes a valid building. Otherwise it throws DataNotValidException
     */
    public boolean validateElements() throws DataNotValidException {
        DataNotValidExceptionLogger.clearLog();
        this.walls = this.filter.getWalls();
        this.rooms = this.filter.getRooms(this.walls);
        this.floors = this.filter.getFloors(rooms);
        this.stairs = this.filter.getStairsRooms();
        this.stairs3D = this.filter.getStairs3D();

//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        for(int i = 0 ; i < floors.size(); i++){
//            System.out.println(floors.get(i));
//        }
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        if (!DataNotValidExceptionLogger.getExceptionMessage().isEmpty()) {
            throw new DataNotValidException(DataNotValidExceptionLogger.getExceptionMessageAsString());
        }
        return true;
    }
}