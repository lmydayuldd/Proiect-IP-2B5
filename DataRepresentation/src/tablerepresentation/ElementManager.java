/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablerepresentation;

import CustomExceptions.DataNotValidException;
import Representations.*;
import java.util.ArrayList;
/**
 *
 * @author Procop Vladimir
 */
public class ElementManager {
    ArrayList<TableElement> elements = null;
    ArrayList<Wall> walls = null;
    ArrayList<Room> rooms = null;
    ArrayList<Floor> floors = null;
    
    public ElementManager(){
        this.elements = new ArrayList<>();
    }
    
    public ElementManager(ArrayList<TableElement> elements){
        this.elements = elements;
    }
    
    public void interpretElements() throws DataNotValidException{
        this.walls = this.getWalls();
        this.rooms = this.getRooms(this.walls);
        this.floors = this.getFloors(rooms);
    }
    
    public ArrayList<Wall> getWalls() throws DataNotValidException{
        ArrayList<Wall> walls = new ArrayList<>();
        
        TableElement element;
        for(int i = 0; i < this.elements.size(); i++){
            element = elements.get(i);
            if(element.elementType.equalsIgnoreCase("wall")){
                Wall newWall = new Wall(new Point(element.x1, element.y1) , new Point(element.x2, element.y2));
                newWall.setFloorNumber(element.floorNumber);
                newWall.setIsExterior(element.isExterior);
                newWall.setRoomNumber(element.room);
                walls.add(new Wall(new Point(element.x1, element.y1) , new Point(element.x2, element.y2)));
            }
            if(element.elementType.equalsIgnoreCase("door")){
                Wall newDoor = new Door(new Point(element.x1, element.y1) , new Point(element.x2, element.y2), element.isExit);
                newDoor.setFloorNumber(element.floorNumber);
                newDoor.setIsExterior(element.isExterior);
                newDoor.setRoomNumber(element.room);
                walls.add(new Door(new Point(element.x1, element.y1) , new Point(element.x2, element.y2), element.isExit));
            }
            if(element.elementType.equalsIgnoreCase("window")){
                Wall newWindow = new Window(new Point(element.x1, element.y1) , new Point(element.x2, element.y2));
                newWindow.setFloorNumber(element.floorNumber);
                newWindow.setIsExterior(element.isExterior);
                newWindow.setRoomNumber(element.room);
                walls.add(new Window(new Point(element.x1, element.y1) , new Point(element.x2, element.y2)));
            }
        }
        return walls;
    }
    
    public ArrayList<Room> getRooms(ArrayList<Wall> walls) throws DataNotValidException{
        ArrayList<Room> rooms = new ArrayList<>();
        Room newRoom;
        Wall wall;
        for(int i = 0; i < walls.size(); i++){
            wall = walls.get(i);
            if(insertIfRoomExists(rooms, wall)){
                continue;
            }
            else{
                newRoom = new Room(wall.getRoomNumber());
                newRoom.setRoomNumber(wall.getRoomNumber());
                newRoom.setFloorNumber(wall.getFloorNumber());
                rooms.add(newRoom);
            }
        }
        
        for(int i = 0; i < rooms.size(); i++){
            Room.validate(rooms.get(i));
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
                newFloor = new Floor();
                newFloor.setFloorLevel(room.getFloorNumber());
                floors.add(newFloor);
            }
        }
        //////////////// VALIDARI TESTE///////////////////////////////////////////////////
        for(int i = 0; i < floors.size (); i++){
            Floor.validate(floors.get(i));
        }
        return floors;
    }
    
    public boolean insertIfRoomExists(ArrayList<Room> rooms, Wall wall) throws DataNotValidException{
        
        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getRoomNumber() == wall.getRoomNumber()){
                rooms.get(i).addWall(wall);
                return true;
            }
        }
        return false;
    }
    
    public boolean insertIfFloorExists(ArrayList<Floor> floors, Room room) throws DataNotValidException{
        for(int i = 0; i < floors.size(); i++){
            if(floors.get(i).getFloorLevel() == room.getFloorNumber()){
                floors.get(i).addRoom(room);
                return true;
            }
        }
        return false;
    }
}
