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

/**
 *
 * @author Procop Vladimir
 */
public class Room extends Element{

    private ArrayList<Wall> walls = new ArrayList<>();
    private int roomNumber;
    private int floorNumber;
    
    public Room(int number){/**Construct a room with a name*/
        this.roomNumber = number;
    }
    public Room(ArrayList<Wall> walls){
        this.walls = walls;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof Room)) return false;
        Room r = (Room)o; 
        if(r.getWalls().size()!=this.getWalls().size()) return false;
        
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
            throw new DataNotValidException("Tried to insert into [" + this.toString() + "] a wall from different floor !");
        }
        this.walls.add(wall);
    }
    
    public void deleteWall(Wall wall)throws DataNotValidException{/**Delete a wall from this room*/
        this.walls.remove(wall);
    }
    
    @Override
    public String toString(){
        String ans = "Room " + this.getRoomNumber() + " : "; int i = 0;
        for(i = 0; i < walls.size()-1; i++){
            ans = ans +  i + " -> "  + walls.get(i).toString() + " , ";
        }
        ans = ans + i + " -> " + walls.get(walls.size()-1).toString();
        return ans + ". Floor: " + this.floorNumber;
    }
    
    public ArrayList<Wall> getWalls() {/**Returns the room's walls*/
        return walls;
    }

    public void setWalls(ArrayList<Wall> walls) {// Nu stiu daca ar avea sens asta..... camera poate fi facuta 
                                                        // doar cu addWall() de mai multe ori
        this.walls = walls;
    }
    
    public static boolean validate(Room room)throws DataNotValidException{/**True if argument is a valid room, false otherwise*/
        ArrayList<Wall> walls = room.walls;
        if((walls==null) || (walls.size()< 3)){
            throw new DataNotValidException("O camera trebuie sa aiba cel putin 3 pereti pentru a fi valida");
        }
        boolean door = false;
        int[] neighbors = new int[walls.size()]; 
        for(int i = 0; i < walls.size(); i++){
            neighbors[i] = 0;
        }
        for(int i = 0; i < walls.size()-1; i++){
            if(!firstQuadrant(walls.get(i))){
                throw new DataNotValidException("["+walls.get(i).toString()+"]" + " nu se situeaza in cadranul 1 in totalitate");
            }
            if(walls.get(i) instanceof Door){
                door = true;
            }
            for(int j = i+1; j < walls.size(); j++){
                if(walls.get(i).equals(walls.get(j))){
                    throw new DataNotValidException("Peretele " + walls.get(i) + " a fost specificat de doua ori");
                }
                if(oneCommonExtremity(walls.get(i), walls.get(j))){
                    neighbors[i]++; 
                    neighbors[j]++;
                    continue;
                }
                else if(intersect(walls.get(i), walls.get(j))){
                    throw new DataNotValidException("[" + walls.get(i) + "]" + " si " + "[" + walls.get(j) + "]" + " se intersecteaza intr-un punct interior");
                }
            }
        }
        
        for(int k = 0 ; k < neighbors.length; k++){
                if(neighbors[k]!=2) {
                        throw new DataNotValidException("["+walls.get(k)+"]" + " are " + neighbors[k] + " vecini, nu 2 ");
                }    
        }
        
        if(walls.get(walls.size()-1) instanceof Door){
            door = true;
        }
        if(!firstQuadrant(walls.get(walls.size()-1))){
                throw new DataNotValidException("["+walls.get(walls.size()-1).toString()+"]" + " nu se situeaza in cadranul 1 in totalitate");
            }
        
        if(!door) {
            throw new DataNotValidException("In camera " + walls.get(0).getRoomNumber() + " nu exista usa");
        }
        return true;
    }
    public static boolean firstQuadrant(Wall wall1) {
        return ((wall1.leftPoint.getX()>=0) && (wall1.leftPoint.getY()>=0) && (wall1.rightPoint.getX()>=0) && (wall1.rightPoint.getY()>=0));
    }
    public static boolean oneCommonExtremity(Wall w1, Wall w2) {
        return ( w1.leftPoint.equals(w2.leftPoint) || (w1.leftPoint.equals(w2.rightPoint)) || 
                (w1.rightPoint.equals(w2.leftPoint)) || (w1.rightPoint.equals(w2.rightPoint)) );
    }
    public static boolean intersect(Wall w1, Wall w2) {
          Line2D w1Line = new Line2D.Float(w1.leftPoint.getX(), w1.leftPoint.getY(), w1.rightPoint.getX(), w1.rightPoint.getY());
          Line2D w2Line = new Line2D.Float(w2.leftPoint.getX(), w2.leftPoint.getY(), w2.rightPoint.getX(), w2.rightPoint.getY());;
          return (w1Line.intersectsLine(w2Line));
    }
    
    private static double slope(Wall w){
        return (double)(w.rightPoint.getY()-w.leftPoint.getY())/(double)((w.rightPoint.getX()-w.leftPoint.getX()));
    }
    
    public static Path2D toPath2D(Room room) {
        Path2D path = new Path2D.Double();
        for(int i = 0; i < room.walls.size(); i++){
            path.append(Wall.toLine2D(room.walls.get(i)), true);
        }
        return path;
    }

    public ArrayList<Wall> getExteriorWalls(){
        ArrayList<Wall> exteriorWalls = new ArrayList<>();
        for(int i = 0; i < walls.size(); i++){
            if(walls.get(i).isIsExterior()){
                exteriorWalls.add(walls.get(i));
            }
        }
        
        return exteriorWalls;
    }
    
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
