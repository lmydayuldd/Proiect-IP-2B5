package gui;

import app.Modul3;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
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

    }

}
