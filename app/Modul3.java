/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import gui.MainFrame;
import java.awt.Color;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 *
 */
public class Modul3 {

    /**
     * @param args the command line arguments
     */
    public static MainFrame mf;
    public static Matrix currentMatrix;
    public static final String PATH = "src/res/building.xml";
    public static final String RUN_PATH = "src/res/building.xml";

    public static void rollback() {

        try {
            PostMethod post = new PostMethod("http://localhost:4500/rollback");
            HttpClient httpClient = new HttpClient();
            int resp = httpClient.executeMethod(post);
            if (resp == 200) {
                System.out.println("Am facut rollback");
                //responseLabel.setText("Inserarea a avut loc cu succes!");
                //responseLabel.setForeground(Color.GREEN);
                //repaint();
            } else if (resp == 500) {
                System.out.println("A crapat serverul!");
                //responseLabel.setForeground(Color.GREEN);
                //repaint();
            }
        } catch (IOException ex) {
            Logger.getLogger(Modul3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return xml file name
     */
    public static void getXML(String path) throws MalformedURLException, ProtocolException, IOException {
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
        rollback();
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
        System.out.println("No errors so far");
    }

}
