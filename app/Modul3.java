/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import gui.MainFrame;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 */
public class Modul3 {

    
    
    /**
     * @param args the command line arguments
     */
    static MainFrame mf;
    static Matrix currentMatrix;
    static final String PATH = "building.xml";
    /**
     * 
     * @return xml file name
     */
    public static void getXML(String path) throws MalformedURLException, ProtocolException, IOException
    {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://localhost:4500/getXML");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line; 
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        Writer w = new PrintWriter(new File(path));
        w.write(result.toString());
        w.close();
    }
    
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        // TODO code application logic here
        ServerM2 srv = new ServerM2();
        srv.start();
        //mf = new MainFrame();
        
        
        getXML(PATH);
        XmlBuildingParser x = new XmlBuildingParser(PATH);
        currentMatrix = x.getMatrix();
       
        //int[][][] xmat = x.getRawMatrix();
        //Matrix m = x.toMatrix(xmat);
        /*
        for(int i=198; i<=202; ++i) {
            for (int j = 28; j <= 33; ++j)
            {
                System.out.print(mat.getCell(1, i, j).getWalls() + " ");
                if(j %25 == 0)
                    System.out.print("|");
            }
            if(i%25 == 0) System.out.println("");
            System.out.println();
        }
        */

        mf = new MainFrame();
//        MinTimePath mdp = new MinTimePath(currentMatrix);
//        System.out.println("!!!");
//        ArrayList<Point> a = mdp.execute(new Point(200, 30, 1), new Point(10, 120, 2));
//        System.out.println("????");
//        System.out.println(a.size());
//        for (Point p : a) {
//            System.out.println(p.getX() + " " + p.getY());
//        }        //a.clear();
        //a.add(new Point(10, 10, 0));
        //a.add(new Point(20, 20, 0));
        //a.add(new Point(30, 30, 1));
//        XmlOutput x1 = new XmlOutput(a);
//        x1.createXml("asd.xml");

        System.out.println("No errors so far");
    }

}
