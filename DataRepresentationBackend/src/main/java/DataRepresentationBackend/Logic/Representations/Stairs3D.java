package DataRepresentationBackend.Logic.Representations;

import DataRepresentationBackend.Logic.CustomExceptions.DataNotValidException;
import DataRepresentationBackend.Logic.TableRepresentation.DataNotValidExceptionLogger;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Procop Vladimir
 */
public class Stairs3D {

    private int bottom = Integer.MAX_VALUE;
    private int top = Integer.MIN_VALUE;
    private String name = "";
    private ArrayList<Room> stairs2D = null;

    public Stairs3D(String name) {
        this.name = name;
        this.stairs2D = new ArrayList<>();
    }

    public void addStairs2D(Room newStairs2D) throws DataNotValidException {
        if (!newStairs2D.getRoomName().equals(this.name)) {
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" + newStairs2D + "] was tried to be added as a Room to Stairs3D with different name");
        }
        if (this.stairs2D.size() > 0) {
            Area areaHereToBeOverwritten = new Area(Room.toPath2D(this.stairs2D.get(0)));
            Area areaHere = new Area(Room.toPath2D(this.stairs2D.get(0)));
            Area areaToBeAdded = new Area(Room.toPath2D(newStairs2D));
            areaHereToBeOverwritten.intersect(areaToBeAdded);
            if ((!areaHereToBeOverwritten.equals(areaHere)) || (!areaHereToBeOverwritten.equals(areaToBeAdded))) {
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("[" + newStairs2D + "] was tried to be added as a Room that is part of some stairs BUT it does NOT overlap those from different floors");
            }
        } else {
            this.name = newStairs2D.getRoomName();
        }
        if (newStairs2D.getFloorNumber() < this.bottom) {
            this.bottom = newStairs2D.getFloorNumber();
        }
        if (newStairs2D.getFloorNumber() > this.top) {
            this.top = newStairs2D.getFloorNumber();
        }
        this.stairs2D.add(newStairs2D);
    }


    public static boolean validate(Stairs3D stairs3D) throws DataNotValidException {
        if (stairs3D.stairs2D.size() < 2) {
            DataNotValidExceptionLogger.getInstance().addExceptionMessage("For Stairs " + stairs3D.getStairs3DName() + " there is only one room specified, on a single floor!");
        }
        Collections.sort(stairs3D.stairs2D, (st1, st2) -> {
            return (st1.getFloorNumber() - st2.getFloorNumber());
        });
        for (int i = 1; i < stairs3D.stairs2D.size(); i++) {
            if (stairs3D.stairs2D.get(i).getFloorNumber() - stairs3D.stairs2D.get(i - 1).getFloorNumber() != 1) {
                DataNotValidExceptionLogger.getInstance().addExceptionMessage("For Stairs " + stairs3D.getStairs3DName() + " the difference between floor numbers is not 1 !");
            }
        }
        return true;
    }

    public String getStairs3DName() {
        return this.name;
    }
}