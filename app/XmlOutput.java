package app;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
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
        Element rootElement = document.createElement("path_points");
        document.appendChild(rootElement);


        for (Point p : points) {
            Element pointsTag = document.createElement("point");
            Integer floor = p.getFloor();
            Double x = p.getX()/10.0;
            Double y = p.getY()/10.0;

            Element xElement = document.createElement("x");
            xElement.appendChild(document.createTextNode(x.toString()));

            Element yElement = document.createElement("y");
            yElement.appendChild(document.createTextNode(y.toString()));

            Element floorElement = document.createElement("floor");
            floorElement.appendChild(document.createTextNode(floor.toString()));

            pointsTag.appendChild(xElement);
            pointsTag.appendChild(yElement);
            pointsTag.appendChild(floorElement);

            rootElement.appendChild(pointsTag);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(new File(path));

        transformer.transform(source,result);
    }

}
