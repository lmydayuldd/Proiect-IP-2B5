package DataRepresentationBackend.Logic.Representations;

import DataRepresentationBackend.Logic.CustomExceptions.DataNotValidException;
import DataRepresentationBackend.Logic.TableRepresentation.DataNotValidExceptionLogger;

import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import static DataRepresentationBackend.Logic.Representations.Room.firstQuadrant;
import static DataRepresentationBackend.Logic.Representations.Room.intersect;

/**
 * @author Procop Vladimir
 */
public class Floor extends Element {
    private int floorLevel;
    private int floorHeight;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Wall> exteriorWalls = new ArrayList<>();
    public boolean hasStairs = false;

    public Floor(int level) {/**Construct floor with floorHeight and floorLevel*/
        this.floorLevel = level;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Floor)) return false;
        Floor f = (Floor) o;
        if (f.getRooms().size() != this.getRooms().size()) return false;
        if (this.floorHeight != f.floorHeight) return false;
        if (this.floorLevel != f.floorLevel) return false;
        for (Room thisRoom : this.getRooms()) {
            boolean flag = false;
            for (Room otherRoom : f.getRooms()) {
                if (thisRoom.equals(otherRoom)) flag = true;
            }
            if (flag == false) return false;
        }

        return true;
    }

    public void addRoom(Room room) throws DataNotValidException {/**Add room to this floor's rooms*/
        if (room.getFloorNumber() != this.floorLevel) {
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("Tried to insert onto Floor " + this.floorLevel + " a Room from floor " + room.getFloorNumber());
        }
        if (room.isStairwell()) {
            this.hasStairs = true;
        }
        this.rooms.add(room);
    }

    public void deleteRoom(Room room) throws DataNotValidException {/**Delete a room from this floor*/
        for (int i = 0; i < this.rooms.size(); i++) {
            if ((rooms.get(i).getFloorNumber() == room.getFloorNumber()) && (rooms.get(i).getRoomName().equalsIgnoreCase(room.getRoomName()))) {
                rooms.remove(i);

            }
        }
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    public int getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(int floorHeight) throws DataNotValidException {
        if (floorHeight < 2) DataNotValidExceptionLogger.getInstance().addExceptionMessage("Floor height must be >=2");
        this.floorHeight = floorHeight;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> room) throws DataNotValidException {//la fel ca la Room ....
        this.rooms = room;
    }

    public static boolean validate(Floor floor) throws DataNotValidException {/**Return true if the Floor is valid. Otherwise it throws DataNotValidException*/
        Path2D room1AsPath = null;
        Path2D room2AsPath = null;
        Area room1AsArea = null;
        Area room2AsArea = null;
        Area room2Temp = null;
        int i = 0;
        for (i = 0; i < floor.rooms.size() - 1; i++) {
            floor.exteriorWalls.addAll(floor.rooms.get(i).getExteriorWalls());
            Room.validate(floor.rooms.get(i));

            room1AsPath = Room.toPath2D(floor.rooms.get(i));
            room1AsArea = new Area(room1AsPath);
            for (int j = i + 1; j < floor.rooms.size(); j++) {
                room2AsPath = Room.toPath2D(floor.rooms.get(j));
                room2AsArea = new Area(room2AsPath);
                room2Temp = new Area(room1AsPath);
                room2Temp.intersect(room2AsArea);
                if (!room2Temp.isEmpty()) {
                    DataNotValidExceptionLogger.getInstance().addExceptionMessage("Rooms [" + floor.rooms.get(i) + "]  AND  [" + floor.rooms.get(j) + "] overlap");
                }
            }
        }
        Room.validate(floor.rooms.get(floor.rooms.size() - 1));
        floor.exteriorWalls.addAll(floor.rooms.get(i).getExteriorWalls());

        Floor.validateExterior(floor);

        return true;
    }

    static public boolean validateExterior(Floor floor) throws DataNotValidException {/**Returns true is the Exterior layout of the floor is valid. Otherwise it throws DataNotValidException*/
        ArrayList<Wall> exterior = floor.exteriorWalls;
        if ((exterior == null) || (exterior.size() < 3)) {
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("Exterior of " + exterior.get(0).floorNumber + " must have at least 3 sides !");
        }
        boolean door = false;
        int[] neighbors = new int[exterior.size()];
        for (int i = 0; i < exterior.size(); i++) {
            neighbors[i] = 0;
        }

        for (int i = 0; i < exterior.size() - 1; i++) {
//            System.out.println("DEBUG Floor.validateExterior: " + exterior.get(i));
            if (!firstQuadrant(exterior.get(i))) {
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" + exterior.get(i).toString() + "]" + "] is not entirely in the right upper quadrant !");
            }
            if (exterior.get(i) instanceof Door) {
                door = true;
            }

            for (int j = i + 1; j < exterior.size(); j++) {
                if (exterior.get(i).equals(exterior.get(j))) {
                    DataNotValidExceptionLogger.getInstance().addExceptionMessage("Wall " + exterior.get(i) + " has been inserted twice for the same room !");
                }
                if (Room.oneCommonExtremity(exterior.get(i), exterior.get(j))) {
                    neighbors[i]++;
                    neighbors[j]++;
                    continue;
                } else if (intersect(exterior.get(i), exterior.get(j))) {
                    DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" + exterior.get(i) + "]" + " AND " + "[" + exterior.get(j) + "]" + " intersect each other in inner point !");
                }
            }
        }
