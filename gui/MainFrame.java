package gui;

import app.Modul3;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    public RemovePanel rpane;
    WindowPanel windowPanel;

    public MainFrame() throws IOException, SAXException, ParserConfigurationException {
        super();
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(600,900));
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(600, 900)); 
        
        XmlTable xmlt = new XmlTable(Modul3.PATH);
        rpane = new RemovePanel(xmlt);
        rightPanel.add(rpane);
        windowPanel = new WindowPanel();
        stairPanel = new StairPanel();
        doorPanel = new DoorPanel();
        setLayout(new FlowLayout());
        setLayout(new FlowLayout());
        wallPanel = new WallPanel();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(new Dimension(1250, 950));
        getContentPane().setBackground(Color.DARK_GRAY);
        //setBackground(Color.yellow);
        setVisible(true);

        leftPanel.add(wallPanel);
        leftPanel.add(stairPanel);
        leftPanel.add(doorPanel);
        leftPanel.add(windowPanel);
        add(leftPanel);
        add(rightPanel);
        
       addListeners();

    }

    private void addListeners() {
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                String[] options = {"YES", "NO"};
                JOptionPane pane = new JOptionPane();
                pane.setMessageType(JOptionPane.QUESTION_MESSAGE);
                pane.setMessage("<html>If you will exit now, all temporary data will be discarded<br>"
                        + "The building will revert to the last valid state.<br>"
                        + "Do you really want to quit?</html>");
                pane.setOptions(options);
                pane.setInitialValue("YES");
                
                JDialog dialog = pane.createDialog(null,"ATTENTION");
                dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(Boolean.TRUE);
                Object answer = pane.getValue();
                System.out.println("Answer: "+answer);
                if(answer.equals("NO"))
                {
                    dialog.dispose();
                    
                }
                else {
                    
                    dispose();
                    System.exit(0);
                }
            }
        });
    }

}
