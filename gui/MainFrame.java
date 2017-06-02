package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 * This class models the main frame of the application.
 * @author alexandru
 */
public class MainFrame extends JFrame{

    WallPanel leftPanel;
    StairPanel stairPanel;
    DoorPanel doorPanel;
    WindowPanel windowPanel;
    public MainFrame() {
        super();
        windowPanel=new WindowPanel();
        stairPanel = new StairPanel();
       doorPanel=new DoorPanel();
            setLayout(new FlowLayout());
        setLayout(new FlowLayout());
        leftPanel = new WallPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1024, 1050));
        getContentPane().setBackground(Color.DARK_GRAY);
        //setBackground(Color.yellow);
        setVisible(true);
        
        
        add(leftPanel);
        add(stairPanel);
        add(doorPanel);
        add (windowPanel);
    }
    
    
    
}
