/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import CustomExceptions.DataNotValidException;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import tablerepresentation.ElementManager;
import tablerepresentation.TableElement;

/**
 *
 * @author Procop Vladimir
 */
public class OneTest {
    
    public ArrayList<TableElement> test0(){
        ArrayList<TableElement> floor = new ArrayList<>();
        floor.add(new TableElement("wall", 0, 0, 5, 0, 1, "C101", 0, 0));
        floor.add(new TableElement("wall", 0, 0, 0, 5, 1, "C101", 0, 0));
        floor.add(new TableElement("wall", 0, 5, 5, 5, 1, "C101", 0, 0));
        floor.add(new TableElement("wall", 5, 5, 5, 0, 1, "C101", 0, 0));
        floor.add(new TableElement("window", 0, 2, 0, 3, 1, "C101", 0, 0));
        floor.add(new TableElement("door", 5, 3, 5, 4, 1, "C101", 0, 0));
        floor.add(new TableElement("stairs", 0, 15, 3, 15, 1, "Stairs1", 0, 0));
        floor.add(new TableElement("stairs", 3, 15, 3, 18, 1, "Stairs1", 0, 0));
        floor.add(new TableElement("stairs", 0, 15, 0, 18, 1, "Stairs1", 0, 0));
        floor.add(new TableElement("stairs", 0, 18, 3, 18, 1, "Stairs1", 0, 0));
        floor.add(new TableElement("stairs", 3, 16, 3, 17, 1, "Stairs1", 0, 0));

        floor.add(new TableElement("wall", 0, 0, 5, 0, 2, "C201", 0, 0));
        floor.add(new TableElement("wall", 0, 0, 0, 5, 2, "C201", 0, 0));
        floor.add(new TableElement("wall", 0, 5, 5, 5, 2, "C201", 0, 0));
        floor.add(new TableElement("wall", 5, 5, 5, 0, 2, "C201", 0, 0));
        floor.add(new TableElement("window", 0, 2, 0, 3, 2, "C201", 0, 0));
        floor.add(new TableElement("door", 5, 3, 5, 4, 2, "C201", 0, 0));
        
        return floor;
    }
    
    @Test 
    public void test0Run()throws DataNotValidException{
        ArrayList<TableElement> floor = test0();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("*******************TEST 0 ********************************************************");
            em.validateElements();
            fail("DataNotValidException expected");
        }catch(DataNotValidException e){
            System.out.println("Test 0 : " + e.getMessage());
        }
    }
    
    
    //no errors
    //no errors
    public ArrayList<TableElement> test1(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,0,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,0,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,0,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,0,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,0,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,0,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,0,"-1",1,0));
        floor.add(new TableElement("wall",36,2,24,2,0,"-1",1,0));
