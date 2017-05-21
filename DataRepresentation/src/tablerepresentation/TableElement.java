/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablerepresentation;

public class TableElement {
    public String elementType = "";
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public int floorNumber;
    public String room;
    public int isExterior;
    public int isExit;
    
    public TableElement(String elementType, int xx1, int yy1, int xx2, int yy2, int floor, String theRoom, int exterior, int exit){
        this.elementType = elementType; 
        x1= xx1;
        x2 = xx2;
        y1 = yy1;
        y2 = yy2;
        floorNumber = floor;
        room = theRoom;
        isExterior = exterior;
        isExit = exit;
    }
    
    public String toString(){
        return ("TableElement : elementType=" + this.elementType + " , x1 = " + this.x2 + " , y1 = " + this.y1 + " , x2 = " + this.x2 + " , y2 = " + this.y2 + " , room = " + this.room + " , isExterior = " + this.isExterior + " , isExit = " + this.isExit);
}
}
