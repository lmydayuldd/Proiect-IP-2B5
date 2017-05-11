/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Representations;

import CustomExceptions.DataNotValidException;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Procop Vladimir
 */
public class Floor extends Element {
    private int floorLevel;
    private int floorHeight;
    private ArrayList<Room> rooms = new ArrayList<>();
   
    public Floor(){/**Construct floor with floorHeight=0 and floorLevel=0*/
        this.floorHeight = 0; this.floorLevel = 0;
    }
    public Floor(int level, int height){/**Construct floor with floorHeight and floorLevel*/
        this.floorHeight = height; this.floorLevel = level;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof Floor)) return false;
        Floor f = (Floor)o; 
        if(f.getRooms().size()!=this.getRooms().size()) return false;
        
        for(Room thisRoom : this.getRooms()){
            boolean flag = false;
            for(Room otherRoom : f.getRooms()){
                if(thisRoom.equals(otherRoom)) flag = true;
            }
            if(flag == false) return false;
        }
        
        return true;
    }
    
    public void addRoom(Room r) throws DataNotValidException{/**Add room to this floor's rooms*/
        this.rooms.add(r);
    }
    
    public void deleteRoom()throws DataNotValidException{/**Delete a room from this floor*/
        //...
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
        if(floorHeight<2) throw new DataNotValidException("Floor height must be >=2");
        this.floorHeight = floorHeight;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> room)throws DataNotValidException {//la fel ca la Room ....
        this.rooms = room;
    }
    
    public static boolean validate(Floor floor) throws DataNotValidException{
        Path2D room1AsPath = null;
        Path2D room2AsPath = null;
        Area room1AsArea = null;
        Area room2AsArea = null;
        Area room2Temp = null;
        
        for(int i = 0 ; i < floor.rooms.size()-1; i++){
            
            Room.validate(floor.rooms.get(i));
                                 
            room1AsPath = Room.toPath2D(floor.rooms.get(i));
            room1AsArea = new Area(room1AsPath);
            for(int j = i+1  ; j  < floor.rooms.size(); j++){
                room2AsPath = Room.toPath2D(floor.rooms.get(j));
                room2AsArea = new Area(room2AsPath);
                room2Temp = new Area(room1AsPath);
                room2Temp.intersect(room2AsArea);
                if(!room2Temp.isEmpty()){
                    throw new DataNotValidException("Rooms [" + floor.rooms.get(i) + "]  AND  [" + floor.rooms.get(j) + "] overlap");
                }
            }
        }        
        
        Room.validate(floor.rooms.get(floor.rooms.size()-1));
        return true;
    }
}
