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
    
    
    public MainFrame() {
        super();
        stairPanel = new StairPanel();
        setLayout(new FlowLayout());
        leftPanel = new WallPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1024, 800));
        getContentPane().setBackground(Color.DARK_GRAY);
        //setBackground(Color.yellow);
        setVisible(true);
        
        
        add(leftPanel);
        add(stairPanel);
    }
    
    
    
}
