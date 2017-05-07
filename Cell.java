package modul3;


import java.util.Vector;

public class Cell {

/**
 * This encodes the walls of the current cell.
 * This variable takes values from 0 to 15, encoding 4 bits representing the four walls (North, East, West and South)
 * If the varriable equals 7, then it means that around this cell there are 3 walls
 * 7 in base 2 is 1110
 * The walls that exist are N, E, W.
 * 
 */
public int walls;

/**
 * If free = 1, then this cell belongs to the building. Otherwise, it is a cell outside the building.
 */
public int free;

    public Vector  myMatrix;

}