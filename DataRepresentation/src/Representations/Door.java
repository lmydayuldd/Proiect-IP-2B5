/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Representations;

/**
 *
 * @author Procop Vladimir
 */
public class Door extends Wall{
    private int exitWay = 0;
        
    public Door(Point lpoint, Point rpoint, int exit){/**Construct door with points*/
        super(lpoint, rpoint);
        this.exitWay = exit;
    }
    
    public Door(Door toCopy, int exit){/**Construct door which is a deep copy of another door*/
        super(toCopy);
        this.exitWay = exit;
    }
    
    public int isExit(){/**True if the door is an exit way, false otherwise*/
        return this.exitWay;
    }
}
