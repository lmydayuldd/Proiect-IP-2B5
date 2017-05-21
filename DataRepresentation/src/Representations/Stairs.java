/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Representations;

import CustomExceptions.DataNotValidException;
import java.util.ArrayList;

/**
 *
 * @author Procop Vladimir
 */
public class Stairs extends Wall {
     
    public Stairs(Point lpoint, Point rpoint){
        super(lpoint, rpoint);
    }
    
    public Stairs(Stairs toCopy){
        super(toCopy);
    } 
}
