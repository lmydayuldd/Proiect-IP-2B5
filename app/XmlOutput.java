package app;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
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
 * Created by Vasile Catana, Tamara Trifan, Cristina Ulinici on 5/28/2017.
 *
 * This class is receving an array of Point() and
 * is giving xml file coordinates
 *
 */
public class XmlOutput{
    private ArrayList<Point> points;

    private DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder docBuilder;

    /**
     * Constructor
     * @param points  an Array with point to be processed
     * @see           XmlOutput
     */
    public  XmlOutput(ArrayList<Point> points) throws ParserConfigurationException {
        this.points = points;
        docBuilder = docFactory.newDocumentBuilder();
    }

    /**
     * Transform an ArrayList<Point> to a xml file
     * * @param path  an absolute path to xml file where to be saved
     * @see         XmlOutput
     */

    public void createXml(String path) throws TransformerException {
        Document document = docBuilder.newDocument();
        Element rootElement = document.createElement("floors");
        document.appendChild(rootElement);

        Integer auxFloor = Integer.MAX_VALUE-1; // I hope that such a floor doesn't exist :D

        Element floorTag = document.createElement("floor");
        Attr attrFloor = document.createAttribute("number");

        boolean singlePointOnFloor = true;

        for (int i=0; i<points.size()-1; ++i) {
            Element pointsTag = document.createElement("type");
            Attr attr = document.createAttribute("type");
            attr.setValue("path");
            pointsTag.setAttributeNode(attr);
            Integer floor = points.get(i).getFloor();
            Integer floorNext = points.get(i+1).getFloor();

            Double x1 = points.get(i).getX() / 10.0;
            Double y1 = points.get(i).getY() / 10.0;

            Double x2 = points.get(i + 1).getX() / 10.0;
            Double y2 = points.get(i + 1).getY() / 10.0;

            Element x1Element = document.createElement("x1");
            x1Element.appendChild(document.createTextNode(x1.toString()));

            Element y1Element = document.createElement("y1");
            y1Element.appendChild(document.createTextNode(y1.toString()));

            Element x2Element = document.createElement("x2");
            x2Element.appendChild(document.createTextNode(x2.toString()));

            Element y2Element = document.createElement("y2");
            y2Element.appendChild(document.createTextNode(y2.toString()));

            if (auxFloor.equals(Integer.MAX_VALUE-1))
            {
                auxFloor = floor;
                attrFloor.setValue(auxFloor.toString());
                floorTag.setAttributeNode(attrFloor);
            }

            if (!floor.equals(floorNext)) {
                if (singlePointOnFloor)
                {
                    pointsTag.appendChild(x1Element);
                    pointsTag.appendChild(y1Element);
                    floorTag.appendChild(pointsTag);
                }

                rootElement.appendChild(floorTag);
                floorTag = document.createElement("floor");
                attrFloor = document.createAttribute("number");
                attrFloor.setValue(floorNext.toString());
                floorTag.setAttributeNode(attrFloor);
                singlePointOnFloor = true;
            }
            else
            {
                pointsTag.appendChild(x1Element);
                pointsTag.appendChild(y1Element);
                pointsTag.appendChild(x2Element);
                pointsTag.appendChild(y2Element);
                floorTag.appendChild(pointsTag);
                singlePointOnFloor = false;
            }

            if (i==points.size()-1)
            {
                if (singlePointOnFloor)
                {
                    pointsTag.appendChild(x1Element);
                    pointsTag.appendChild(y1Element);
                    floorTag.appendChild(pointsTag);
                }

                pointsTag.appendChild(x1Element);
                pointsTag.appendChild(y1Element);
                floorTag.appendChild(pointsTag);
                rootElement.appendChild(floorTag);
            }
        }

        rootElement.appendChild(floorTag);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(new File(path));

        transformer.transform(source,result);
    }

}
