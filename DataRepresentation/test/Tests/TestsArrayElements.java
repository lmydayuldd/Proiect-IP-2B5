package Tests;

import CustomExceptions.DataNotValidException;
import Representations.Wall;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import tablerepresentation.ElementManager;
import tablerepresentation.TableElement;
/**
 *
 * @author Dorin, Daniel
 *
 */
public class TestsArrayElements {
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

    //error: room "101" and "102" badly placed
    public ArrayList<TableElement> test6(){
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
        floor.add(new TableElement("door",5,11,10,11,1,"101",0,1));
        floor.add(new TableElement("wall",2,11,5,11,1,"101",0,0));
        floor.add(new TableElement("wall",10,11,13,11,1,"101",0,0));
        floor.add(new TableElement("wall",13,11,13,9,1,"101",0,0));
        floor.add(new TableElement("wall",13,5,13,2,1,"101",0,0));
        floor.add(new TableElement("wall",13,2,2,2,1,"101",1,0));
        floor.add(new TableElement("wall",2,2,2,11,1,"101",1,0));

        //room "102"
        floor.add(new TableElement("door",22,12,22,16,1,"102",0,1));
        floor.add(new TableElement("wall",11,9,11,18,1,"102",0,0));
        floor.add(new TableElement("wall",11,18,22,18,1,"102",0,0));
        floor.add(new TableElement("wall",22,18,22,16,1,"102",0,0));
        floor.add(new TableElement("wall",22,12,22,9,1,"102",0,0));
        floor.add(new TableElement("wall",22,9,11,9,1,"102",1,0));

        //room "103"
        floor.add(new TableElement("door",29,16,32,16,1,"103",0,1));
        floor.add(new TableElement("wall",34,13,34,10,1,"103",0,1));
        floor.add(new TableElement("wall",27,16,29,16,1,"103",0,0));
        floor.add(new TableElement("wall",32,16,34,16,1,"103",0,0));
        floor.add(new TableElement("wall",34,16,34,13,1,"103",0,0));
        floor.add(new TableElement("wall",34,10,34,4,1,"103",0,0));
        floor.add(new TableElement("wall",34,4,27,4,1,"103",0,0));
        floor.add(new TableElement("wall",27,4,27,16,1,"103",0,0));
        return floor;
    }
    @Test
    public void test6Run(){
        ArrayList<TableElement> floor = test6();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("**TEST 6 **");
            em.validateElements();
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println("Test 6 : " + e.getMessage());
        }
    }

    //error: two rooms ("101") with same number
    public ArrayList<TableElement> test7(){
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
        floor.add(new TableElement("door",5,11,10,11,1,"101",0,1));
        floor.add(new TableElement("wall",2,11,5,11,1,"101",0,0));
        floor.add(new TableElement("wall",10,11,13,11,1,"101",0,0)); /////
        floor.add(new TableElement("wall",13,11,13,2,1,"101",0,0));///
        floor.add(new TableElement("wall",13,2,2,2,1,"101",1,0));
        floor.add(new TableElement("wall",2,2,2,11,1,"101",1,0));

        //room "101"
        floor.add(new TableElement("door",24,9,24,5,1,"101",0,1));
        floor.add(new TableElement("wall",13,2,13,11,1,"101",0,0));///////
        floor.add(new TableElement("wall",13,11,24,11,1,"101",0,0)); /////
        floor.add(new TableElement("wall",24,11,24,9,1,"101",0,0));
        floor.add(new TableElement("wall",24,5,24,2,1,"101",0,0));
        floor.add(new TableElement("wall",24,2,13,2,1,"101",1,0));


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
    public void test7Run(){
        ArrayList<TableElement> floor = test7();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("**TEST 7 **");
            em.validateElements();
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println("Test 7 : " + e.getMessage());
        }
    }

    //error: room "102" badly placed
    public ArrayList<TableElement> test8(){
        ArrayList<TableElement> floor = new ArrayList<>();
      //constructor: TableElement(type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement("door",10,27,15,27,1,"-1",1,1)); 
        floor.add(new TableElement("door",2,17,2,22,1,"-1",1,1));
        floor.add(new TableElement("wall",2,27,10,27,1,"-1",1,0));
        floor.add(new TableElement("wall",15,27,24,27,1,"-1",1,0));
        floor.add(new TableElement("wall",24,27,24,18,1,"-1",1,0));
        floor.add(new TableElement("wall",24,18,36,18,1,"-1",1,0));
        floor.add(new TableElement("wall",36,18,36,2,1,"-1",1,0));////
        floor.add(new TableElement("wall",36,2,2,2,1,"-1",1,0)); ////
        floor.add(new TableElement("wall",2,22,2,27,1,"-1",1,0));

        //room "101"
        floor.add(new TableElement("door",5,11,10,11,1,"101",0,1));
        floor.add(new TableElement("wall",2,11,5,11,1,"101",0,0));
        floor.add(new TableElement("wall",10,11,13,11,1,"101",0,0));
        floor.add(new TableElement("wall",13,11,13,2,1,"101",0,0));
        floor.add(new TableElement("wall",13,2,2,2,1,"101",1,0));
        floor.add(new TableElement("wall",2,2,2,11,1,"101",1,0));

        //room "102"
//        floor.add(new TableElement("door",26,18,26,14,1,"101",0,1));
        floor.add(new TableElement("door",26,18,26,14,1,"102",0,1));
        floor.add(new TableElement("wall",15,11,15,20,1,"102",0,0));
        floor.add(new TableElement("wall",15,20,26,20,1,"102",0,0));
        floor.add(new TableElement("wall",26,20,26,18,1,"102",0,0));
        floor.add(new TableElement("wall",26,14,26,11,1,"102",0,0));
        floor.add(new TableElement("wall",26,11,15,11,1,"102",0,0));

        //room "103"
        floor.add(new TableElement("door",29,16,32,16,1,"103",0,1));
        floor.add(new TableElement("wall",34,13,34,10,1,"103",0,1));
        floor.add(new TableElement("wall",27,16,29,16,1,"103",0,0));
        floor.add(new TableElement("wall",32,16,34,16,1,"103",0,0));
        floor.add(new TableElement("wall",34,16,34,13,1,"103",0,0));
        floor.add(new TableElement("wall",34,10,34,4,1,"103",0,0));
        floor.add(new TableElement("wall",34,4,27,4,1,"103",0,0));
        floor.add(new TableElement("wall",27,4,27,16,1,"103",0,0));
        return floor;
    }
    @Test
    public void test8Run(){
        ArrayList<TableElement> floor = test8();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("**TEST 8 **");
            em.validateElements();
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println("Test 8 : " + e.getMessage());
        }
    }

    //error: room "104" incomplete
    public ArrayList<TableElement> test9(){
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
        floor.add(new TableElement("door",5,11,10,11,1,"101",0,1));
        floor.add(new TableElement("wall",2,11,5,11,1,"101",0,0));
        floor.add(new TableElement("wall",10,11,13,11,1,"101",0,0));
        floor.add(new TableElement("wall",13,11,13,2,1,"101",0,0));
        floor.add(new TableElement("wall",13,2,2,2,1,"101",1,0));
        floor.add(new TableElement("wall",2,2,2,11,1,"101",1,0));

      //room "102"
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,1));
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));
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

       //room "104"
        floor.add(new TableElement("wall",9,17,17,17,1,"104",0,0));
        floor.add(new TableElement("wall",17,17,17,23,1,"104",0,0));
        floor.add(new TableElement("wall",17,23,9,23,1,"104",0,0));
        return floor;
    }
    @Test
    public void test9Run(){
        ArrayList<TableElement> floor = test9();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("**TEST 9 **");
            em.validateElements();
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println("Test 9 : " + e.getMessage());
        }
    }

    //error: room "104" outside the floor
    public ArrayList<TableElement> test10(){
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
        floor.add(new TableElement("door",5,11,10,11,1,"101",0,0));
        floor.add(new TableElement("wall",2,11,5,11,1,"101",0,0));
        floor.add(new TableElement("wall",10,11,13,11,1,"101",0,0));
        floor.add(new TableElement("wall",13,11,13,2,1,"101",0,0));
        floor.add(new TableElement("wall",13,2,2,2,1,"101",1,0));
        floor.add(new TableElement("wall",2,2,2,11,1,"101",1,0));

      //room "102"
        floor.add(new TableElement("door",24,9,24,5,1,"102",0,1));
        floor.add(new TableElement("wall",13,2,13,11,1,"102",0,0));
        floor.add(new TableElement("wall",13,11,24,11,1,"102",0,0));
        floor.add(new TableElement("wall",24,11,24,9,1,"102",0,0));
        floor.add(new TableElement("wall",24,5,24,2,1,"102",0,0));
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

       //room "104"
        floor.add(new TableElement("door",29,22,33,22,1,"104",0,1));
        floor.add(new TableElement("wall",28,19,28,22,1,"104",0,0));
        floor.add(new TableElement("wall",33,22,34,22,1,"104",0,0));
        floor.add(new TableElement("wall",34,22,34,19,1,"104",0,0));
        floor.add(new TableElement("wall",34,19,28,19,1,"104",0,0));
//        floor.add(new TableElement("wall",28,19,28,22,1,"104",0,0));
        return floor;
    }
    @Test
    public void test10Run(){
        ArrayList<TableElement> floor = test10();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("**TEST 10 **");
            em.validateElements();
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println("Test 10 : " + e.getMessage());
        }
    }

    //No error
    public ArrayList<TableElement> test11(){
        ArrayList<TableElement> floor = new ArrayList<>();
      //constructor: TableElement(type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
      //Floor exterior walls
        floor.add(new TableElement("door",4,12,9,12,2,"-1",1,1));
        floor.add(new TableElement("door",19,19,23,19,2,"-1",1,1));
        floor.add(new TableElement("door",12,1,19,1,2,"-1",1,1));
        floor.add(new TableElement("wall",1,1,1,12,2,"-1",1,0));
        floor.add(new TableElement("wall",1,12,4,12,2,"-1",1,0));
        floor.add(new TableElement("wall",9,12,15,12,2,"-1",1,0));
        floor.add(new TableElement("wall",15,12,15,19,2,"-1",1,0));
        floor.add(new TableElement("wall",15,19,19,19,2,"-1",1,0));
        floor.add(new TableElement("wall",23,19,27,19,2,"-1",1,0));
        floor.add(new TableElement("wall",27,19,27,1,2,"-1",1,0));
        floor.add(new TableElement("wall",27,1,19,1,2,"-1",1,0));
        floor.add(new TableElement("wall",12,1,1,1,2,"-1",1,0));


        //room "201"
        floor.add(new TableElement("door",10,2,10,5,2,"201",0,1));
        floor.add(new TableElement("wall",1,1,1,6,2,"201",1,0));
        floor.add(new TableElement("wall",1,6,10,6,2,"201",0,0));
        floor.add(new TableElement("wall",10,6,10,5,2,"201",0,0));
        floor.add(new TableElement("wall",10,2,10,1,2,"201",0,0));
        floor.add(new TableElement("wall",10,1,1,1,2,"201",1,0));

        //room "202"
        floor.add(new TableElement("door",4,12,9,12,2,"202",0,1));
        floor.add(new TableElement("door",10,10,10,7,2,"202",0,1));
        floor.add(new TableElement("wall",1,6,1,12,2,"202",1,0));
        floor.add(new TableElement("wall",1,12,4,12,2,"202",1,0));
        floor.add(new TableElement("wall",9,12,10,12,2,"202",1,0));
        floor.add(new TableElement("wall",10,12,10,10,2,"202",0,0));
        floor.add(new TableElement("wall",10,7,10,6,2,"202",0,0));
        floor.add(new TableElement("wall",10,6,1,6,2,"202",0,0));

        //room "203"
        floor.add(new TableElement("door",10,2,10,5,2,"203",0,1));
        floor.add(new TableElement("door",10,7,10,10,2,"203",0,1));
        floor.add(new TableElement("door",17,12,20,12,2,"203",0,1));
        floor.add(new TableElement("door",12,1,19,1,2,"203",1,1));
        floor.add(new TableElement("wall",10,1,10,2,2,"203",0,0));
        floor.add(new TableElement("wall",10,5,10,7,2,"203",0,0));
        floor.add(new TableElement("wall",10,10,10,12,2,"203",0,0));
        floor.add(new TableElement("wall",10,12,15,12,2,"203",1,0));
        floor.add(new TableElement("wall",15,12,17,12,2,"203",0,0));
        floor.add(new TableElement("wall",20,12,21,12,2,"203",0,0));
        floor.add(new TableElement("wall",21,12,21,1,2,"203",0,0));
        floor.add(new TableElement("wall",21,1,19,1,2,"203",1,0));
        floor.add(new TableElement("wall",12,1,10,1,2,"203",1,0));


        //room "204"
        floor.add(new TableElement("door",23,10,25,10,2,"204",0,1));
        floor.add(new TableElement("wall",21,1,21,10,2,"204",0,0));
        floor.add(new TableElement("wall",21,10,23,10,2,"204",0,0));
        floor.add(new TableElement("wall",25,10,27,10,2,"204",0,0));
        floor.add(new TableElement("wall",27,10,27,1,2,"204",1,0));
        floor.add(new TableElement("wall",27,1,21,1,2,"204",1,0));


        //room "205"
        floor.add(new TableElement("door",17,12,20,12,2,"205",0,1));
        floor.add(new TableElement("door",19,19,23,19,2,"205",1,1));
        floor.add(new TableElement("door",23,12,25,12,2,"205",0,1));
        floor.add(new TableElement("wall",15,12,15,19,2,"205",1,0));
        floor.add(new TableElement("wall",15,19,19,19,2,"205",1,0));
        floor.add(new TableElement("wall",23,19,27,19,2,"205",1,0));
        floor.add(new TableElement("wall",27,19,27,10,2,"205",1,0));
        floor.add(new TableElement("wall",27,10,25,10,2,"205",0,0));
        floor.add(new TableElement("wall",23,10,21,10,2,"205",0,0));
        floor.add(new TableElement("wall",21,10,21,12,2,"205",0,0));
        floor.add(new TableElement("wall",21,12,20,12,2,"205",0,0));
        floor.add(new TableElement("wall",17,12,15,12,2,"205",0,0));

        return floor;
    }
    @Test
    public void test11Run() throws DataNotValidException{
        ArrayList<TableElement> floor = test11();
        ElementManager em = new ElementManager(floor);
//        try{
    System.out.println("**TEST 11 **");
            assertTrue(em.validateElements());
//            fail("DataNotValidException expected !");
//        }catch(DataNotValidException e){
//            System.out.println("Test 11 : " + e.getMessage());
//        }
    }

    //Error: exit bad placed
    public ArrayList<TableElement> test12(){
            ArrayList<TableElement> floor = new ArrayList<>();
    //constructor: TableElement(type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
          //Floor exterior walls
            floor.add(new TableElement("door",4,12,9,12,2,"-1",1,1));
            floor.add(new TableElement("door",19,19,23,19,2,"-1",1,1));
            floor.add(new TableElement("door",12,1,19,1,2,"-1",1,1));
            floor.add(new TableElement("wall",1,1,1,12,2,"-1",1,0));
            floor.add(new TableElement("wall",1,12,4,12,2,"-1",1,0));
            floor.add(new TableElement("wall",9,12,15,12,2,"-1",1,0));
            floor.add(new TableElement("wall",15,12,15,19,2,"-1",1,0));
            floor.add(new TableElement("wall",15,19,19,19,2,"-1",1,0));
            floor.add(new TableElement("wall",23,19,27,19,2,"-1",1,0));
            floor.add(new TableElement("wall",27,19,27,1,2,"-1",1,0));
            floor.add(new TableElement("wall",27,1,19,1,2,"-1",1,0));
            floor.add(new TableElement("wall",12,1,1,1,2,"-1",1,0));


            //room "201"
            floor.add(new TableElement("door",11,2,11,5,2,"201",0,1));
            floor.add(new TableElement("wall",1,1,1,6,2,"201",1,0));
            floor.add(new TableElement("wall",1,6,10,6,2,"201",0,0));
            floor.add(new TableElement("wall",10,6,10,1,2,"201",0,0));
            floor.add(new TableElement("wall",10,1,1,1,2,"201",1,0));

            //room "202"
            floor.add(new TableElement("door",4,12,9,12,2,"202",0,1));
            floor.add(new TableElement("door",10,10,10,7,2,"202",0,1));
            floor.add(new TableElement("wall",1,6,1,12,2,"202",1,0));
            floor.add(new TableElement("wall",1,12,4,12,2,"202",1,0));
            floor.add(new TableElement("wall",12,10,12,2,2,"202",1,0));
            floor.add(new TableElement("wall",10,12,10,10,2,"202",0,0));
            floor.add(new TableElement("wall",10,7,10,6,2,"202",0,0));
            floor.add(new TableElement("wall",10,6,1,6,2,"202",0,0));

            //room "203"
            floor.add(new TableElement("door",10,2,10,5,2,"203",0,1));
            floor.add(new TableElement("door",10,7,10,10,2,"203",0,1));
            floor.add(new TableElement("door",17,12,20,12,2,"203",0,1));
            floor.add(new TableElement("door",12,1,19,1,2,"203",1,1));
            floor.add(new TableElement("wall",10,1,10,2,2,"203",0,0));
            floor.add(new TableElement("wall",10,5,10,7,2,"203",0,0));
            floor.add(new TableElement("wall",10,10,10,12,2,"203",0,0));
            floor.add(new TableElement("wall",10,12,15,12,2,"203",1,0));
            floor.add(new TableElement("wall",15,12,17,12,2,"203",0,0));
            floor.add(new TableElement("wall",20,12,21,12,2,"203",0,0));
            floor.add(new TableElement("wall",21,12,21,1,2,"203",0,0));
            floor.add(new TableElement("wall",21,1,19,1,2,"203",1,0));
            floor.add(new TableElement("wall",12,1,10,1,2,"203",1,0));


            //room "204"
            floor.add(new TableElement("door",23,10,25,10,2,"204",0,1));
            floor.add(new TableElement("wall",21,1,21,10,2,"204",0,0));
            floor.add(new TableElement("wall",21,10,23,10,2,"204",0,0));
            floor.add(new TableElement("wall",25,10,27,10,2,"204",0,0));
            floor.add(new TableElement("wall",27,10,27,1,2,"204",1,0));
            floor.add(new TableElement("wall",27,1,21,1,2,"204",1,0));


            //room "205"
            floor.add(new TableElement("door",17,12,20,12,2,"205",0,1));
            floor.add(new TableElement("door",19,19,23,19,2,"205",1,1));
            floor.add(new TableElement("door",23,12,25,12,2,"205",0,1));
            floor.add(new TableElement("wall",15,12,15,19,2,"205",1,0));
            floor.add(new TableElement("wall",15,19,19,19,2,"205",1,0));
            floor.add(new TableElement("wall",23,19,27,19,2,"205",1,0));
            floor.add(new TableElement("wall",27,19,27,10,2,"205",1,0));
            floor.add(new TableElement("wall",27,10,25,10,2,"205",0,0));
            floor.add(new TableElement("wall",23,10,21,10,2,"205",0,0));
            floor.add(new TableElement("wall",21,10,21,12,2,"205",0,0));
            floor.add(new TableElement("wall",21,12,20,12,2,"205",0,0));
            floor.add(new TableElement("wall",17,12,15,12,2,"205",0,0));

            return floor;
        }
    @Test
    public void test12Run() throws DataNotValidException{
        ArrayList<TableElement> floor = test12();
        ElementManager em = new ElementManager(floor);
        try{
        System.out.println("**TEST 12 **");
            assertTrue(em.validateElements());
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println("Test 12 : " + e.getMessage());
        }
    }

   //Error: Room "201" doesn't have exit.
    public ArrayList<TableElement> test13(){
            ArrayList<TableElement> floor = new ArrayList<>();
    //constructor: TableElement(type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
          //Floor exterior walls
            floor.add(new TableElement("door",4,12,9,12,2,"-1",1,1));
            floor.add(new TableElement("door",19,19,23,19,2,"-1",1,1));
            floor.add(new TableElement("door",12,1,19,1,2,"-1",1,1));
            floor.add(new TableElement("wall",1,1,1,12,2,"-1",1,0));
            floor.add(new TableElement("wall",1,12,4,12,2,"-1",1,0));
            floor.add(new TableElement("wall",9,12,15,12,2,"-1",1,0));
            floor.add(new TableElement("wall",15,12,15,19,2,"-1",1,0));
            floor.add(new TableElement("wall",15,19,19,19,2,"-1",1,0));
            floor.add(new TableElement("wall",23,19,27,19,2,"-1",1,0));
            floor.add(new TableElement("wall",27,19,27,1,2,"-1",1,0));
            floor.add(new TableElement("wall",27,1,19,1,2,"-1",1,0));
            floor.add(new TableElement("wall",12,1,1,1,2,"-1",1,0));


            //room "201"
            floor.add(new TableElement("wall",1,1,1,6,2,"201",1,0));
            floor.add(new TableElement("wall",1,6,10,6,2,"201",0,0));
            floor.add(new TableElement("wall",10,6,10,1,2,"201",0,0));
            floor.add(new TableElement("wall",10,1,1,1,2,"201",1,0));

            //room "202"
            floor.add(new TableElement("door",4,12,9,12,2,"202",0,1));
            floor.add(new TableElement("door",10,10,10,7,2,"202",0,1));
            floor.add(new TableElement("wall",1,6,1,12,2,"202",1,0));
            floor.add(new TableElement("wall",1,12,4,12,2,"202",1,0));
            floor.add(new TableElement("wall",9,12,10,12,2,"202",1,0));
            floor.add(new TableElement("wall",10,12,10,10,2,"202",0,0));
            floor.add(new TableElement("wall",10,7,10,6,2,"202",0,0));
            floor.add(new TableElement("wall",10,6,1,6,2,"202",0,0));

            //room "203"
            floor.add(new TableElement("door",10,2,10,5,2,"203",0,1));
            floor.add(new TableElement("door",10,7,10,10,2,"203",0,1));
            floor.add(new TableElement("door",17,12,20,12,2,"203",0,1));
            floor.add(new TableElement("door",12,1,19,1,2,"203",1,1));
            floor.add(new TableElement("wall",10,1,10,2,2,"203",0,0));
            floor.add(new TableElement("wall",10,5,10,7,2,"203",0,0));
            floor.add(new TableElement("wall",10,10,10,12,2,"203",0,0));
            floor.add(new TableElement("wall",10,12,15,12,2,"203",1,0));
            floor.add(new TableElement("wall",15,12,17,12,2,"203",0,0));
            floor.add(new TableElement("wall",20,12,21,12,2,"203",0,0));
            floor.add(new TableElement("wall",21,12,21,1,2,"203",0,0));
            floor.add(new TableElement("wall",21,1,19,1,2,"203",1,0));
            floor.add(new TableElement("wall",12,1,10,1,2,"203",1,0));


            //room "204"
            floor.add(new TableElement("door",23,10,25,10,2,"204",0,1));
            floor.add(new TableElement("wall",21,1,21,10,2,"204",0,0));
            floor.add(new TableElement("wall",21,10,23,10,2,"204",0,0));
            floor.add(new TableElement("wall",25,10,27,10,2,"204",0,0));
            floor.add(new TableElement("wall",27,10,27,1,2,"204",1,0));
            floor.add(new TableElement("wall",27,1,21,1,2,"204",1,0));


            //room "205"
            floor.add(new TableElement("door",17,12,20,12,2,"205",0,1));
            floor.add(new TableElement("door",19,19,23,19,2,"205",1,1));
            floor.add(new TableElement("door",23,12,25,12,2,"205",0,1));
            floor.add(new TableElement("wall",15,12,15,19,2,"205",1,0));
            floor.add(new TableElement("wall",15,19,19,19,2,"205",1,0));
            floor.add(new TableElement("wall",23,19,27,19,2,"205",1,0));
            floor.add(new TableElement("wall",27,19,27,10,2,"205",1,0));
            floor.add(new TableElement("wall",27,10,25,10,2,"205",0,0));
            floor.add(new TableElement("wall",23,10,21,10,2,"205",0,0));
            floor.add(new TableElement("wall",21,10,21,12,2,"205",0,0));
            floor.add(new TableElement("wall",21,12,20,12,2,"205",0,0));
            floor.add(new TableElement("wall",17,12,15,12,2,"205",0,0));

            return floor;
        }
        @Test
        public void test13Run() throws DataNotValidException{
            ArrayList<TableElement> floor = test13();
            ElementManager em = new ElementManager(floor);
            try{
            System.out.println("**TEST 13 **");
                assertTrue(em.validateElements());
                fail("DataNotValidException expected !");
            }catch(DataNotValidException e){
                System.out.println("Test 13 : " + e.getMessage());
            }
        }
    
    //Error: Room "205" has one exit too big
    public ArrayList<TableElement> test14(){
            ArrayList<TableElement> floor = new ArrayList<>();
    //constructor: TableElement(type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
          //Floor exterior walls
            floor.add(new TableElement("door",4,12,9,12,2,"-1",1,1));
            floor.add(new TableElement("door",19,19,23,19,2,"-1",1,1));
            floor.add(new TableElement("door",12,1,19,1,2,"-1",1,1));
            floor.add(new TableElement("wall",1,1,1,12,2,"-1",1,0));
            floor.add(new TableElement("wall",1,12,4,12,2,"-1",1,0));
            floor.add(new TableElement("wall",9,12,15,12,2,"-1",1,0));
            floor.add(new TableElement("wall",15,12,15,19,2,"-1",1,0));
            floor.add(new TableElement("wall",15,19,19,19,2,"-1",1,0));
            floor.add(new TableElement("wall",23,19,27,19,2,"-1",1,0));
            floor.add(new TableElement("wall",27,19,27,1,2,"-1",1,0));
            floor.add(new TableElement("wall",27,1,19,1,2,"-1",1,0));
            floor.add(new TableElement("wall",12,1,1,1,2,"-1",1,0));


            //room "201"
            floor.add(new TableElement("door",10,2,10,5,2,"201",0,1));
            floor.add(new TableElement("wall",1,1,1,6,2,"201",1,0));
            floor.add(new TableElement("wall",1,6,10,6,2,"201",0,0));
            floor.add(new TableElement("wall",10,6,10,5,2,"201",0,0));
            floor.add(new TableElement("wall",10,2,10,1,2,"201",0,0));
            floor.add(new TableElement("wall",10,1,1,1,2,"201",1,0));

            //room "202"
            floor.add(new TableElement("door",4,12,9,12,2,"202",0,1));
            floor.add(new TableElement("door",10,10,10,7,2,"202",0,1));
            floor.add(new TableElement("wall",1,6,1,12,2,"202",1,0));
            floor.add(new TableElement("wall",1,12,4,12,2,"202",1,0));
            floor.add(new TableElement("wall",9,12,10,12,2,"202",1,0));
            floor.add(new TableElement("wall",10,12,10,10,2,"202",0,0));
            floor.add(new TableElement("wall",10,7,10,6,2,"202",0,0));
            floor.add(new TableElement("wall",10,6,1,6,2,"202",0,0));

            //room "203"
            floor.add(new TableElement("door",10,2,10,5,2,"203",0,1));
            floor.add(new TableElement("door",10,7,10,10,2,"203",0,1));
            floor.add(new TableElement("door",17,12,20,12,2,"203",0,1));
            floor.add(new TableElement("door",12,1,19,1,2,"203",1,1));
            floor.add(new TableElement("wall",10,1,10,2,2,"203",0,0));
            floor.add(new TableElement("wall",10,5,10,7,2,"203",0,0));
            floor.add(new TableElement("wall",10,10,10,12,2,"203",0,0));
            floor.add(new TableElement("wall",10,12,15,12,2,"203",1,0));
            floor.add(new TableElement("wall",15,12,17,12,2,"203",0,0));
            floor.add(new TableElement("wall",20,12,21,12,2,"203",0,0));
            floor.add(new TableElement("wall",21,12,21,1,2,"203",0,0));
            floor.add(new TableElement("wall",21,1,19,1,2,"203",1,0));
            floor.add(new TableElement("wall",12,1,10,1,2,"203",1,0));


            //room "204"
            floor.add(new TableElement("door",23,10,25,10,2,"204",0,1));
            floor.add(new TableElement("wall",21,1,21,10,2,"204",0,0));
            floor.add(new TableElement("wall",21,10,23,10,2,"204",0,0));
            floor.add(new TableElement("wall",25,10,27,10,2,"204",0,0));
            floor.add(new TableElement("wall",27,10,27,1,2,"204",1,0));
            floor.add(new TableElement("wall",27,1,21,1,2,"204",1,0));


            //room "205"
            floor.add(new TableElement("door",19,12,22,12,2,"205",0,1));
            floor.add(new TableElement("door",19,19,23,19,2,"205",1,1));
            floor.add(new TableElement("door",23,12,25,12,2,"205",0,1));
            floor.add(new TableElement("wall",15,12,15,19,2,"205",1,0));
            floor.add(new TableElement("wall",15,19,19,19,2,"205",1,0));
            floor.add(new TableElement("wall",23,19,27,19,2,"205",1,0));
            floor.add(new TableElement("wall",27,19,27,10,2,"205",1,0));
            floor.add(new TableElement("wall",27,10,25,10,2,"205",0,0));
            floor.add(new TableElement("wall",23,10,21,10,2,"205",0,0));
            floor.add(new TableElement("wall",21,10,21,12,2,"205",0,0));
            floor.add(new TableElement("wall",21,12,20,12,2,"205",0,0));
            floor.add(new TableElement("wall",17,12,15,12,2,"205",0,0));

            return floor;
        }
    @Test
    public void test14Run(){
        ArrayList<TableElement> floor = test14();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("**TEST 14 **");
            em.validateElements();
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println("Test 14 : " + e.getMessage());
        }
    }

    //Error: Room "202" outside the floor
    public ArrayList<TableElement> test15(){
            ArrayList<TableElement> floor = new ArrayList<>();
    //constructor: TableElement(type,x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
          //Floor exterior walls
            floor.add(new TableElement("door",4,12,9,12,2,"-1",1,1));
            floor.add(new TableElement("door",19,19,23,19,2,"-1",1,1));
            floor.add(new TableElement("door",12,1,19,1,2,"-1",1,1));
            floor.add(new TableElement("wall",1,1,1,12,2,"-1",1,0));
            floor.add(new TableElement("wall",1,12,4,12,2,"-1",1,0));
            floor.add(new TableElement("wall",9,12,15,12,2,"-1",1,0));
            floor.add(new TableElement("wall",15,12,15,19,2,"-1",1,0));
            floor.add(new TableElement("wall",15,19,19,19,2,"-1",1,0));
            floor.add(new TableElement("wall",23,19,27,19,2,"-1",1,0));
            floor.add(new TableElement("wall",27,19,27,1,2,"-1",1,0));
            floor.add(new TableElement("wall",27,1,19,1,2,"-1",1,0));
            floor.add(new TableElement("wall",12,1,1,1,2,"-1",1,0));


            //room "201"
            floor.add(new TableElement("door",10,2,10,5,2,"201",0,1));
            floor.add(new TableElement("wall",1,1,1,6,2,"201",1,0));
            floor.add(new TableElement("wall",1,6,10,6,2,"201",0,0));
            floor.add(new TableElement("wall",10,6,10,5,2,"201",0,0));
            floor.add(new TableElement("wall",10,2,10,1,2,"201",0,0));
            floor.add(new TableElement("wall",10,1,1,1,2,"201",1,0));

            //room "202"
            floor.add(new TableElement("door",4,12,9,12,2,"202",0,1));
            floor.add(new TableElement("door",10,10,10,7,2,"202",0,1));
            floor.add(new TableElement("wall",1,6,1,13,2,"202",1,0));
            floor.add(new TableElement("wall",1,13,10,13,2,"202",1,0));
            floor.add(new TableElement("wall",10,13,10,10,2,"202",0,0));
            floor.add(new TableElement("wall",10,7,10,6,2,"202",0,0));
            floor.add(new TableElement("wall",10,6,1,6,2,"202",0,0));

            //room "203"
            floor.add(new TableElement("door",10,2,10,5,2,"203",0,1));
            floor.add(new TableElement("door",10,7,10,10,2,"203",0,1));
            floor.add(new TableElement("door",17,12,20,12,2,"203",0,1));
            floor.add(new TableElement("door",12,1,19,1,2,"203",1,1));
            floor.add(new TableElement("wall",10,1,10,2,2,"203",0,0));
            floor.add(new TableElement("wall",10,5,10,7,2,"203",0,0));
            floor.add(new TableElement("wall",10,10,10,12,2,"203",0,0));
            floor.add(new TableElement("wall",10,12,15,12,2,"203",1,0));
            floor.add(new TableElement("wall",15,12,17,12,2,"203",0,0));
            floor.add(new TableElement("wall",20,12,21,12,2,"203",0,0));
            floor.add(new TableElement("wall",21,12,21,1,2,"203",0,0));
            floor.add(new TableElement("wall",21,1,19,1,2,"203",1,0));
            floor.add(new TableElement("wall",12,1,10,1,2,"203",1,0));


            //room "204"
            floor.add(new TableElement("door",23,10,25,10,2,"204",0,1));
            floor.add(new TableElement("wall",21,1,21,10,2,"204",0,0));
            floor.add(new TableElement("wall",21,10,23,10,2,"204",0,0));
            floor.add(new TableElement("wall",25,10,27,10,2,"204",0,0));
            floor.add(new TableElement("wall",27,10,27,1,2,"204",1,0));
            floor.add(new TableElement("wall",27,1,21,1,2,"204",1,0));


            //room "205"
            floor.add(new TableElement("door",17,12,20,12,2,"205",0,1));
            floor.add(new TableElement("door",19,19,23,19,2,"205",1,1));
            floor.add(new TableElement("door",23,12,25,12,2,"205",0,1));
            floor.add(new TableElement("wall",15,12,15,19,2,"205",1,0));
            floor.add(new TableElement("wall",15,19,19,19,2,"205",1,0));
            floor.add(new TableElement("wall",23,19,27,19,2,"205",1,0));
            floor.add(new TableElement("wall",27,19,27,10,2,"205",1,0));
            floor.add(new TableElement("wall",27,10,25,10,2,"205",0,0));
            floor.add(new TableElement("wall",23,10,21,10,2,"205",0,0));
            floor.add(new TableElement("wall",21,10,21,12,2,"205",0,0));
            floor.add(new TableElement("wall",21,12,20,12,2,"205",0,0));
            floor.add(new TableElement("wall",17,12,15,12,2,"205",0,0));

            return floor;
        }
    @Test
    public void test15Run(){
        ArrayList<TableElement> floor = test15();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("**TEST 15 **");
            em.validateElements();
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println("Test 15 : " + e.getMessage());
        }
    }
    
    public ArrayList<TableElement> test16(){
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
        floor.add(new TableElement("door",29,16,32,16,1,"103",1,0));
        floor.add(new TableElement("door",34,13,34,10,1,"103",1,0));
        floor.add(new TableElement("wall",27,16,29,16,1,"103",1,0));
        floor.add(new TableElement("wall",32,16,34,16,1,"103",1,0));
        floor.add(new TableElement("wall",34,16,34,13,1,"103",1,0));
        floor.add(new TableElement("wall",34,10,34,4,1,"103",1,0));
        floor.add(new TableElement("wall",34,4,27,4,1,"103",1,0));
        floor.add(new TableElement("wall",27,4,27,16,1,"103",1,0));

        return floor;
    }
    
    @Test 
    public void test16Run()throws DataNotValidException{
        ArrayList<TableElement> floor = test16();
        ElementManager em = new ElementManager(floor);
        try{
            System.out.println("**TEST 16 **");
            em.validateElements();
//            fail("DataNotValidException expected");
        }catch(DataNotValidException e){
            System.out.println("Test 16 : " + e.getMessage());
        }
    }
    }
