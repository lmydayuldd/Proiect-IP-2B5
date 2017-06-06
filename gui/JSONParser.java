/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexandru
 */
public class JSONParser {
    
    public String[] getAndParse()
    {
        
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL("http://localhost:4500/getTemporaryData");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            
            String json = result.toString();
            //Aici tre sa faci request de JSON si sa il parsezi
            
            
            
            
            
            
            return null;
        } catch (IOException ex) {
            Logger.getLogger(JSONParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
