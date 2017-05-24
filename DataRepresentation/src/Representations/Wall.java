/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Representations;

import CustomExceptions.DataNotValidException;
import java.awt.geom.Line2D;
import tablerepresentation.DataNotValidExceptionLogger;

/**
 *
 * @author Procop Vladimir
 */
public class Wall {
    protected Point leftPoint = null;
    protected Point rightPoint = null;
    protected int floorNumber;
    protected String roomName;
    protected int isExterior;
    public boolean isStairs = false;
    
    public Wall(Point lpoint, Point rpoint){/**Construct wall with points*/
        this.leftPoint = new Point(lpoint);
        this.rightPoint = new Point(rpoint);
    }
    
    public Wall(Wall toCopy){/**Construct wall which is a deep copy of another door*/
        this.leftPoint = new Point(toCopy.getLeftPoint());
        this.rightPoint = new Point(toCopy.getRightPoint());
    }

    /**
     * @return the leftPoint
     */
    public Point getLeftPoint() {
        return leftPoint;
    }

    /**
     * @param leftPoint the leftPoint to set
     */
    public void setLeftPoint(Point leftPoint) {
        this.leftPoint = leftPoint;
    }

    /**
     * @return the rightPoint
     */
    public Point getRightPoint() {
        return rightPoint;
    }

    /**
     * @param rightPoint the rightPoint to set
     */
    public void setRightPoint(Point rightPoint) {
        this.rightPoint = rightPoint;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof Wall)) return false;
        Wall w = (Wall)o;
        return (this.leftPoint.equals(w.leftPoint)) && (this.rightPoint.equals(w.rightPoint) &&(w.getFloorNumber()==this.getFloorNumber()) && (this.getRoomName() == w.getRoomName()) );
    }
    
    @Override
    public String toString(){
        return ("Wall: lpoint(" + this.leftPoint.toString().substring(7) + "), rpoint(" + this.rightPoint.toString().substring(7)+"), room: " + this.roomName);
    }
    
    public static Line2D toLine2D(Wall wall){/**Returns equivalent representation of Wall as equivalent Line2D object*/
        return (new Line2D.Double(wall.leftPoint.getX(), wall.leftPoint.getY(), wall.rightPoint.getX(), wall.rightPoint.getY()));
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) throws DataNotValidException {
        if((floorNumber < -10) || (floorNumber > 200)){
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("Floor number must be between -10 and 200");
        }
        this.floorNumber = floorNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int isExterior() {
        return isExterior;
    }

    public void setIsExterior(int isExterior) {
        this.isExterior = isExterior;
    }
}
