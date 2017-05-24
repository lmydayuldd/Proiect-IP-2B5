/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Representations;

import CustomExceptions.DataNotValidException;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import tablerepresentation.DataNotValidExceptionLogger;

/**
 *
 * @author Procop Vladimir
 */
public class Room extends Element{

    private ArrayList<Wall> walls = new ArrayList<>();
    private String roomName;
    private int floorNumber;
    private boolean isStairwell = false;
    
    public Room(String name, int floorNumber){/**Construct a room with a name*/
        this.roomName = name;
        this.floorNumber = floorNumber;
    }
    public Room(ArrayList<Wall> walls){/**Constructs Room with Walls*/
        this.walls = walls;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof Room)) return false;
        Room r = (Room)o; 
        if(r.getWalls().size()!=this.getWalls().size()) return false;
        if(r.floorNumber != this.floorNumber) return false;
        for(Wall thisWall : this.getWalls()){
            boolean flag = false;
            for(Wall otherWall : r.getWalls()){
                if(thisWall.equals(otherWall)) flag = true;
            }
            if(flag == false) return false;
        }
        
        return true;
    }
    
    public void addWall(Wall wall) throws DataNotValidException{/**Add a wall to this room*/
        if(wall.getFloorNumber()!=this.getFloorNumber()){
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("Tried to insert into [" + this.toString() + "] a wall from a different floor !");
        }
        if(!wall.getRoomName().equalsIgnoreCase(this.roomName)){
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("Tried to insert into [" + this.toString() + "] a wall from a different room !");
        }
        if(wall.isStairs){
            this.isStairwell = true;
        }
        this.walls.add(wall);
    }
    
    public void deleteWall(Wall wall)throws DataNotValidException{/**Delete a wall from this room*/
        this.walls.remove(wall);
    }
    
    @Override
    public String toString(){
        String ans = "Room " + this.getRoomName() + " : "; int i = 0;
        for(i = 0; i < walls.size()-1; i++){
            ans = ans +  i + " -> "  + walls.get(i).toString() + " , ";
        }
        ans = ans + i + " -> " + walls.get(walls.size()-1).toString() + '\n';
        return ans + ". Floor: " + this.floorNumber;
    }
    
    public ArrayList<Wall> getWalls() {/**Returns the room's walls*/
        return walls;
    }

    public void setWalls(ArrayList<Wall> walls) { 
        this.walls = walls;
    }
    
    public static boolean validate(Room room)throws DataNotValidException{/**True if argument is a valid room, false otherwise*/
        ArrayList<Wall> walls = room.walls;
        if((walls==null) || (walls.size()< 3)){
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("A Room must have at least 3 walls in order to be valid !");
        }
        boolean door = false;
        int[] neighbors = new int[walls.size()]; 
        for(int i = 0; i < walls.size(); i++){
            neighbors[i] = 0;
        }
        for(int i = 0; i < walls.size()-1; i++){
            if(!firstQuadrant(walls.get(i))){
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("["+walls.get(i).toString()+"] is not entirely in the right upper quadrant !");
            }
            if(walls.get(i) instanceof Door){
                door = true;
            }
            if((!walls.get(i).isStairs) && room.isStairwell){
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("["+walls.get(i).toString()+"] is not of type Stairs but it is specified as part of a Stairwell");
            }
            for(int j = i+1; j < walls.size(); j++){
                if(walls.get(i).getFloorNumber() != walls.get(j).getFloorNumber()){
                    DataNotValidExceptionLogger.getInstance().addExceptionMessage("In [" + room.toString() + "] walls from different floors have been inserted !" );
                }
                if(!walls.get(i).getRoomName().equalsIgnoreCase(walls.get(j).getRoomName())){
                    DataNotValidExceptionLogger.getInstance().addExceptionMessage("In [" + room.getRoomName() + "] walls from different rooms have been inserted !" );
                }
                if(walls.get(i).equals(walls.get(j))){
                    DataNotValidExceptionLogger.getInstance().addExceptionMessage("Wall " + walls.get(i) + " has been inserted twice for the same room !");
                }
                if(oneCommonExtremity(walls.get(i), walls.get(j))){
                    neighbors[i]++; 
                    neighbors[j]++;
                    continue;
                }
                else if(intersect(walls.get(i), walls.get(j))){
                    DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" + walls.get(i) + "]" + " AND " + "[" + walls.get(j) + "]" + " intersect each other in inner point !");
                }
            }
        }
        
        for(int k = 0 ; k < neighbors.length; k++){
                if(neighbors[k]!=2) {
                        DataNotValidExceptionLogger.getInstance().addExceptionMessage("["+walls.get(k)+"]" + " has " + neighbors[k] + " neighbours, NOT 2 ");
                }    
        }
        
        if(walls.get(walls.size()-1) instanceof Door){
            door = true;
        }
        if(!firstQuadrant(walls.get(walls.size()-1))){
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("["+walls.get(walls.size()-1).toString()+"]" + " is not entirely in the right upper quadrant !");
        }
        if((!walls.get(walls.size()-1).isStairs) && room.isStairwell){
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("["+walls.get(walls.size()-1).toString()+"] is not of type Stairs but it is specified as part of a Stairwell");
            }
        
        if(!door) {
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("In Room " + walls.get(0).getRoomName() + " , Floor : " + walls.get(0).floorNumber + " there is no Door !");
        }
        return true;
    }
    public static boolean firstQuadrant(Wall wall1) {/**True if the Wall parameter is entirely in the right upper quadrant*/
        return ((wall1.leftPoint.getX()>=0) && (wall1.leftPoint.getY()>=0) && (wall1.rightPoint.getX()>=0) && (wall1.rightPoint.getY()>=0));
    }
    public static boolean oneCommonExtremity(Wall w1, Wall w2) {/**True if the two Wall arguments share just one extremity*/
        return ( w1.leftPoint.equals(w2.leftPoint) || (w1.leftPoint.equals(w2.rightPoint)) || 
                (w1.rightPoint.equals(w2.leftPoint)) || (w1.rightPoint.equals(w2.rightPoint)) );
    }
    public static boolean intersect(Wall w1, Wall w2) {/**True if the two Wall arguments intersect in an interior point*/
          Line2D w1Line = new Line2D.Float(w1.leftPoint.getX(), w1.leftPoint.getY(), w1.rightPoint.getX(), w1.rightPoint.getY());
          Line2D w2Line = new Line2D.Float(w2.leftPoint.getX(), w2.leftPoint.getY(), w2.rightPoint.getX(), w2.rightPoint.getY());;
          return (w1Line.intersectsLine(w2Line));
    }
    
    private static double slope(Wall w){
        return (double)(w.rightPoint.getY()-w.leftPoint.getY())/(double)((w.rightPoint.getX()-w.leftPoint.getX()));
    }
    
    public static Path2D toPath2D(Room room) {/**Returns Path2D object equivalent to the Room parameter*/
        Path2D path = new Path2D.Double();
        for(int i = 0; i < room.walls.size(); i++){
            path.append(Wall.toLine2D(room.walls.get(i)), true);
        }
        return path;
    }

    public ArrayList<Wall> getExteriorWalls(){/**Returns an ArrayList of Room's exterior Walls*/
        ArrayList<Wall> exteriorWalls = new ArrayList<>();
        for(int i = 0; i < walls.size(); i++){
            if(walls.get(i).isExterior() == 1){
                exteriorWalls.add(walls.get(i));
            }
        }
        
        return exteriorWalls;
    }
    
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String name) {
        this.roomName = name;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public boolean isStairwell() {
        return isStairwell;
    }
    public void setIsStairwell(boolean stairwell) {
        this.isStairwell = stairwell;
    }
}
