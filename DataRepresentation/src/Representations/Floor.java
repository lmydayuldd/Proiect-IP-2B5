/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Representations;

import CustomExceptions.DataNotValidException;
import static Representations.Room.firstQuadrant;
import static Representations.Room.intersect;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.math.BigInteger;
import java.util.ArrayList;
import tablerepresentation.DataNotValidExceptionLogger;

/**
 *
 * @author Procop Vladimir
 */
public class Floor extends Element {
    private int floorLevel;
    private int floorHeight;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Wall> exteriorWalls = new ArrayList<>();
    public boolean hasStairs = false;
   /**Construct floor with floorHeight and floorLevel*/
    public Floor(int level){
        this.floorLevel = level;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof Floor)) return false;
        Floor f = (Floor)o; 
        if(f.getRooms().size()!=this.getRooms().size()) return false;
        if(this.floorHeight!=f.floorHeight) return false;
        if(this.floorLevel!=f.floorLevel) return false;
        for(Room thisRoom : this.getRooms()){
            boolean flag = false;
            for(Room otherRoom : f.getRooms()){
                if(thisRoom.equals(otherRoom)) flag = true;
            }
            if(flag == false) return false;
        }
        
        return true;
    }
    /**Add room to this floor's rooms*/
    public void addRoom(Room room) throws DataNotValidException{
        if(room.getFloorNumber()!=this.floorLevel){
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("Tried to insert onto Floor " + this.floorLevel + " a Room from floor " + room.getFloorNumber());
        }
        if(room.isStairwell()){
            this.hasStairs = true;
        }
        this.rooms.add(room);
    }
    /**Delete a room from this floor*/
    public void deleteRoom(Room room)throws DataNotValidException{
        for(int i = 0 ; i < this.rooms.size(); i++){
            if((rooms.get(i).getFloorNumber() == room.getFloorNumber()) && (rooms.get(i).getRoomName().equalsIgnoreCase(room.getRoomName()))){
                rooms.remove(i);
                
            }
        }
    }
    
    public int getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(int floorLevel){
        this.floorLevel = floorLevel;
    }

    public int getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(int floorHeight) throws DataNotValidException{
        if(floorHeight<2) DataNotValidExceptionLogger.getInstance().addExceptionMessage("Floor height must be >=2");
        this.floorHeight = floorHeight;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> room)throws DataNotValidException {//la fel ca la Room ....
        this.rooms = room;
    }
    /**Return true if the Floor is valid. Otherwise it throws DataNotValidException*/
    public static boolean validate(Floor floor) throws DataNotValidException{
        Path2D room1AsPath = null;
        Path2D room2AsPath = null;
        Area room1AsArea = null;
        Area room2AsArea = null;
        Area room2Temp = null;
        int i = 0;    
        
        if(floor.rooms.size()==0){
            Floor.validateExterior(floor);
            return true;
        }
        
        for(i = 0 ; i < floor.rooms.size()-1; i++){
            floor.exteriorWalls.addAll(floor.rooms.get(i).getExteriorWalls());
            Room.validate(floor.rooms.get(i));
                                 
            room1AsPath = Room.toPath2D(floor.rooms.get(i));
            room1AsArea = new Area(room1AsPath);
            for(int j = i+1  ; j  < floor.rooms.size(); j++){
                room2AsPath = Room.toPath2D(floor.rooms.get(j));
                room2AsArea = new Area(room2AsPath);
                room2Temp = new Area(room1AsPath);
                room2Temp.intersect(room2AsArea);
                if(!room2Temp.isEmpty()){
                    DataNotValidExceptionLogger.getInstance().addExceptionMessage("Rooms [" + floor.rooms.get(i) + "]  AND  [" + floor.rooms.get(j) + "] overlap");
                }
            }
        }        
        Room.validate(floor.rooms.get(floor.rooms.size()-1));
        floor.exteriorWalls.addAll(floor.rooms.get(i).getExteriorWalls());
        
        Floor.validateExterior(floor);
        
        return true;
    }
    /**Returns true is the Exterior layout of the floor is valid. Otherwise it adds Error message to log*/
    static public boolean validateExterior(Floor floor)throws DataNotValidException{
        ArrayList<Wall> exterior = floor.exteriorWalls;
        if((exterior==null) || (exterior.size()< 3)){
            throw new DataNotValidException("Exterior of floor " + floor.floorLevel + " is not a polygon");
        }
        boolean githubedecacat = true;
        boolean door = false;
        int[] neighbors = new int[exterior.size()]; 
        for(int i = 0; i < exterior.size(); i++){
            neighbors[i] = 0;
        }
        
        for(int i = 0; i < exterior.size()-1; i++){
//            System.out.println("DEBUG Floor.validateExterior: " + exterior.get(i));
            if(!Room.firstQuadrant(exterior.get(i))){
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("["+exterior.get(i).toString()+"]" + "] is not entirely in the right upper quadrant !");
            }
            if(exterior.get(i) instanceof Door){
                door = true;
            }
            
            for(int j = i+1; j < exterior.size(); j++){
                if(exterior.get(i).equals(exterior.get(j))){
                    DataNotValidExceptionLogger.getInstance().addExceptionMessage("Wall " + exterior.get(i) + " has been inserted twice for the same room !");
                }
                if(Room.oneCommonExtremity(exterior.get(i), exterior.get(j))){
                    neighbors[i]++; 
                    neighbors[j]++;
                    continue;
                }
                else if(intersect(exterior.get(i), exterior.get(j))){
                    DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" + exterior.get(i) + "]" + " AND " + "[" + exterior.get(j) + "]" + " intersect each other in inner point !");
                }
            }
        }