//        floor.add(new TableElement("wall",36,2,2,2,0,"-1",1,0)); ////
//        floor.add(new TableElement("wall",2,2,2,17,0,"-1",1,0));  ////
        floor.add(new TableElement("wall",2,11,2,17,0,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,0,"-1",1,0));

        //room "101"
        floor.add(new TableElement("door",5,11,10,11,0,"101",0,0));
        floor.add(new TableElement("wall",2,11,5,11,0,"101",0,0));
        floor.add(new TableElement("wall",10,11,13,11,0,"101",0,0));
        floor.add(new TableElement("wall",13,11,13,2,0,"101",0,0));
        floor.add(new TableElement("wall",13,2,2,2,0,"101",1,0));     /////
        floor.add(new TableElement("wall",2,2,2,11,0,"101",1,0));    /////

        //room "102"
        floor.add(new TableElement("door",24,9,24,5,0,"102",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,0,"102",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,0,"102",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,0,"102",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,0,"102",1,0));////      ////
        floor.add(new TableElement("wall",13,2,13,11,0,"102",0,0));////

        //room "103"
        floor.add(new TableElement("door",29,16,32,16,0,"103",0,0));
        floor.add(new TableElement("door",34,13,34,10,0,"103",0,0));
        floor.add(new TableElement("wall",27,16,29,16,0,"103",0,0));
        floor.add(new TableElement("wall",32,16,34,16,0,"103",0,0));
        floor.add(new TableElement("wall",34,16,34,13,0,"103",0,0));
        floor.add(new TableElement("wall",34,10,34,4,0,"103",0,0));
        floor.add(new TableElement("wall",34,4,27,4,0,"103",0,0));
        floor.add(new TableElement("wall",27,4,27,16,0,"103",0,0));
        return floor;
    }
    
    @Test 
    public void test1Run()throws DataNotValidException{
        ArrayList<TableElement> floor = test1();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("**TEST 1 **");
            assertTrue(em.validateElements());
        }catch(DataNotValidException e){
            System.out.println("Test 1 : " + e.getMessage());
        }

    }

    //error: floor door not where it should be
    public ArrayList<TableElement> test2(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
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
    @Test
    public void test2Run(){
        ArrayList<TableElement> floor = test2();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("**TEST 2 **");
            em.validateElements();
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println("Test 2 : " + e.getMessage());
        }
    }

    //error: floor with no doors
    public ArrayList<TableElement> test3(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,0,"-1",1,0));
        floor.add(new TableElement("wall",2,17,2,22,0,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,0,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,0,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,0,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,0,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,0,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,0,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,0,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,0,"-1",1,0));

        //room "101"
        floor.add(new TableElement("door",5,11,10,11,0,"101",0,0));
        floor.add(new TableElement("wall",2,11,5,11,0,"101",0,0));
        floor.add(new TableElement("wall",10,11,13,11,0,"101",0,0));
        floor.add(new TableElement("wall",13,11,13,2,0,"101",0,0));
        floor.add(new TableElement("wall",13,2,2,2,0,"101",0,0));
        floor.add(new TableElement("wall",2,2,2,11,0,"101",0,0));

        //room "102"
        floor.add(new TableElement("door",24,9,24,5,0,"102",0,0));
        floor.add(new TableElement("wall",13,11,24,11,0,"102",0,0));
        floor.add(new TableElement("wall",24,11,24,9,0,"102",0,0));
        floor.add(new TableElement("wall",24,5,24,2,0,"102",0,0));
        floor.add(new TableElement("wall",24,2,13,2,0,"102",0,0));
        floor.add(new TableElement("wall",13,2,13,11,0,"102",0,0));

        //room "103"
        floor.add(new TableElement("door",29,16,32,16,0,"103",0,0));
        floor.add(new TableElement("door",34,13,34,10,0,"103",0,0));
        floor.add(new TableElement("wall",27,16,29,16,0,"103",0,0));
        floor.add(new TableElement("wall",32,16,34,16,0,"103",0,0));
        floor.add(new TableElement("wall",34,16,34,13,0,"103",0,0));
        floor.add(new TableElement("wall",34,10,34,4,0,"103",0,0));
        floor.add(new TableElement("wall",34,4,27,4,0,"103",0,0));
        floor.add(new TableElement("wall",27,4,27,16,0,"103",0,0));

        return floor;
    }
    @Test
    public void test3Run()throws DataNotValidException{
        ArrayList<TableElement> floor = test3();
        ElementManager em = new ElementManager(floor);
//        try{
            System.out.println("**TEST 3 **");
            assertTrue(em.validateElements());
//            fail("DataNotValidException expected !");
//        }catch(DataNotValidException e){
//            System.out.println("Test 3 : " + e.getMessage());
//        }
    }

    //error: room "101" no door
    public ArrayList<TableElement> test4(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
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
    @Test
    public void test4Run(){
        ArrayList<TableElement> floor = test4();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("**TEST 4 **");
            em.validateElements();
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println("Test 4 : " + e.getMessage());
        }
    }
    
    public ArrayList<TableElement> test16(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        
        //Floor 1
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
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,1,"102",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));////

        //stairs "s1"
        floor.add(new TableElement("door",29,16,32,16,1,"s1",0,0));
        floor.add(new TableElement("door",34,13,34,10,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,1,"s1",0,0));
        
        //Floor 2
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,2,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,2,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,2,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,2,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,2,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,2,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,2,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,2,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,2,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,2,"-1",1,0));

        //room "201"
        floor.add(new TableElement("door",5,11,10,11,2,"201",0,0));
        floor.add(new TableElement("wall",2,11,5,11,2,"201",0,0));
        floor.add(new TableElement("wall",10,11,13,11,2,"201",0,0));
        floor.add(new TableElement("wall",13,11,13,2,2,"201",0,0));
        floor.add(new TableElement("wall",13,2,2,2,2,"201",0,0));
        floor.add(new TableElement("wall",2,2,2,11,2,"201",0,0));

        //stairs "s1"
        floor.add(new TableElement("stairs",29,16,32,16,2,"s1",0,0));
        floor.add(new TableElement("door",34,13,34,10,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,2,"s1",0,0));

        return floor;
    }
    @Test 
    public void test16Run()throws DataNotValidException{
        ArrayList<TableElement> building = test16();
        ElementManager em = new ElementManager(building);
        try{
            System.out.println("**TEST 16 **");
            em.validateElements();
//            fail("DataNotValidException expected");
        }catch(DataNotValidException e){
            System.out.println("Test 16 : " + e.getMessage());
        }
    }
    
    //no errors
    public ArrayList<TableElement> test17(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        
        //Floor 1
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
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,1,"102",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));////

        //stairs "s1"
        floor.add(new TableElement("door",29,16,32,16,1,"s1",0,0));
        floor.add(new TableElement("door",34,13,34,10,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,1,"s1",0,0));
        
        //Floor 2
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,2,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,2,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,2,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,2,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,2,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,2,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,2,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,2,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,2,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,2,"-1",1,0));

        //room "201"
        floor.add(new TableElement("door",5,11,10,11,2,"201",0,0));
        floor.add(new TableElement("wall",2,11,5,11,2,"201",0,0));
        floor.add(new TableElement("wall",10,11,13,11,2,"201",0,0));
        floor.add(new TableElement("wall",13,11,13,2,2,"201",0,0));
        floor.add(new TableElement("wall",13,2,2,2,2,"201",0,0));
        floor.add(new TableElement("wall",2,2,2,11,2,"201",0,0));

        //stairs "s1"
        floor.add(new TableElement("door",29,16,32,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,13,34,10,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,2,"s1",0,0));

        
        //Floor 3
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,3,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,3,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,3,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,3,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,3,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,3,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,3,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,3,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,3,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,3,"-1",1,0));

        //room "301"
        floor.add(new TableElement("door",24,9,24,5,3,"301",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,3,"301",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,3,"301",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,3,"301",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,3,"301",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,3,"301",0,0));////

        //stairs "s1"
        floor.add(new TableElement("stairs",29,16,32,16,3,"s1",0,0));
        floor.add(new TableElement("door",34,13,34,10,3,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,3,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,3,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,3,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,3,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,3,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,3,"s1",0,0));
        
        return floor;
    }
    @Test 
    public void test17Run()throws DataNotValidException{
        ArrayList<TableElement> building = test17();
        ElementManager em = new ElementManager(building);
        try{
            System.out.println("**TEST 17 **");
            assertTrue(em.validateElements());
//            fail("DataNotValidException expected");
        }catch(DataNotValidException e){
            System.out.println("Test 17 : " + e.getMessage());
        }
    }
    
    
    //no errors
    public ArrayList<TableElement> test18(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        
        //Floor 1
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
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,1,"102",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));////

        //stairs "s1"
        floor.add(new TableElement("door",29,16,32,16,1,"s1",0,0));
        floor.add(new TableElement("door",34,13,34,10,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,1,"s1",0,0));
        
        //Floor 2
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,2,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,2,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,2,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,2,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,2,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,2,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,2,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,2,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,2,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,2,"-1",1,0));

        //room "201"
        floor.add(new TableElement("door",5,11,10,11,2,"201",0,0));
        floor.add(new TableElement("wall",2,11,5,11,2,"201",0,0));
        floor.add(new TableElement("wall",10,11,13,11,2,"201",0,0));
        floor.add(new TableElement("wall",13,11,13,2,2,"201",0,0));
        floor.add(new TableElement("wall",13,2,2,2,2,"201",0,0));
        floor.add(new TableElement("wall",2,2,2,11,2,"201",0,0));

        //stairs "s1"
        floor.add(new TableElement("door",29,16,32,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,13,34,10,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,2,"s1",0,0));
        
        //stairs "s2"
        floor.add(new TableElement("door",10,23,13,23,2,"s2",0,0));
        floor.add(new TableElement("stairs",13,23,15,23,2,"s2",0,0));
        floor.add(new TableElement("stairs",15,23,15,17,2,"s2",0,0));
        floor.add(new TableElement("stairs",15,17,7,17,2,"s2",0,0));
        floor.add(new TableElement("stairs",7,17,7,23,2,"s2",0,0));
        floor.add(new TableElement("stairs",7,23,10,23,2,"s2",0,0));

        
        //Floor 3
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,3,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,3,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,3,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,3,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,3,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,3,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,3,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,3,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,3,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,3,"-1",1,0));

        //room "301"
        floor.add(new TableElement("door",24,9,24,5,3,"301",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,3,"301",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,3,"301",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,3,"301",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,3,"301",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,3,"301",0,0));////

        //stairs "s2"
        floor.add(new TableElement("door",10,23,13,23,3,"s2",0,0));
        floor.add(new TableElement("stairs",13,23,15,23,3,"s2",0,0));
        floor.add(new TableElement("stairs",15,23,15,17,3,"s2",0,0));
        floor.add(new TableElement("stairs",15,17,7,17,3,"s2",0,0));
        floor.add(new TableElement("stairs",7,17,7,23,3,"s2",0,0));
        floor.add(new TableElement("stairs",7,23,10,23,3,"s2",0,0));
        
        return floor;
    }
    @Test 
    public void test18Run()throws DataNotValidException{
        ArrayList<TableElement> building = test18();
        ElementManager em = new ElementManager(building);
        try{
            System.out.println("**TEST 18 **");
            assertTrue(em.validateElements());
//            fail("DataNotValidException expected");
        }catch(DataNotValidException e){
            System.out.println("Test 18 : " + e.getMessage());
        }
    }
    
    
    //error - s1 from second floor bad coordinates
    public ArrayList<TableElement> test20(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        
        //Floor 1
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
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,1,"102",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));////

        //stairs "s1"
        floor.add(new TableElement("door",29,16,32,16,1,"s1",0,0));
        floor.add(new TableElement("door",34,13,34,10,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,1,"s1",0,0));
        
        //Floor 2
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,2,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,2,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,2,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,2,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,2,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,2,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,2,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,2,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,2,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,2,"-1",1,0));

        //room "201"
        floor.add(new TableElement("door",5,11,10,11,2,"201",0,0));
        floor.add(new TableElement("wall",2,11,5,11,2,"201",0,0));
        floor.add(new TableElement("wall",10,11,13,11,2,"201",0,0));
        floor.add(new TableElement("wall",13,11,13,2,2,"201",0,0));
        floor.add(new TableElement("wall",13,2,2,2,2,"201",0,0));
        floor.add(new TableElement("wall",2,2,2,11,2,"201",0,0));

        //stairs "s1"
        floor.add(new TableElement("door",24,16,27,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",29,16,29,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",29,4,22,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",22,4,22,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",22,16,24,16,2,"s1",0,0));
        
        
        //Floor 3
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,3,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,3,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,3,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,3,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,3,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,3,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,3,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,3,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,3,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,3,"-1",1,0));

        //room "301"
        floor.add(new TableElement("door",24,9,24,5,3,"301",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,3,"301",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,3,"301",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,3,"301",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,3,"301",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,3,"301",0,0));////

        //stairs "s1"
        floor.add(new TableElement("stairs",29,16,32,16,3,"s1",0,0));
        floor.add(new TableElement("door",34,13,34,10,3,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,3,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,3,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,3,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,3,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,3,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,3,"s1",0,0));
        
        return floor;
    }
    @Test 
    public void test20Run()throws DataNotValidException{
        ArrayList<TableElement> building = test20();
        ElementManager em = new ElementManager(building);
        try{
            System.out.println("**TEST 20 **");
            em.validateElements();
            fail("DataNotValidException expected");
        }catch(DataNotValidException e){
            System.out.println("Test 20 : " + e.getMessage());
        }
    }
    
    //error - no door s1 from floor 2
    public ArrayList<TableElement> test21(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        
        //Floor 1
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
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,1,"102",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));////

        //stairs "s1"
        floor.add(new TableElement("door",29,16,32,16,1,"s1",0,0));
        floor.add(new TableElement("door",34,13,34,10,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,1,"s1",0,0));
        
        //Floor 2
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,2,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,2,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,2,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,2,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,2,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,2,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,2,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,2,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,2,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,2,"-1",1,0));

        //room "201"
        floor.add(new TableElement("door",5,11,10,11,2,"201",0,0));
        floor.add(new TableElement("wall",2,11,5,11,2,"201",0,0));
        floor.add(new TableElement("wall",10,11,13,11,2,"201",0,0));
        floor.add(new TableElement("wall",13,11,13,2,2,"201",0,0));
        floor.add(new TableElement("wall",13,2,2,2,2,"201",0,0));
        floor.add(new TableElement("wall",2,2,2,11,2,"201",0,0));

        //stairs "s1"
        floor.add(new TableElement("stairs",29,16,32,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,13,34,10,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,2,"s1",0,0));

        
        //Floor 3
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,3,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,3,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,3,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,3,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,3,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,3,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,3,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,3,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,3,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,3,"-1",1,0));

        //room "301"
        floor.add(new TableElement("door",24,9,24,5,3,"301",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,3,"301",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,3,"301",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,3,"301",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,3,"301",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,3,"301",0,0));////

        //stairs "s1"
        floor.add(new TableElement("stairs",29,16,32,16,3,"s1",0,0));
        floor.add(new TableElement("door",34,13,34,10,3,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,3,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,3,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,3,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,3,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,3,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,3,"s1",0,0));
        
        return floor;
    }
    @Test 
    public void test21Run()throws DataNotValidException{
        ArrayList<TableElement> building = test21();
        ElementManager em = new ElementManager(building);
        try{
            System.out.println("**TEST 21 **");
            em.validateElements();
            fail("DataNotValidException expected");
        }catch(DataNotValidException e){
            System.out.println("Test 21 : " + e.getMessage());
        }
    }
    
    //error - no stairs floor 3
    public ArrayList<TableElement> test22(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        
        //Floor 1
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
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,1,"102",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));////

        //stairs "s1"
        floor.add(new TableElement("door",29,16,32,16,1,"s1",0,0));
        floor.add(new TableElement("door",34,13,34,10,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,1,"s1",0,0));
        
        //Floor 2
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,2,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,2,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,2,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,2,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,2,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,2,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,2,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,2,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,2,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,2,"-1",1,0));

        //room "201"
        floor.add(new TableElement("door",5,11,10,11,2,"201",0,0));
        floor.add(new TableElement("wall",2,11,5,11,2,"201",0,0));
        floor.add(new TableElement("wall",10,11,13,11,2,"201",0,0));
        floor.add(new TableElement("wall",13,11,13,2,2,"201",0,0));
        floor.add(new TableElement("wall",13,2,2,2,2,"201",0,0));
        floor.add(new TableElement("wall",2,2,2,11,2,"201",0,0));

        //stairs "s1"
        floor.add(new TableElement("door",29,16,32,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,13,34,10,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,2,"s1",0,0));

        
        //Floor 3
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,3,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,3,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,3,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,3,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,3,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,3,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,3,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,3,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,3,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,3,"-1",1,0));

        //room "301"
        floor.add(new TableElement("door",24,9,24,5,3,"301",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,3,"301",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,3,"301",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,3,"301",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,3,"301",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,3,"301",0,0));////

        
        return floor;
    }
    @Test 
    public void test22Run()throws DataNotValidException{
        ArrayList<TableElement> building = test22();
        ElementManager em = new ElementManager(building);
        try{
            System.out.println("**TEST 22 **");
            em.validateElements();
            fail("DataNotValidException expected");
        }catch(DataNotValidException e){
            System.out.println("Test 22 : " + e.getMessage());
        }
    }
    
    
    //floor 3 inaccessable
    public ArrayList<TableElement> test23(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(type,type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        
        //Floor 1
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
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,1,"102",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));////

        //stairs "s1"
        floor.add(new TableElement("door",29,16,32,16,1,"s1",0,0));
        floor.add(new TableElement("door",34,13,34,10,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,1,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,1,"s1",0,0));
        
        //Floor 2
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,2,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,2,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,2,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,2,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,2,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,2,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,2,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,2,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,2,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,2,"-1",1,0));

        //room "201"
        floor.add(new TableElement("door",5,11,10,11,2,"201",0,0));
        floor.add(new TableElement("wall",2,11,5,11,2,"201",0,0));
        floor.add(new TableElement("wall",10,11,13,11,2,"201",0,0));
        floor.add(new TableElement("wall",13,11,13,2,2,"201",0,0));
        floor.add(new TableElement("wall",13,2,2,2,2,"201",0,0));
        floor.add(new TableElement("wall",2,2,2,11,2,"201",0,0));

        //stairs "s1"
        floor.add(new TableElement("door",29,16,32,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,13,34,10,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,16,29,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",32,16,34,16,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,16,34,13,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,10,34,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",34,4,27,4,2,"s1",0,0));
        floor.add(new TableElement("stairs",27,4,27,16,2,"s1",0,0));
        

        
        //Floor 3
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,3,"-1",1,0));
        floor.add(new TableElement("door",2,17,2,22,3,"-1",1,0));
        floor.add(new TableElement("wall",2,27,10,27,3,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,3,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,3,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,3,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,3,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,3,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,3,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,3,"-1",1,0));

        //room "301"
        floor.add(new TableElement("door",24,9,24,5,3,"301",0,0));////
        floor.add(new TableElement("wall",13,11,24,11,3,"301",0,0));////
        floor.add(new TableElement("wall",24,11,24,9,3,"301",0,0));////
        floor.add(new TableElement("wall",24,5,24,2,3,"301",0,0));////
        floor.add(new TableElement("wall",24,2,13,2,3,"301",0,0));////
        floor.add(new TableElement("wall",13,2,13,11,3,"301",0,0));////

        //stairs "s2"
        floor.add(new TableElement("door",10,23,13,23,3,"s2",0,0));
        floor.add(new TableElement("stairs",13,23,15,23,3,"s2",0,0));
        floor.add(new TableElement("stairs",15,23,15,17,3,"s2",0,0));
        floor.add(new TableElement("stairs",15,17,7,17,3,"s2",0,0));
        floor.add(new TableElement("stairs",7,17,7,23,3,"s2",0,0));
        floor.add(new TableElement("stairs",7,23,10,23,3,"s2",0,0));
        
        return floor;
    }
    @Test 
    public void test23Run()throws DataNotValidException{
        ArrayList<TableElement> building = test23();
        ElementManager em = new ElementManager(building);
        try{
            System.out.println("**TEST 23 **");
            em.validateElements();
            fail("DataNotValidException expected");
        }catch(DataNotValidException e){
            System.out.println("Test 23 : " + e.getMessage());
        }
    }
}
