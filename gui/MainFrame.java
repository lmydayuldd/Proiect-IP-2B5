package gui;

import app.Modul3;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.xml.sax.SAXException;

/**
 * This class models the main frame of the application.
 *
 * @author alexandru
 */
public class MainFrame extends JFrame {

    WallPanel wallPanel;
    StairPanel stairPanel;
    DoorPanel doorPanel;
    JPanel leftPanel, rightPanel;
    JButton validateButton, xmlButton;
    
    WindowPanel windowPanel;

    public MainFrame() throws IOException, SAXException, ParserConfigurationException {
        super();
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(600, 800));
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(600, 800)); 
        
        XmlTable xmlt = new XmlTable("src/res/building.xml");
        RemovePanel rpane = new RemovePanel(xmlt);
        rightPanel.add(rpane);
        windowPanel = new WindowPanel();
        stairPanel = new StairPanel();
        doorPanel = new DoorPanel();
        setLayout(new FlowLayout());
        setLayout(new FlowLayout());
        wallPanel = new WallPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1244, 820));
        getContentPane().setBackground(Color.DARK_GRAY);
        //setBackground(Color.yellow);
        setVisible(true);

        leftPanel.add(wallPanel);
        leftPanel.add(stairPanel);
        leftPanel.add(doorPanel);
        leftPanel.add(windowPanel);
        add(leftPanel);
        add(rightPanel);
        
        validateButton = new JButton("Validate changes!");
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    
                    PostMethod post = new PostMethod("http://localhost:4500/finalSave");
                    HttpClient httpClient = new HttpClient();
                    int resp = httpClient.executeMethod(post);
                    System.out.println("Am cerut validarea, iar raspunsul a fost: " + resp);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                
             
            }
        });
        
        xmlButton = new JButton("get XML schema");
        xmlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Modul3.getXML(Modul3.PATH);
                } catch (ProtocolException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
             
            }
        });
        rpane.add(validateButton);
        rpane.add(xmlButton);
        
    }

}
