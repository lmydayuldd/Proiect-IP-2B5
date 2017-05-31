/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


/**
 * This class makes the panel that handles the stair manager in the application.
 * With the elements on this panel the user should add or remove 
 * the stairs in the building.
 * @author karina
 */
class StairPanel extends JPanel{

    JComboBox <String> componentType;
    Border border = new BevelBorder(1);
    JLabel topLeftLabel, bottomRightLabel, stairHelpLabel;
    JTextField x1TextField, y1TextField, x2TextField, y2TextField;
    JButton addStair;
    
    /**
     * Split the gui in three pane's to arrange them nicely.
     */
    JPanel topPane, addStairPane, removeStairPane;
    
    
    public StairPanel() {
        
        // Setting up all the components
        setLayout(new FlowLayout());
        topLeftLabel = new JLabel("TopLeft Coordinates: ");
        //topLeftLabel.setForeground(Color.LIGHT_GRAY);
        bottomRightLabel = new JLabel("Bottom-Right Coordinates: ");
        //bottomRightLabel.setForeground(Color.LIGHT_GRAY);
        // Initializing Labels with html code to set font and break lines.
        stairHelpLabel = new JLabel("<html><h1>Add Stair<br></h1>Fill up this form in order to add stairs to the Application. </html>");
        x1TextField = new JTextField("X");
        x1TextField.setPreferredSize(new Dimension(50, 20));
        y1TextField = new JTextField("Y");
        y1TextField.setPreferredSize(new Dimension(50, 20));
        x2TextField = new JTextField("X");
        x2TextField.setPreferredSize(new Dimension(50, 20));
        y2TextField = new JTextField("Y");
        y2TextField.setPreferredSize(new Dimension(50, 20));
        addStair = new JButton("Add Stair");
        
        // Starting to initialize the three inside Panels: topPane, addStairPane and removeStairPane
        topPane = new JPanel();
        topPane.setPreferredSize(new Dimension(1000, 100));
        addStairPane = new JPanel();
        addStairPane.setPreferredSize(new Dimension(1000, 100));
        removeStairPane = new JPanel();
        
        //Adding components of topPane
        topPane.add(stairHelpLabel);
        
        add(topPane);
        
        //Adding components of add Stair Pane
        addStairPane.add(topLeftLabel);
        addStairPane.add(x1TextField);
        addStairPane.add(y1TextField);
        addStairPane.add(bottomRightLabel);
        addStairPane.add(x2TextField);
        addStairPane.add(y2TextField);
        addStairPane.add(addStair);
        
        add(addStairPane);
        
        setPreferredSize(new Dimension(1024, 250));
        setBorder(border);
        //setBackground(Color.DARK_GRAY);
    }
}
