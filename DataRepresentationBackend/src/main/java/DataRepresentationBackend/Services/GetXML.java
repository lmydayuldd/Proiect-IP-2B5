package DataRepresentationBackend.Services;

import CustomExceptions.DataNotValidException;
import Representations.Floor;
import Representations.Room;
import Representations.Wall;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tablerepresentation.ElementFilter;
import tablerepresentation.TableElement;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.nio.file.FileAlreadyExistsException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Razvan Romanescu.
 */
public class GetXML {

    public String getXML() {

        try {
            Document xmlDoc = buildXML();

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(xmlDoc), new StreamResult(writer));

            return writer.getBuffer().toString().replaceAll("\n|\r", "");

        }   catch (Exception e) {
            return ("Really poor exception handling: " + e.toString());
        }
    }

    private Document buildXML() throws Exception {
        Document xmlDoc = new DocumentImpl();

        ConcreteDatabaseService concreteDB = new ConcreteDatabaseService();

        ArrayList<TableElement> listOfEverything = concreteDB.getTemporaryDataTable();
        ElementFilter elementFilter = new ElementFilter(listOfEverything);
        ArrayList<Wall> listOfWalls = elementFilter.getWalls();
        ArrayList<Room> listOfRooms = elementFilter.getRooms(listOfWalls);
        ArrayList<Integer> listOfFloors = new ArrayList<>();

        Integer i = 0, j = 0, t = 0, ok = 0;

        for(i = 0; i < listOfEverything.size(); i++) {
            ok = 1;
            for(j = 0; j < listOfFloors.size(); j++) {
                if(listOfFloors.get(j) == listOfEverything.get(i).floorNumber)
                    ok = 0;
            }
            if(ok == 1)
                listOfFloors.add(listOfEverything.get(i).floorNumber);
        }

        listOfFloors.sort(Integer::compareTo);

        /* Creating the root element */
        Element rootElement = xmlDoc.createElement("floors");
        xmlDoc.appendChild(rootElement);

        for(i = 0; i < listOfFloors.size(); i++) {
            Element floorElement = xmlDoc.createElement("floor");
            floorElement.setAttribute("number", String.valueOf(listOfFloors.get(i)));

            for(j = 0; j < listOfRooms.size(); j++) {
                if(listOfRooms.get(j).getFloorNumber() == listOfFloors.get(i)) {
                    Element roomElement = xmlDoc.createElement("room");
                    Element nameElement = xmlDoc.createElement("name");

                    nameElement.appendChild(xmlDoc.createTextNode(listOfRooms.get(j).getRoomName()));

                    roomElement.appendChild(nameElement);

                    for(t = 0; t < listOfEverything.size(); t++) {
                        if(listOfEverything.get(t).floorNumber == listOfFloors.get(i)
                                && listOfEverything.get(t).room.equals(listOfRooms.get(j).getRoomName())) {

                            Element typeElement = xmlDoc.createElement("type");

                            typeElement.setAttribute("name", listOfEverything.get(t).elementType);

                            Element x1Element = xmlDoc.createElement("x1");
                            Element y1Element = xmlDoc.createElement("y1");
                            Element x2Element = xmlDoc.createElement("x2");
                            Element y2Element = xmlDoc.createElement("y2");

                            x1Element.appendChild(xmlDoc.createTextNode(String.valueOf(listOfEverything.get(t).x1)));
                            y1Element.appendChild(xmlDoc.createTextNode(String.valueOf(listOfEverything.get(t).y1)));
                            x2Element.appendChild(xmlDoc.createTextNode(String.valueOf(listOfEverything.get(t).x2)));
                            y2Element.appendChild(xmlDoc.createTextNode(String.valueOf(listOfEverything.get(t).y2)));

                            typeElement.appendChild(x1Element);
                            typeElement.appendChild(y1Element);
                            typeElement.appendChild(x2Element);
                            typeElement.appendChild(y2Element);

                            roomElement.appendChild(typeElement);
                        }
                    }

                    floorElement.appendChild(roomElement);
                }
            }
            rootElement.appendChild(floorElement);
        }

        return xmlDoc;
    }
}
