/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.util.ArrayList;
import tablerepresentation.TableElement;

/**
 *
 * @author dorin
 */
public class TestsArrayElements {
    //no errors
    public ArrayList<TableElement> test1(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement(10,27,15,27,1,-1,true,true));
        floor.add(new TableElement(2,17,2,22,1,-1,true,true));
        floor.add(new TableElement(2,27,10,27,1,-1,true,false));
        floor.add(new TableElement(15,27,24,27,1,-1,true,false));
        floor.add(new TableElement(24,27,24,18,1,-1,true,false));
        floor.add(new TableElement(24,18,36,18,1,-1,true,false));
        floor.add(new TableElement(36,18,36,2,1,-1,true,false));
        floor.add(new TableElement(36,2,2,2,1,-1,true,false));
        floor.add(new TableElement(2,2,2,17,1,-1,true,false));
        floor.add(new TableElement(2,22,2,27,1,-1,true,false));
        
        //room 101
        floor.add(new TableElement(5,11,10,11,1,101,false,true));
        floor.add(new TableElement(2,11,5,11,1,101,false,false));
        floor.add(new TableElement(10,11,13,11,1,101,false,false));
        floor.add(new TableElement(13,11,13,2,1,101,false,false));
        floor.add(new TableElement(13,2,2,2,1,101,false,false));
        floor.add(new TableElement(2,2,2,11,1,101,false,false));
        
        //room 102
        floor.add(new TableElement(24,9,24,5,1,101,false,true));
        floor.add(new TableElement(13,11,24,11,1,101,false,false));
        floor.add(new TableElement(24,11,24,9,1,101,false,false));
        floor.add(new TableElement(24,5,24,2,1,101,false,false));
        floor.add(new TableElement(24,2,13,2,1,101,false,false));
        floor.add(new TableElement(13,2,13,11,1,101,false,false));
        
        //room 103
        floor.add(new TableElement(29,16,32,16,1,101,false,true));
        floor.add(new TableElement(34,13,34,10,1,101,false,true));
        floor.add(new TableElement(27,16,29,16,1,101,false,false));
        floor.add(new TableElement(32,16,34,16,1,101,false,false));
        floor.add(new TableElement(34,16,34,13,1,101,false,false));
        floor.add(new TableElement(34,10,34,4,1,101,false,false));
        floor.add(new TableElement(34,4,27,4,1,101,false,false));
        floor.add(new TableElement(27,4,27,16,1,101,false,false));
        
