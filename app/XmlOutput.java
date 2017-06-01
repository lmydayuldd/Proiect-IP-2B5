package app;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Vic on 5/31/2017.
 */
public class XmlOutput {
    private ArrayList<Point> points;
    public XmlOutput(ArrayList<Point> pts){
        this.points=pts;
    }

    /**
     * This takes the output of our pathfinding algorithm
     * and creates an XML file with the path.
     */
    public void createXml(String path) throws TransformerException, ParserConfigurationException {
        Document doc = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().newDocument();
        Integer n=points.size();
        Element floors = doc.createElement("floors");
        doc.appendChild(floors);
        Integer maxFloor=0;
        for(int i=0;i<n;i++){
            if(points.get(i).getFloor()>maxFloor)
                maxFloor=points.get(i).getFloor();
        }
        Element[] floor= new Element[maxFloor+1];
        for (Integer i=1;i<=maxFloor;i++){
            floor[i]=doc.createElement("floor");
            floor[i].setAttribute("number", String.valueOf(i));
        }
        if(n==1){
            Element room = doc.createElement("room");
            Element type = doc.createElement("type");
            Element x1 = doc.createElement("x1");
            Element x2 = doc.createElement("x2");
            Element y1 = doc.createElement("y1");
            Element y2 = doc.createElement("y2");
            type.setAttribute("name","path");

            floors.appendChild(floor[points.get(0).getFloor()]);
            floors.getLastChild().appendChild(room);
            room.appendChild(type);
            type.appendChild(x1);
            x1.setTextContent(String.valueOf((points.get(0).getX()/(float)10)));
            type.appendChild(y1);
            y1.setTextContent(String.valueOf((points.get(0).getY()/(float)10)));
            type.appendChild(x2);
            x2.setTextContent(String.valueOf((points.get(0).getX()/(float)10)));
            type.appendChild(y2);
            y2.setTextContent(String.valueOf((points.get(0).getY()/(float)10)));
        }
        else{

        for(int i=1; i<points.size();i++) {
            //If first segment in path
            if(i==1){
                Element room = doc.createElement("room");
                Element type = doc.createElement("type");
                Element x1 = doc.createElement("x1");
                Element x2 = doc.createElement("x2");
                Element y1 = doc.createElement("y1");
                Element y2 = doc.createElement("y2");
                type.setAttribute("name","path");

                floors.appendChild(floor[points.get(i).getFloor()]);
                floors.getLastChild().appendChild(room);
                room.appendChild(type);
                type.appendChild(x1);
                x1.setTextContent(String.valueOf((points.get(i-1).getX()/(float)10)));
                type.appendChild(y1);
                y1.setTextContent(String.valueOf((points.get(i-1).getY()/(float)10)));
                type.appendChild(x2);
                x2.setTextContent(String.valueOf((points.get(i).getX()/(float)10)));
                type.appendChild(y2);
                y2.setTextContent(String.valueOf((points.get(i).getY()/(float)10)));
            }
            else{
                if(points.get(i).getFloor()==points.get(i-1).getFloor()){
                    //Floor does not change
                    Element type = doc.createElement("type");
                    Element x1 = doc.createElement("x1");
                    Element x2 = doc.createElement("x2");
                    Element y1 = doc.createElement("y1");
                    Element y2 = doc.createElement("y2");
                    type.setAttribute("name","path");
                    floors.getLastChild().getLastChild().appendChild(type);
                    type.appendChild(x1);
                    x1.setTextContent(String.valueOf((points.get(i-1).getX()/(float)10)));
                    type.appendChild(y1);
                    y1.setTextContent(String.valueOf((points.get(i-1).getY()/(float)10)));
                    type.appendChild(x2);
                    x2.setTextContent(String.valueOf((points.get(i).getX()/(float)10)));
                    type.appendChild(y2);
                    y2.setTextContent(String.valueOf((points.get(i).getY()/(float)10)));
                }
                else{
                    Element room = doc.createElement("room");
                    Element type = doc.createElement("type");
                    Element x1 = doc.createElement("x1");
                    Element x2 = doc.createElement("x2");
                    Element y1 = doc.createElement("y1");
                    Element y2 = doc.createElement("y2");
                    type.setAttribute("name","path");
                    floors.appendChild(floor[points.get(i).getFloor()]);
                    floors.getLastChild().appendChild(room);
                    room.appendChild(type);
                    type.appendChild(x1);
                    x1.setTextContent(String.valueOf((points.get(i-1).getX()/(float)10)));
                    type.appendChild(y1);
                    y1.setTextContent(String.valueOf((points.get(i-1).getY()/(float)10)));
                    type.appendChild(x2);
                    x2.setTextContent(String.valueOf((points.get(i).getX()/(float)10)));
                    type.appendChild(y2);
                    y2.setTextContent(String.valueOf((points.get(i).getY()/(float)10)));
                }
            }
        }}
        // Output the document.
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(source, result);

        System.out.println("File saved!");
    }
}
