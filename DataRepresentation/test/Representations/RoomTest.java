/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Representations;

import CustomExceptions.DataNotValidException;
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
public class RoomTest {
    
    public RoomTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of equals method, of class Room.
     */
    @Test
    public void testEquals() {
        
    }

    /**
     * Test of addWall method, of class Room.
     */
    @Test
    public void testAddWall() throws Exception {
        
    }

    /**
     * Test of deleteWall method, of class Room.
     */
    @Test
    public void testDeleteWall() throws Exception {
       
    }

    /**
     * Test of toString method, of class Room.
     */
    @Test
    public void testToString() {
       
    }

    /**
     * Test of getWalls method, of class Room.
     */
    @Test
    public void testGetWalls() {
        
    }

    /**
     * Test of setWalls method, of class Room.
     */
    @Test
    public void testSetWalls() {
        
    }

    /**
     * Test of validate method, of class Room.
     */
    @Test
    public void testValidate1() throws Exception {
        System.out.println("validate");
        ArrayList<Wall> walls = new ArrayList<Wall>();
        walls.add(new Door(new Point(1, 1), new Point(2, 0), 1));
        walls.add(new Wall(new Point(2, 0), new Point(3, 0)));
        walls.add(new Wall(new Point(3, 0), new Point(2, 2)));
        walls.add(new Wall(new Point(2, 2), new Point(1, 1)));
        Room room1 = new Room(walls);
        
        boolean expResult = true;
        boolean result = Room.validate(room1);
        assertEquals(expResult, result);   
    }
    
    @Test
    public void testValidate2() throws Exception {
        ArrayList<Wall> walls = new ArrayList<Wall>();
        walls.add(new Door(new Point(1, 1), new Point(2, 0), 1));
        walls.add(new Wall(new Point(2, 0), new Point(1, 0)));
        walls.add(new Wall(new Point(1, 0), new Point(2, 2)));
        Room room = new Room(walls);
        
        try{
            boolean result = Room.validate(room);
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testValidate3() throws Exception {
        System.out.println("validate");
        ArrayList<Wall> walls = new ArrayList<Wall>();
        walls.add(new Window(new Point(1, 1), new Point(2, 0)));
        walls.add(new Wall(new Point(2, 0), new Point(3, 0)));
        walls.add(new Wall(new Point(3, 0), new Point(2, 2)));
        walls.add(new Wall(new Point(2, 2), new Point(1, 1)));
        Room room = new Room(walls);
        
        try{
            boolean result = Room.validate(room);
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println(e.getMessage());
        }   
    }
    @Test
    public void testValidate4() throws Exception {
        System.out.println("validate");
        ArrayList<Wall> walls = new ArrayList<Wall>();
        walls.add(new Door(new Point(1, 1), new Point(2, 0), 1));
        walls.add(new Wall(new Point(2, 0), new Point(3, 0)));
        walls.add(new Wall(new Point(3, 0), new Point(2, 2)));
        Room room = new Room(walls);
        
        try{
            boolean result = Room.validate(room);
            fail("DataNotValidException expected !");
        }catch(DataNotValidException e){
            System.out.println(e.getMessage());
        }   
    }
    /**
     * Test of toPath2D method, of class Room.
     */
    @Test
    public void testToPath2D() {
        
    }
    
}
