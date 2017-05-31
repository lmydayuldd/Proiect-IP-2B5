/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
import gui.*;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
/**
 *
 */
public class Modul3 {

    /**
     * @param args the command line arguments
     */
    static MainFrame mf;
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        // TODO code application logic here
        ServerM2 srv = new ServerM2();
        srv.start();
        //mf = new MainFrame();
        XmlBuildingParser x = new XmlBuildingParser("building.xml");
        x.parse();
        
    }

}
