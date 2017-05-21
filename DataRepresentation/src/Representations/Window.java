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
public class Window extends Wall{
    public Window(Point lpoint, Point rpoint){
        super(lpoint, rpoint);
    }
    
    public Window(Window toCopy){
        super(toCopy);
    } 
}
