package DataRepresentationBackend.Logic.Representations;

import java.util.ArrayList;

/**
 * @author Procop Vladimir
 */
public class Building {

    private ArrayList<Floor> floors = new ArrayList<>();

    public ArrayList<Floor> getFloors() {/**Returns floors of the building*/
        return floors;
    }

    public void setFloors(ArrayList<Floor> floors) {/**Set floors of the building*/
        //...
        this.floors = floors;
    }
}