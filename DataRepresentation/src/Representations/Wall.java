/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Representations;

import java.awt.geom.Line2D;

/**
 *
 * @author Procop Vladimir
 */
public class Wall {
    protected Point leftPoint = null;
    protected Point rightPoint = null;
    
    public Wall(){/**Construct wall which is or isn't an exit-way*/
    }
    
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
        return (this.leftPoint.equals(w.leftPoint)) && (this.rightPoint.equals(w.rightPoint));
    }
    
    @Override
    public String toString(){
        return ("Wall: lpoint(" + this.leftPoint.toString().substring(7) + "), rpoint(" + this.rightPoint.toString().substring(7)+")");
    }
    
    public static Line2D toLine2D(Wall wall){
        return (new Line2D.Double(wall.leftPoint.getX(), wall.leftPoint.getY(), wall.rightPoint.getX(), wall.rightPoint.getY()));
    }
}
