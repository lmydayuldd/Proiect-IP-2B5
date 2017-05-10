package DataRepresentationBackend.Models;

import java.io.Serializable;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 08-May-17.
 */
public class TemporaryData {
    public String elementType, room;
    public Integer x1, y1, x2, y2, floor, isExitWay;

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = x1;
    }

    public Integer getY1() {
        return y1;
    }

    public void setY1(Integer y1) {
        this.y1 = y1;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    public Integer getY2() {
        return y2;
    }

    public void setY2(Integer y2) {
        this.y2 = y2;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getIsExitWay() {
        return isExitWay;
    }

    public void setIsExitWay(Integer isExitWay) {
        this.isExitWay = isExitWay;
    }
}