//        System.out.println("DEBUG Floor.validateExterior: " + exterior.get(exterior.size()-1));
        for(int k = 0 ; k < neighbors.length; k++){
                if(neighbors[k]!=2) {
                        DataNotValidExceptionLogger.getInstance().addExceptionMessage("["+exterior.get(k)+"]" + " has " + neighbors[k] + " neighbours, NOT 2 !");
                }    
        }
        
        if(exterior.get(exterior.size()-1) instanceof Door){
            door = true;
        }
        if(!firstQuadrant(exterior.get(exterior.size()-1))){
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("["+exterior.get(exterior.size()-1).toString()+"] is not entirely in the right upper quadrant !");
            }
        
        if((!door) && (floor.floorLevel==0)) {
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("There is no door on the first floor " + exterior.get(0).getFloorNumber());
        }
        
        
        Area exteriorAsArea = new Area(Floor.wallListAsPath2D(exterior));
        Path2D roomAsPath2D;
        Area roomAsArea;
        Area roomTemp;
        
        for(int j = 0 ; j< exterior.size(); j++){  /// DEBUG
                System.out.println(exterior.get(j));   
            }
        
        for(int i = 0 ; i < floor.rooms.size(); i++){
            roomTemp = new Area(Room.toPath2D(floor.rooms.get(i)));
            roomTemp.intersect(exteriorAsArea);
            if(!roomTemp.equals(new Area(Room.toPath2D(floor.rooms.get(i))))){
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" + floor.rooms.get(i) + "] is not contained by it's floor , " + floor.rooms.get(i).getFloorNumber());
                System.out.println("~~~~~~~~~~~~~~ARIE INTERSECTIE~~~~~~~~~~~~~~~~~~~~~~~~DEBUG"); float[] coords = new float[2];
                for(PathIterator it = roomTemp.getPathIterator(null); !it.isDone(); it.next()){
                    it.currentSegment(coords);
                    System.out.println("punct cale intersectie " + coords[0] + " , " + coords[1]);
                }
                System.out.println("~~~~~~~~~~~~~~GATA~~~~~~~~~~~~~~~~~~~~~~~~");
            } 
        }
        return true;
    }
    /**Adds an exterior Wall to the Floor*/
    public void addExteriorWall(Wall wall){
        this.exteriorWalls.add(wall);
    }
    /**Returns a Path2D object, equivalent to the parameter*/
    public static Path2D wallListAsPath2D(ArrayList<Wall> walls){
        Path2D path = new Path2D.Double();
        for(int i = 0; i < walls.size(); i++){
            path.append(Wall.toLine2D(walls.get(i)), true);
        }
        return path;
    }
    public String toString(){
        String ans = "FLOOR " + this.floorLevel + " : " + '\n';
        ans += "---------------------- EXTERIOR WALLS -----------------------------------------------------------------------------------------------------------------------------------------------------" + '\n';
        for(int i = 0; i < this.exteriorWalls.size(); i++){
            ans = ans  + exteriorWalls.get(i) + "  ,  ";
        }
        ans += '\n';
        ans += "------------------------- ROOMS --------------------------------------------------------------------------------------------------------------------------------------------------------------" + '\n';
        for(int i = 0; i < this.rooms.size(); i++){
            ans = ans  + rooms.get(i) + '\n';
        }
        return ans;
    }
    
    public ArrayList<Wall> getExteriorWalls(){
        return this.exteriorWalls;
    }
}
