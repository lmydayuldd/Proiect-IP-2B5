/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import CustomExceptions.DataNotValidException;
import Representations.Floor;
import Representations.Wall;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import tablerepresentation.ElementManager;
import tablerepresentation.TableElement;
/**
 *
 * @author dorin
 */
 
public class TestsArrayElements {
    //no errors
    public ArrayList<TableElement> test1(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,1,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,1,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,1,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,1,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,1,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,1,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,1,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,1,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,1,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,1,"-1",1,0));
        
        //room "101"
        floor.add(new TableElement("door",5,11,10,11,1,"101",0,0));
        floor.add(new TableElement("wall",2,11,5,11,1,"101",0,0));
        floor.add(new TableElement("wall",10,11,13,11,1,"101",0,0));
        floor.add(new TableElement("wall",13,11,13,2,1,"101",0,0));
        floor.add(new TableElement("wall",13,2,2,2,1,"101",0,0));
        floor.add(new TableElement("wall",2,2,2,11,1,"101",0,0));
        
        //room "102"
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,0));
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));
        floor.add(new TableElement("wall",24,2,13,2,1,"102",0,0));
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));
        
        //room "103"
        floor.add(new TableElement("door",29,16,32,16,1,"103",0,0));
        floor.add(new TableElement("door",34,13,34,10,1,"103",0,0));
        floor.add(new TableElement("wall",27,16,29,16,1,"103",0,0));
        floor.add(new TableElement("wall",32,16,34,16,1,"103",0,0));
        floor.add(new TableElement("wall",34,16,34,13,1,"103",0,0));
        floor.add(new TableElement("wall",34,10,34,4,1,"103",0,0));
        floor.add(new TableElement("wall",34,4,27,4,1,"103",0,0));
        floor.add(new TableElement("wall",27,4,27,16,1,"103",0,0));
        
        return floor;
    }
    
    //error: floor door not where it should be
    public ArrayList<TableElement> test2(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement("door",10,28,15,28,1,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,1,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,1,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,1,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,1,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,1,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,1,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,1,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,1,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,1,"-1",1,0));
        
        //room "101"
        floor.add(new TableElement("door",5,11,10,11,1,"101",0,0));
        floor.add(new TableElement("wall",2,11,5,11,1,"101",0,0));
        floor.add(new TableElement("wall",10,11,13,11,1,"101",0,0));
        floor.add(new TableElement("wall",13,11,13,2,1,"101",0,0));
        floor.add(new TableElement("wall",13,2,2,2,1,"101",0,0));
        floor.add(new TableElement("wall",2,2,2,11,1,"101",0,0));
        
        //room "102"
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,0));
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));
        floor.add(new TableElement("wall",24,2,13,2,1,"102",0,0));
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));
        
        //room "103"
        floor.add(new TableElement("door",29,16,32,16,1,"103",0,0));
        floor.add(new TableElement("door",34,13,34,10,1,"103",0,0));
        floor.add(new TableElement("wall",27,16,29,16,1,"103",0,0));
        floor.add(new TableElement("wall",32,16,34,16,1,"103",0,0));
        floor.add(new TableElement("wall",34,16,34,13,1,"103",0,0));
        floor.add(new TableElement("wall",34,10,34,4,1,"103",0,0));
        floor.add(new TableElement("wall",34,4,27,4,1,"103",0,0));
        floor.add(new TableElement("wall",27,4,27,16,1,"103",0,0));
        
        return floor;
    }
    
    //error: floor with no doors
    public ArrayList<TableElement> test3(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement("wall",10,27,15,27,1,"-1",1,0));
        floor.add(new TableElement("wall",2,17,2,22,1,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,1,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,1,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,1,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,1,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,1,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,1,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,1,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,1,"-1",1,0));
        
        //room "101"
        floor.add(new TableElement("door",5,11,10,11,1,"101",0,0));
        floor.add(new TableElement("wall",2,11,5,11,1,"101",0,0));
        floor.add(new TableElement("wall",10,11,13,11,1,"101",0,0));
        floor.add(new TableElement("wall",13,11,13,2,1,"101",0,0));
        floor.add(new TableElement("wall",13,2,2,2,1,"101",0,0));
        floor.add(new TableElement("wall",2,2,2,11,1,"101",0,0));
        
        //room "102"
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,0));
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));
        floor.add(new TableElement("wall",24,2,13,2,1,"102",0,0));
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));
        
        //room "103"
        floor.add(new TableElement("door",29,16,32,16,1,"103",0,0));
        floor.add(new TableElement("door",34,13,34,10,1,"103",0,0));
        floor.add(new TableElement("wall",27,16,29,16,1,"103",0,0));
        floor.add(new TableElement("wall",32,16,34,16,1,"103",0,0));
        floor.add(new TableElement("wall",34,16,34,13,1,"103",0,0));
        floor.add(new TableElement("wall",34,10,34,4,1,"103",0,0));
        floor.add(new TableElement("wall",34,4,27,4,1,"103",0,0));
        floor.add(new TableElement("wall",27,4,27,16,1,"103",0,0));
        
        return floor;
    }
    
    //error: room "101" no door
    public ArrayList<TableElement> test4(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,1,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,1,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,1,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,1,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,1,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,1,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,1,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,1,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,1,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,1,"-1",1,0));
        
        //room "101"
        floor.add(new TableElement("wall",5,11,10,11,1,"101",0,0));
        floor.add(new TableElement("wall",2,11,5,11,1,"101",0,0));
        floor.add(new TableElement("wall",10,11,13,11,1,"101",0,0));
        floor.add(new TableElement("wall",13,11,13,2,1,"101",0,0));
        floor.add(new TableElement("wall",13,2,2,2,1,"101",0,0));
        floor.add(new TableElement("wall",2,2,2,11,1,"101",0,0));
        
        //room "102"
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,0));
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));
        floor.add(new TableElement("wall",24,2,13,2,1,"102",0,0));
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));
        
        //room "103"
        floor.add(new TableElement("door",29,16,32,16,1,"103",0,0));
        floor.add(new TableElement("door",34,13,34,10,1,"103",0,0));
        floor.add(new TableElement("wall",27,16,29,16,1,"103",0,0));
        floor.add(new TableElement("wall",32,16,34,16,1,"103",0,0));
        floor.add(new TableElement("wall",34,16,34,13,1,"103",0,0));
        floor.add(new TableElement("wall",34,10,34,4,1,"103",0,0));
        floor.add(new TableElement("wall",34,4,27,4,1,"103",0,0));
        floor.add(new TableElement("wall",27,4,27,16,1,"103",0,0));
        
        return floor;
    }
    
    //error: room "102" door can't be accessed
    public ArrayList<TableElement> test5(){
        ArrayList<TableElement> floor = new ArrayList<>();
        
        return floor;
    }
    
    //error: room "101" and "102" badly placed
    public ArrayList<TableElement> test6(){
        ArrayList<TableElement> floor = new ArrayList<>();
        
        return floor;
    }
    
    //error: tow rooms ("101") with same number
    public ArrayList<TableElement> test7(){
        ArrayList<TableElement> floor = new ArrayList<>();
        
        return floor;
    }
    
    //error: room "102" badly placed
    public ArrayList<TableElement> test8(){
        ArrayList<TableElement> floor = new ArrayList<>();
        
        return floor;
    }
    
    //error: room 104 incomplete
    public ArrayList<TableElement> test9(){
        ArrayList<TableElement> floor = new ArrayList<>();
        
        return floor;
    }
    
    //error: room 104 outside the floor 
    public ArrayList<TableElement> test10(){
        ArrayList<TableElement> floor = new ArrayList<>();
        
        return floor;
    }
    
    @Test
    public void test1Run(){
        ArrayList<TableElement> floor1 = this.test1();
        ElementManager em = new ElementManager(floor1);
        try{
            assertTrue(em.validateElements());
        }
        catch(DataNotValidException e){
            System.out.println("Test 1 : " + e.getMessage());
        }
    }
    
    @Test
    public void test2Run(){
        ArrayList<TableElement> floor2 = this.test2();
        ElementManager em = new ElementManager(floor2);
        try{
            assertTrue(em.validateElements());
            fail("DataNotValidExceptoin expected");
        }catch(DataNotValidException e){
            System.out.println("Test 2 : " + e.getMessage());
        }
    }
    
    @Test
    public void test4Run(){
        ArrayList<TableElement> floor4 = this.test4();
        ElementManager em = new ElementManager(floor4);
        try{
            assertTrue(em.validateElements());
            fail("DataNotValidExceptoin expected");
        }
        catch(DataNotValidException e){
            System.out.println("Test 4 : " + e.getMessage());
        }
    }
    
    @Test
    public void test6Run(){
        ArrayList<TableElement> floor6 = this.test6();
        ElementManager em = new ElementManager(floor6);
        try{
            assertTrue(em.validateElements());
            fail("DataNotValidExceptoin expected");
        }
        catch(DataNotValidException e){
            System.out.println("Test 6 : " + e.getMessage());
        }
    }
}