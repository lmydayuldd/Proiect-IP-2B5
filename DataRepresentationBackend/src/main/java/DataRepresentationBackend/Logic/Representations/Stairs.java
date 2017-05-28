package DataRepresentationBackend.Logic.Representations;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 28-May-17.
 */

import DataRepresentationBackend.Logic.CustomExceptions.DataNotValidException;
import DataRepresentationBackend.Logic.TableRepresentation.DataNotValidExceptionLogger;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Procop Vladimir
 */
public class Stairs extends Wall {

    public Stairs(Point lpoint, Point rpoint) {
        super(lpoint, rpoint);
        this.isStairs = true;
    }

    public Stairs(Stairs toCopy) {
        super(toCopy);
        this.isStairs = true;
    }

    public static boolean validate(ArrayList<Room> stairs2d) throws DataNotValidException {
        HashMap<String, ArrayList<Room>> stairs3d = new HashMap<>();
        Room rost = null;
        ArrayList<Room> tempList = null;
        for (int i = 0; i < stairs2d.size(); i++) {
            rost = stairs2d.get(i);
            if (stairs3d.containsKey(rost.getRoomName())) {
                stairs3d.get(rost.getRoomName()).add(rost);
            } else {
                tempList = new ArrayList<>();
                tempList.add(rost);
                stairs3d.put(rost.getRoomName(), tempList);
            }
        }

        for (Map.Entry<String, ArrayList<Room>> entry : stairs3d.entrySet()) {
            String key = entry.getKey();
            ArrayList<Room> value = entry.getValue();
            Area a0 = new Area(Room.toPath2D(value.get(0)));
            for (int i = 1; i < value.size(); i++) {
                if (!a0.equals(Room.toPath2D(value.get(i)))) {
                    DataNotValidExceptionLogger.getInstance().addExceptionMessage("Stairs ");
                }
            }
        }
        return true;
    }
}

