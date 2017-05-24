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
    //no errors
    //no errors
    public ArrayList<TableElement> test1(){
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
    public void test1Run()throws DataNotValidException{
        ArrayList<TableElement> floor = test1();
        ElementManager em = new ElementManager(floor);
//        try{
            System.out.println("**TEST 1 **");
            assertTrue(em.validateElements());
//        }catch(DataNotValidException e){
//            System.out.println("Test 1 : " + e.getMessage());
//        }

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


    // No errors
    public ArrayList<TableElement> test5(){
        ArrayList<TableElement> floor = new ArrayList<>();
      //constructor: TableElement(type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,1,"-1",1,1));
        floor.add(new TableElement("door",2,17,2,22,1,"-1",1,1));
        floor.add(new TableElement("wall",2,27,10,27,1,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,1,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,1,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,1,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,1,"-1",1,0));
        floor.add(new TableElement("wall",36,2,2,2,1,"-1",1,0));
        floor.add(new TableElement("wall",2,2,2,17,1,"-1",1,0));
        floor.add(new TableElement("wall",2,22,2,27,1,"-1",1,0));

        //room "101"
        floor.add(new TableElement("door",5,11,10,11,1,"101",0,1));////
        floor.add(new TableElement("door",13,9,13,5,1,"101",0,1));
        floor.add(new TableElement("wall",2,11,5,11,1,"101",0,0));////
        floor.add(new TableElement("wall",10,11,13,11,1,"101",0,0));////
        floor.add(new TableElement("wall",13,11,13,2,1,"101",0,0));/////
        floor.add(new TableElement("wall",13,2,2,2,1,"101",1,0));//////
        floor.add(new TableElement("wall",2,2,2,11,1,"101",1,0));/////

        //room "102"
        floor.add(new TableElement("door",13,5,13,9,1,"102",0,1));
        floor.add(new TableElement("wall",13,2,13,5,1,"102",0,0));
        floor.add(new TableElement("wall",13,9,13,11,1,"102",0,0));
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));
        floor.add(new TableElement("wall",24,11,24,2,1,"102",0,0));
        floor.add(new TableElement("wall",24,2,13,2,1,"102",1,0));

        //room "103"
        floor.add(new TableElement("door",29,16,32,16,1,"103",0,1));
        floor.add(new TableElement("door",34,13,34,10,1,"103",0,1));
        floor.add(new TableElement("wall",27,16,29,16,1,"103",0,0));
        floor.add(new TableElement("wall",32,16,34,16,1,"103",0,0));
        floor.add(new TableElement("wall",34,16,34,13,1,"103",0,0));
        floor.add(new TableElement("wall",34,10,34,4,1,"103",0,0));
        floor.add(new TableElement("wall",34,4,27,4,1,"103",0,0));
        floor.add(new TableElement("wall",27,4,27,16,1,"103",0,0));
        return floor;
    }
    @Test
    public void test5Run()throws DataNotValidException{
        ArrayList<TableElement> floor = test5();
        ElementManager em = new ElementManager(floor);
//        try{
            System.out.println("**TEST 5 **");
            assertTrue(em.validateElements());
//            fail("DataNotValidException expected !");
//        }catch(DataNotValidException e){
//            System.out.println("Test 5 : " + e.getMessage());
//        }
    }
}
