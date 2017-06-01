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
        XmlBuildingParser x = new XmlBuildingParser("src/res/building.xml");
        //x.parse();
        //x.fillWall(200, 200, 180, 0, 2, 1);
        Matrix mat = x.getMatrix();
        
        
        //x.fillWall(100, 1, 2, 70, 3);

        //int[][][] xmat = x.getRawMatrix();
        /*Matrix m = x.toMatrix(xmat);
        for(int i=0; i<=110; ++i) {
            for (int j = 0; j <= 110; ++j)
                System.out.print(m.getCell(2, i, j).getWalls());
        }
        */
        
        mf = new MainFrame();
        MinDistancePath mdp = new MinDistancePath(mat);
        ArrayList<Point> a = mdp.execute(new Point(0, 0, 0), new Point(10, 10, 0));
        XmlOutput x1 = new XmlOutput(a);
        x1.createXml("asd.xml");
        
        System.out.println("No errors so far");
    }

}
