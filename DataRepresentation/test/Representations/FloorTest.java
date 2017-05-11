/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Representations;

import CustomExceptions.DataNotValidException;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Procop Vladimir
 */
public class FloorTest {
    
    public FloorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of equals method, of class Floor.
     */
    @Test
    public void testEquals() {
        
    }

    /**
     * Test of addRoom method, of class Floor.
     */
    @Test
    public void testAddRoom() throws Exception {
        
    }

    /**
     * Test of deleteRoom method, of class Floor.
     */
    @Test
    public void testDeleteRoom() throws Exception {
        
    }

    /**
     * Test of getFloorLevel method, of class Floor.
     */
    @Test
    public void testGetFloorLevel() {
        
    }

    /**
     * Test of setFloorLevel method, of class Floor.
     */
    @Test
    public void testSetFloorLevel() {
        
    }

    /**
     * Test of getFloorHeight method, of class Floor.
     */
    @Test
    public void testGetFloorHeight() {
        
    }

    /**
     * Test of setFloorHeight method, of class Floor.
     */
    @Test
    public void testSetFloorHeight() throws Exception {
        
    }

    /**
     * Test of getRooms method, of class Floor.
     */
    @Test
    public void testGetRooms() {
        
    }

    /**
     * Test of setRooms method, of class Floor.
     */
    @Test
    public void testSetRooms() throws Exception {
        
    }

