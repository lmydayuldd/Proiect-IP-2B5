package DataRepresentationBackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 08-May-17.
 */
public class TemporaryData {
    public String type, room;
    public Integer x1, y1, x2, y2, floor, isExitWay, isExterior;

    public TemporaryData() {
    }

    public TemporaryData(String type, String room, Integer x1, Integer y1, Integer x2, Integer y2, Integer floor, Integer isExitWay, Integer isExterior) {
        this.type = type;
        this.room = room;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.floor = floor;
        this.isExitWay = isExitWay;
        this.isExterior = isExterior;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getIsExterior() {
        return isExterior;
    }

    public void setIsExterior(Integer isExterior) {
        this.isExterior = isExterior;
    }

    @JsonIgnore
    public Boolean isValid() {
        return !(this.getType() == null || this.getFloor() == null || this.getIsExitWay() == null ||
                this.getIsExterior() == null || this.getRoom() == null || this.getX1() == null ||
                this.getX2() == null || this.getY1() == null || this.getY2() == null ||
                getType().isEmpty() || getRoom().isEmpty());
    }

    @JsonIgnore
    public Boolean containValidData() {
        if (!(getType().toLowerCase().equals("wall") || getType().toLowerCase().equals("door") || getType().toLowerCase().equals("window")
                || getType().toLowerCase().equals("stairs") || getType().toLowerCase().equals("room"))) return false;
        return true;
    }

    @JsonIgnore
    public Boolean coordinatesParametersValid() {
       if (getX1() < 0 || getX2() < 0 || getY1() < 0 || getY2() < 0) return false;
       if (getX1() > 100 || getX2() > 100 || getY1() > 100 || getY2() > 100) return false;
       return true;
    }
}
