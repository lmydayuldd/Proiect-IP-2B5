/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
import gui.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
/**
 *
 */
public class Modul3 {

    /**
     * @param args the command line arguments
     */
    static MainFrame mf;
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        // TODO code application logic here
        //ServerM2 srv = new ServerM2();
        //srv.start();
        //mf = new MainFrame();
        //XmlBuildingParser x = new XmlBuildingParser("building.xml");
        //x.parse();
        //x.fillWall(0, 0, 0, 20, 2);
        //Matrix mat = x.getMatrix();
        
        ArrayList<Point> a = new ArrayList<>();
                a.add(new Point(10, 10, 2));
                a.add(new Point(20, 20, 2));
                XmlOutput x = new XmlOutput(a);
                x.createXml("asd.xml");
        
        System.out.println("No errors so far");
    }

}