    /**
     * Test of validate method, of class Floor.
     */
    @Test
    public void testValidate1() throws DataNotValidException {
        // TEST1 PENTRU ETAJ ///////////////////////////////////////
        ArrayList<Wall> walls = new ArrayList<Wall>();
        walls.add(new Door(new Point(1, 1), new Point(2, 0), true));
        walls.add(new Wall(new Point(2, 0), new Point(3, 0)));
        walls.add(new Wall(new Point(3, 0), new Point(2, 2)));
        walls.add(new Wall(new Point(2, 2), new Point(1, 1)));
        Room room1 = new Room(walls);
        
        ArrayList<Wall> walls2 = new ArrayList<Wall>();
        walls2.add(new Door(new Point(1, 1), new Point(2, 0), true));
        walls2.add(new Wall(new Point(2, 0), new Point(2, 2)));
        walls2.add(new Wall(new Point(2, 2), new Point(1, 1)));
        Room room2 = new Room(walls2);

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        Floor instance = new Floor();
        instance.setRooms(rooms);
        // TODO review the generated test code and remove the default call to fail.
        try{
            Floor.validate(instance);
            fail("DataNotValidExceptoin expected");
        }catch(DataNotValidException e){
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testValidate2() throws DataNotValidException {
        //  TEST2 PENTRU ETAJ ///////////////////////////////////////
        ArrayList<Wall> walls = new ArrayList<Wall>();
        walls.add(new Door(new Point(1, 1), new Point(2, 0), true));
        walls.add(new Wall(new Point(2, 0), new Point(3, 0)));
        walls.add(new Wall(new Point(3, 0), new Point(2, 2)));
        walls.add(new Wall(new Point(2, 2), new Point(1, 1)));
        Room room1 = new Room(walls);
        
        ArrayList<Wall> walls2 = new ArrayList<Wall>();
        walls2.add(new Door(new Point(1, 1), new Point(2, 0), true));
        walls2.add(new Wall(new Point(2, 0), new Point(1, 0)));
        walls2.add(new Wall(new Point(1, 0), new Point(1, 1)));
        Room room2 = new Room(walls2);

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        Floor instance = new Floor();
        instance.setRooms(rooms);
        // TODO review the generated test code and remove the default call to fail.
        try{
            assertTrue(Floor.validate(instance));
        }catch(DataNotValidException e){
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testValidate3() throws DataNotValidException {
        //  TEST3 PENTRU ETAJ ///////////////////////////////////////
        ArrayList<Wall> walls = new ArrayList<Wall>();
        walls.add(new Wall(new Point(0, 1), new Point(0, 2)));
        walls.add(new Wall(new Point(0, 2), new Point(1, 2)));
        walls.add(new Wall(new Point(1, 2), new Point(1, 1)));
        walls.add(new Door(new Point(1, 1), new Point(0, 1), false));
        Room room1 = new Room(walls);
        
        ArrayList<Wall> walls2 = new ArrayList<Wall>();
        walls2.add(new Door(new Point(1, 1), new Point(0, 1), false));
        walls2.add(new Wall(new Point(0, 1), new Point(0, 0)));
        walls2.add(new Wall(new Point(0, 0), new Point(1, 0)));
        walls2.add(new Door(new Point(1, 0), new Point(1, 1), false));
        Room room2 = new Room(walls2);
        
        ArrayList<Wall> walls3 = new ArrayList<Wall>();
        walls3.add(new Door(new Point(2, 1), new Point(1, 1), false));
        walls3.add(new Door(new Point(1, 1), new Point(1, 0), false));
        walls3.add(new Wall(new Point(1, 0), new Point(2, 0)));
        walls3.add(new Door(new Point(2, 0), new Point(2, 1), false));
        Room room3 = new Room(walls3);
        
        ArrayList<Wall> walls4 = new ArrayList<Wall>();
        walls4.add(new Door(new Point(3, 1), new Point(2, 1), false));
        walls4.add(new Door(new Point(2, 1), new Point(2, 0), false));
        walls4.add(new Wall(new Point(2, 0), new Point(3, 0)));
        walls4.add(new Door(new Point(3, 0), new Point(3, 1), false));
        Room room4 = new Room(walls4);
        
        ArrayList<Wall> walls5 = new ArrayList<Wall>();
        walls5.add(new Door(new Point(4, 1), new Point(3, 1), false));
        walls5.add(new Door(new Point(3, 1), new Point(3, 0), false));
        walls5.add(new Wall(new Point(3, 0), new Point(4, 0)));
        walls5.add(new Door(new Point(4, 0), new Point(4, 1), false));
        Room room5 = new Room(walls5);
        
        ArrayList<Wall> walls6 = new ArrayList<Wall>();
        walls6.add(new Door(new Point(5, 1), new Point(4, 1), false));
        walls6.add(new Door(new Point(4, 1), new Point(4, 0), false));
        walls6.add(new Wall(new Point(4, 0), new Point(5, 0)));
        walls6.add(new Wall(new Point(5, 0), new Point(3, 0)));
        Room room6 = new Room(walls6);
        
        
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        rooms.add(room6);
        Floor instance = new Floor();
        instance.setRooms(rooms);
        // TODO review the generated test code and remove the default call to fail.
        try{
            Floor.validate(instance);
            fail("DataNotValidExceptoin expected");
        }catch(DataNotValidException e){
            System.out.println(e.getMessage());
        }
    }
    
    @Test
    public void testValidate4() throws DataNotValidException {
        //  TEST3 PENTRU ETAJ ///////////////////////////////////////
        ArrayList<Wall> walls = new ArrayList<Wall>();
        walls.add(new Wall(new Point(0, 1), new Point(0, 2)));
        walls.add(new Wall(new Point(0, 2), new Point(1, 2)));
        walls.add(new Wall(new Point(1, 2), new Point(1, 1)));
        walls.add(new Door(new Point(1, 1), new Point(0, 1), false));
        Room room1 = new Room(walls);
        
        ArrayList<Wall> walls2 = new ArrayList<Wall>();
        walls2.add(new Door(new Point(1, 1), new Point(0, 1), false));
        walls2.add(new Wall(new Point(0, 1), new Point(0, 0)));
        walls2.add(new Wall(new Point(0, 0), new Point(1, 0)));
        walls2.add(new Door(new Point(1, 0), new Point(1, 1), false));
        Room room2 = new Room(walls2);
        
        ArrayList<Wall> walls3 = new ArrayList<Wall>();
        walls3.add(new Door(new Point(2, 1), new Point(1, 1), false));
        walls3.add(new Door(new Point(1, 1), new Point(1, 0), false));
        walls3.add(new Wall(new Point(1, 0), new Point(2, 0)));
        walls3.add(new Door(new Point(2, 0), new Point(2, 1), false));
        Room room3 = new Room(walls3);
        
        ArrayList<Wall> walls4 = new ArrayList<Wall>();
        walls4.add(new Door(new Point(3, 1), new Point(2, 1), false));
        walls4.add(new Door(new Point(2, 1), new Point(2, 0), false));
        walls4.add(new Wall(new Point(2, 0), new Point(3, 0)));
        walls4.add(new Door(new Point(3, 0), new Point(3, 1), false));
        Room room4 = new Room(walls4);
        
        ArrayList<Wall> walls5 = new ArrayList<Wall>();
        walls5.add(new Door(new Point(4, 1), new Point(3, 1), false));
        walls5.add(new Door(new Point(3, 1), new Point(3, 0), false));
        walls5.add(new Wall(new Point(3, 0), new Point(4, 0)));
        walls5.add(new Door(new Point(4, 0), new Point(4, 1), false));
        Room room5 = new Room(walls5);
        
        ArrayList<Wall> walls6 = new ArrayList<Wall>();
        walls6.add(new Door(new Point(5, 1), new Point(4, 1), false));
        walls6.add(new Door(new Point(4, 1), new Point(4, 0), false));
        walls6.add(new Wall(new Point(4, 0), new Point(5, 0)));
        walls6.add(new Wall(new Point(5, 0), new Point(5, 1)));
        Room room6 = new Room(walls6);
        
        
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);
        rooms.add(room6);
        Floor instance = new Floor();
        instance.setRooms(rooms);
        // TODO review the generated test code and remove the default call to fail.
        try{
            
            assertTrue(Floor.validate(instance));
            
        }catch(DataNotValidException e){
            System.out.println(e.getMessage());
        }
    }
}