        return floor;
    }
    
    //error: floor door not where it should be
    public ArrayList<TableElement> test2(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement(10,28,15,28,1,-1,true,true));
        floor.add(new TableElement(2,17,2,22,1,-1,true,true));
        floor.add(new TableElement(2,27,10,27,1,-1,true,false));
        floor.add(new TableElement(15,27,24,27,1,-1,true,false));
        floor.add(new TableElement(24,27,24,18,1,-1,true,false));
        floor.add(new TableElement(24,18,36,18,1,-1,true,false));
        floor.add(new TableElement(36,18,36,2,1,-1,true,false));
        floor.add(new TableElement(36,2,2,2,1,-1,true,false));
        floor.add(new TableElement(2,2,2,17,1,-1,true,false));
        floor.add(new TableElement(2,22,2,27,1,-1,true,false));
        
        //room 101
        floor.add(new TableElement(5,11,10,11,1,101,false,true));
        floor.add(new TableElement(2,11,5,11,1,101,false,false));
        floor.add(new TableElement(10,11,13,11,1,101,false,false));
        floor.add(new TableElement(13,11,13,2,1,101,false,false));
        floor.add(new TableElement(13,2,2,2,1,101,false,false));
        floor.add(new TableElement(2,2,2,11,1,101,false,false));
        
        //room 102
        floor.add(new TableElement(24,9,24,5,1,101,false,true));
        floor.add(new TableElement(13,11,24,11,1,101,false,false));
        floor.add(new TableElement(24,11,24,9,1,101,false,false));
        floor.add(new TableElement(24,5,24,2,1,101,false,false));
        floor.add(new TableElement(24,2,13,2,1,101,false,false));
        floor.add(new TableElement(13,2,13,11,1,101,false,false));
        
        //room 103
        floor.add(new TableElement(29,16,32,16,1,101,false,true));
        floor.add(new TableElement(34,13,34,10,1,101,false,true));
        floor.add(new TableElement(27,16,29,16,1,101,false,false));
        floor.add(new TableElement(32,16,34,16,1,101,false,false));
        floor.add(new TableElement(34,16,34,13,1,101,false,false));
        floor.add(new TableElement(34,10,34,4,1,101,false,false));
        floor.add(new TableElement(34,4,27,4,1,101,false,false));
        floor.add(new TableElement(27,4,27,16,1,101,false,false));
        
        return floor;
    }
    
    //error: floor with no doors
    public ArrayList<TableElement> test3(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement(10,27,15,27,1,-1,true,false));
        floor.add(new TableElement(2,17,2,22,1,-1,true,false));
        floor.add(new TableElement(2,27,10,27,1,-1,true,false));
        floor.add(new TableElement(15,27,24,27,1,-1,true,false));
        floor.add(new TableElement(24,27,24,18,1,-1,true,false));
        floor.add(new TableElement(24,18,36,18,1,-1,true,false));
        floor.add(new TableElement(36,18,36,2,1,-1,true,false));
        floor.add(new TableElement(36,2,2,2,1,-1,true,false));
        floor.add(new TableElement(2,2,2,17,1,-1,true,false));
        floor.add(new TableElement(2,22,2,27,1,-1,true,false));
        
        //room 101
        floor.add(new TableElement(5,11,10,11,1,101,false,true));
        floor.add(new TableElement(2,11,5,11,1,101,false,false));
        floor.add(new TableElement(10,11,13,11,1,101,false,false));
        floor.add(new TableElement(13,11,13,2,1,101,false,false));
        floor.add(new TableElement(13,2,2,2,1,101,false,false));
        floor.add(new TableElement(2,2,2,11,1,101,false,false));
        
        //room 102
        floor.add(new TableElement(24,9,24,5,1,101,false,true));
        floor.add(new TableElement(13,11,24,11,1,101,false,false));
        floor.add(new TableElement(24,11,24,9,1,101,false,false));
        floor.add(new TableElement(24,5,24,2,1,101,false,false));
        floor.add(new TableElement(24,2,13,2,1,101,false,false));
        floor.add(new TableElement(13,2,13,11,1,101,false,false));
        
        //room 103
        floor.add(new TableElement(29,16,32,16,1,101,false,true));
        floor.add(new TableElement(34,13,34,10,1,101,false,true));
        floor.add(new TableElement(27,16,29,16,1,101,false,false));
        floor.add(new TableElement(32,16,34,16,1,101,false,false));
        floor.add(new TableElement(34,16,34,13,1,101,false,false));
        floor.add(new TableElement(34,10,34,4,1,101,false,false));
        floor.add(new TableElement(34,4,27,4,1,101,false,false));
        floor.add(new TableElement(27,4,27,16,1,101,false,false));
        
        return floor;
    }
    
    //error: room 101 no door
    public ArrayList<TableElement> test4(){
        ArrayList<TableElement> floor = new ArrayList<>();
        //constructor: TableElement(x1,y1,x2,y2,floorNumber,room,isExterior,isExit)
        //Floor exterior walls
        floor.add(new TableElement(10,27,15,27,1,-1,true,true));
        floor.add(new TableElement(2,17,2,22,1,-1,true,true));
        floor.add(new TableElement(2,27,10,27,1,-1,true,false));
        floor.add(new TableElement(15,27,24,27,1,-1,true,false));
        floor.add(new TableElement(24,27,24,18,1,-1,true,false));
        floor.add(new TableElement(24,18,36,18,1,-1,true,false));
        floor.add(new TableElement(36,18,36,2,1,-1,true,false));
        floor.add(new TableElement(36,2,2,2,1,-1,true,false));
        floor.add(new TableElement(2,2,2,17,1,-1,true,false));
        floor.add(new TableElement(2,22,2,27,1,-1,true,false));
        
        //room 101
        floor.add(new TableElement(5,11,10,11,1,101,false,true));
        floor.add(new TableElement(2,11,5,11,1,101,false,false));
        floor.add(new TableElement(10,11,13,11,1,101,false,false));
        floor.add(new TableElement(13,11,13,2,1,101,false,false));
        floor.add(new TableElement(13,2,2,2,1,101,false,false));
        floor.add(new TableElement(2,2,2,11,1,101,false,false));
        
        //room 102
        floor.add(new TableElement(24,9,24,5,1,101,false,true));
        floor.add(new TableElement(13,11,24,11,1,101,false,false));
        floor.add(new TableElement(24,11,24,9,1,101,false,false));
        floor.add(new TableElement(24,5,24,2,1,101,false,false));
        floor.add(new TableElement(24,2,13,2,1,101,false,false));
        floor.add(new TableElement(13,2,13,11,1,101,false,false));
        
        //room 103
        floor.add(new TableElement(29,16,32,16,1,101,false,true));
        floor.add(new TableElement(34,13,34,10,1,101,false,true));
        floor.add(new TableElement(27,16,29,16,1,101,false,false));
        floor.add(new TableElement(32,16,34,16,1,101,false,false));
        floor.add(new TableElement(34,16,34,13,1,101,false,false));
        floor.add(new TableElement(34,10,34,4,1,101,false,false));
        floor.add(new TableElement(34,4,27,4,1,101,false,false));
        floor.add(new TableElement(27,4,27,16,1,101,false,false));
        
        return floor;
    }
    
    //error: room 102 door can't be accessed
    public ArrayList<TableElement> test5(){
        ArrayList<TableElement> floor = new ArrayList<>();
        
        return floor;
    }
    
    //error: room 101 and 102 badly placed
    public ArrayList<TableElement> test6(){
        ArrayList<TableElement> floor = new ArrayList<>();
        
        return floor;
    }
    
    //error: tow rooms (101) with same number
    public ArrayList<TableElement> test7(){
        ArrayList<TableElement> floor = new ArrayList<>();
        
        return floor;
    }
    
    //error: room 102 badly placed
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
}
