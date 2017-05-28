/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablerepresentation;

import CustomExceptions.DataNotValidException;
import Representations.Door;
import Representations.Floor;
import Representations.Point;
import Representations.Room;
import Representations.Stairs;
import Representations.Stairs3D;
import Representations.Wall;
import Representations.Window;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Procop Vladimir
 */
public class ElementFilter {

    private ArrayList<TableElement> elements = null;
    private ArrayList<Wall> exteriorWallsNoRoom = null;
    private ArrayList<Room> stairsRooms = null;
    private ArrayList<Stairs3D> stairs3D = null;
    
    public ElementFilter(ArrayList<TableElement> elements){
        this.elements = elements;
        this.exteriorWallsNoRoom = new ArrayList<>();
        this.stairsRooms = new ArrayList<Room>();
    }
    
    public ArrayList<Wall> getWalls() throws DataNotValidException{/**Filters from the set of TableElements those which are Walls*/
        ArrayList<Wall> walls = new ArrayList<>();
        
        
        TableElement element;
        for(int i = 0; i < this.elements.size(); i++){
            element = elements.get(i);
            
            if((element.isExit == 1) && (element.isExterior == 0)) DataNotValidExceptionLogger.getInstance().addExceptionMessage(element + " is an Exit but it is not Exterior !");
            if((element.isExit == 1) && (!element.elementType.equalsIgnoreCase("door"))) DataNotValidExceptionLogger.getInstance().addExceptionMessage(element + " is an Exit but it is not a Door");
            if(element.elementType.equalsIgnoreCase("wall")){
                Wall newWall = new Wall(new Point(element.x1, element.y1) , new Point(element.x2, element.y2));
                newWall.setFloorNumber(element.floorNumber);
                newWall.setRoomName(element.room);
                newWall.setIsExterior(element.isExterior);
                
                if(element.room.equalsIgnoreCase("-1")){
                    if(element.isExterior != 1){
                        DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" +  element + "] must be Exterior because room = -1 ");
                    }
                    this.exteriorWallsNoRoom.add(newWall);
                    continue;
                }
                walls.add(newWall);
            }
            if(element.elementType.equalsIgnoreCase("door")){
                Wall newDoor = new Door(new Point(element.x1, element.y1) , new Point(element.x2, element.y2), element.isExit);
                newDoor.setFloorNumber(element.floorNumber);
                newDoor.setIsExterior(element.isExterior);
                newDoor.setRoomName(element.room);
                
                if(element.room.equalsIgnoreCase("-1")){
                    if(element.isExterior != 1){
                        DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" +  element + "] must be Exterior because room = -1 ");
                    }
                    this.exteriorWallsNoRoom.add(newDoor);
                    continue;
                }
                walls.add(newDoor);
            }
            if(element.elementType.equalsIgnoreCase("window")){
                Wall newWindow = new Window(new Point(element.x1, element.y1) , new Point(element.x2, element.y2));
                newWindow.setFloorNumber(element.floorNumber);
                newWindow.setIsExterior(element.isExterior);
                newWindow.setRoomName(element.room);
                
                if(element.room.equalsIgnoreCase("-1")){
                    if(element.isExterior != 1){
                        DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" +  element + "] must be Exterior because room = -1 ");
                    }
                    this.exteriorWallsNoRoom.add(newWindow);
                    continue;
                }
                walls.add(newWindow);
            }
            if(element.elementType.equalsIgnoreCase("stairs")){
                Wall newStairs = new Stairs(new Point(element.x1, element.y1) , new Point(element.x2, element.y2));
                newStairs.setFloorNumber(element.floorNumber);
                newStairs.setIsExterior(element.isExterior);
                newStairs.setRoomName(element.room);
                walls.add(newStairs);
            }
        }
        return walls;
    }
    
    public ArrayList<Room> getRooms(ArrayList<Wall> walls) throws DataNotValidException{/**Filters from the set of Walls the set of Rooms in the building*/
        ArrayList<Room> rooms = new ArrayList<>();
        Room newRoom;
        Wall wall;
        for(int i = 0; i < walls.size(); i++){
            wall = walls.get(i);
            if(insertIfRoomExists(rooms, wall)){
                continue;
            }
            else{
                newRoom = new Room(wall.getRoomName(), wall.getFloorNumber());
                newRoom.addWall(wall);
                rooms.add(newRoom);
            }
        }
        
        for(int i = 0; i < rooms.size(); i++){
            Room.validate(rooms.get(i));
            if(rooms.get(i).isStairwell()){
                this.stairsRooms.add(rooms.get(i));
            }
        }
        return rooms;
    }
    
    public ArrayList<Floor> getFloors(ArrayList<Room> rooms) throws DataNotValidException{
        ArrayList<Floor> floors = new ArrayList<>();
        Room room;
        Floor newFloor;
        for(int i = 0; i < rooms.size(); i++){
            room = rooms.get(i);
            if(insertIfFloorExists(floors, room)){
                continue; // verificare de etaj se face in room.addWall(Wall);
            }
            else{
                newFloor = new Floor(room.getFloorNumber());
                newFloor.addRoom(room);
                floors.add(newFloor);
            }
        }
        Wall wall = null;
        for(int i = 0; i < this.exteriorWallsNoRoom.size(); i++){
            wall = exteriorWallsNoRoom.get(i);
            if(addToExteriorIfFloorExists(floors, wall)){
                continue; // verificare de etaj se face in room.addWall(Wall);
            }
            else{
                newFloor = new Floor(wall.getFloorNumber());
                newFloor.addExteriorWall(wall);
                floors.add(newFloor);
            }
        }
            //////////////// VALIDARI TESTE///////////////////////////////////////////////////
        Collections.sort(floors, (f1, f2) -> {
            return (f1.getFloorLevel()-f2.getFloorLevel());
        });
        for(int i = 0; i < floors.size (); i++){
            Floor.validate(floors.get(i));
            if((i>0)&&(floors.get(i).getFloorLevel()-floors.get(i-1).getFloorLevel() != 1)){
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("From Floor " + floors.get(i-1).getFloorLevel() + " you jump directly to Floor " + floors.get(i).getFloorLevel());
            }
            if((!floors.get(i).hasStairs) && (floors.get(i).getFloorLevel()!=0)){
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("Floor " + floors.get(i).getFloorLevel() + " is not reached by any stairs");
            }
        }
        return floors;
    }
    
    public boolean insertIfRoomExists(ArrayList<Room> rooms, Wall wall) throws DataNotValidException{/**Tries to insert the Wall parameter in one of the existing Rooms. If it succeeds it returns true. Otherwise it returns false*/
        for(int i = 0; i < rooms.size(); i++){
            if((rooms.get(i).getRoomName().equalsIgnoreCase(wall.getRoomName())) && (rooms.get(i).getFloorNumber() == wall.getFloorNumber())){
                rooms.get(i).addWall(wall);
                return true;
            }
        }
        return false;
    }
    
    public boolean insertIfFloorExists(ArrayList<Floor> floors, Room room) throws DataNotValidException{/**Tries to insert the Room parameter in one of the existing Floors. If it succeeds it returns true. Otherwise it returns false*/
        for(int i = 0; i < floors.size(); i++){
            if(floors.get(i).getFloorLevel() == room.getFloorNumber()){
                floors.get(i).addRoom(room);
                return true;
            }
        }
        return false;
    }
    
    public boolean addToExteriorIfFloorExists(ArrayList<Floor> floors, Wall wall){/**Tries to insert the Wall parameter in one of the existing Floors as exterior Wall. If it succeeds it returns true. Otherwise it returns false*/
        for(int i = 0; i < floors.size(); i++){
            if(floors.get(i).getFloorLevel() == wall.getFloorNumber()){
                floors.get(i).addExteriorWall(wall);
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Stairs3D> getStairs3D() throws DataNotValidException{
        ArrayList<Stairs3D> stairs3D = new ArrayList<Stairs3D>();
        Room stairs2D = null; Stairs3D newStairs3D = null;
        for(int i = 0; i < stairsRooms.size(); i++){
            stairs2D = stairsRooms.get(i);
            if(insertIfStairsExist(stairs3D, stairs2D)){
                continue; // verificare de etaj se face in room.addWall(Wall);
            }
            else{
                newStairs3D = new Stairs3D(stairs2D.getRoomName());
                newStairs3D.addStairs2D(stairs2D);
                stairs3D.add(newStairs3D);
            }
        }
        for(int i = 0; i < stairs3D.size (); i++){
            Stairs3D.validate(stairs3D.get(i));///////////////////////////  <--- LOOK AT IT <--------
        }
        return stairs3D;
    }
    
    public boolean insertIfStairsExist(ArrayList<Stairs3D> stairs3D, Room stair) throws DataNotValidException{/**Tries to insert the Room parameter in one of the existing Floors. If it succeeds it returns true. Otherwise it returns false*/
        for(int i = 0; i < stairs3D.size(); i++){
            if(stairs3D.get(i).getStairs3DName() == stair.getRoomName()){
                stairs3D.get(i).addStairs2D(stair);
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Room> getStairsRooms(){
        return this.stairsRooms;
    }
}
