/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablerepresentation;

import CustomExceptions.DataNotValidException;
import Representations.*;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Procop Vladimir
 */
public class ElementManager {/**Class that manages the elements in the TEMPORARY_DATA table. Contains methods for validating or rejecting the building representation from the table*/
    ArrayList<TableElement> elements = null;
    ArrayList<Wall> walls = null;
    ArrayList<Room> rooms = null;
    ArrayList<Floor> floors = null;
    ArrayList<Stairs3D> stairs3D = null;
    ArrayList<Room> stairs = null;
    ElementFilter filter = null;
    
    /**Constructor. No elements and no exterior walls.*/
    public ElementManager(){
        this.elements = new ArrayList<>();
        this.filter = new ElementFilter(this.elements);
    }
    /**Constructor. The parameter becomes the manager's set of TableElements.*/
    public ElementManager(ArrayList<TableElement> elements){
        this.elements = elements;
        this.filter = new ElementFilter(this.elements);
    }
    /**Adds an element to the set of TableElements*/
    public void addElement(TableElement te){
        this.elements.add(te);
    }
    
    /**Return true if the specified set of TableElements constitutes a valid building. Otherwise it throws DataNotValidException*/
    public boolean validateElements() throws DataNotValidException{
        DataNotValidExceptionLogger.clearLog();
        this.walls = this.filter.getWalls();
        this.rooms = this.filter.getRooms(this.walls);
        this.floors = this.filter.getFloors(rooms);
        this.stairs = this.filter.getStairsRooms();
        this.stairs3D = this.filter.getStairs3D();
        
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        for(int i = 0 ; i < floors.size(); i++){
//            for(int j = 0 ; j< floors.get(i).getExteriorWalls().size(); j++){
//                System.out.println(floors.get(i).getExteriorWalls().get(j));   
//            }
//        }
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        if(!DataNotValidExceptionLogger.getExceptionMessage().isEmpty()){
            throw new DataNotValidException(DataNotValidExceptionLogger.getExceptionMessageAsString());
        }
        return true;
    }
}