//        System.out.println("DEBUG Floor.validateExterior: " + exterior.get(exterior.size()-1));
        for (int k = 0; k < neighbors.length; k++) {
            if (neighbors[k] != 2) {
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" + exterior.get(k) + "]" + " has " + neighbors[k] + " neighbours, NOT 2 !");
            }
        }

        if (exterior.get(exterior.size() - 1) instanceof Door) {
            door = true;
        }
        if (!firstQuadrant(exterior.get(exterior.size() - 1))) {
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" + exterior.get(exterior.size() - 1).toString() + "] is not entirely in the right upper quadrant !");
        }

        if ((!door) && (floor.floorLevel == 0)) {
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("There is no door on the first floor " + exterior.get(0).getFloorNumber());
        }


        Area exteriorAsArea = new Area(Floor.wallListAsPath2D(exterior));
        Path2D roomAsPath2D;
        Area roomAsArea;
        Area roomTemp;
        for (int i = 0; i < floor.rooms.size(); i++) {
            roomTemp = new Area(Room.toPath2D(floor.rooms.get(i)));
            roomTemp.intersect(exteriorAsArea);
            if (!roomTemp.equals(new Area(Room.toPath2D(floor.rooms.get(i))))) {
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" + floor.rooms.get(i) + "] is not contained by it's floor , " + floor.rooms.get(i).getFloorNumber());
            }
        }
        return true;
    }

    public void addExteriorWall(Wall wall) {/**Adds an exterior Wall to the Floor*/
        this.exteriorWalls.add(wall);
    }

    public static Path2D wallListAsPath2D(ArrayList<Wall> walls) {/**Returns a Path2D object, equivalent to the parameter*/
        Path2D path = new Path2D.Double();
        for (int i = 0; i < walls.size(); i++) {
            path.append(Wall.toLine2D(walls.get(i)), true);
        }
        return path;
    }

    public String toString() {
        String ans = "FLOOR " + this.floorLevel + " : " + '\n';
        ans += "---------------------- EXTERIOR WALLS -----------------------------------------------------------------------------------------------------------------------------------------------------" + '\n';
        for (int i = 0; i < this.exteriorWalls.size(); i++) {
            ans = ans + exteriorWalls.get(i) + "  ,  ";
        }
        ans += '\n';
        ans += "------------------------- ROOMS --------------------------------------------------------------------------------------------------------------------------------------------------------------" + '\n';
        for (int i = 0; i < this.rooms.size(); i++) {
            ans = ans + rooms.get(i) + '\n';
        }
        return ans;
    }
}