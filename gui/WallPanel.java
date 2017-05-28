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
 * This class makes the panel that handles the wall manager in the application.
 * With the elements on this panel the user should add, remove or modify 
 * the walls in the building.
 * @author alexandru
 */
class WallPanel extends JPanel{

    JComboBox <String> componentType;
    Border border = new BevelBorder(1);
    JLabel topLeftLabel, bottomRightLabel, wallHelpLabel;
    JTextField x1TextField, y1TextField, x2TextField, y2TextField;
    JButton addWall;
    
    /**
     * Split the gui in three pane's to arrange them nicely.
     */
    JPanel topPane, addWallPane, removeWallPane;
    
    
    public WallPanel() {
        
        // Setting up all the components
        setLayout(new FlowLayout());
        topLeftLabel = new JLabel("TopLeft Coordinates: ");
        //topLeftLabel.setForeground(Color.LIGHT_GRAY);
        bottomRightLabel = new JLabel("Bottom-Right Coordinates");
        //bottomRightLabel.setForeground(Color.LIGHT_GRAY);
        // Initializing Labels with html code to set font and break lines.
        wallHelpLabel = new JLabel("<html><h1>Add Wall<br></h1>Fill up this form in order to add a wall to the Application. </html>");
        x1TextField = new JTextField("X");
        x1TextField.setPreferredSize(new Dimension(50, 20));
        y1TextField = new JTextField("Y");
        y1TextField.setPreferredSize(new Dimension(50, 20));
        x2TextField = new JTextField("X");
        x2TextField.setPreferredSize(new Dimension(50, 20));
        y2TextField = new JTextField("Y");
        y2TextField.setPreferredSize(new Dimension(50, 20));
        addWall = new JButton("Add Wall");
        
        // Starting to initialize the three inside Panels: topPane, addWallPane and removeWallPane
        topPane = new JPanel();
        topPane.setPreferredSize(new Dimension(1000, 100));
        addWallPane = new JPanel();
        addWallPane.setPreferredSize(new Dimension(1000, 100));
        removeWallPane = new JPanel();
        
        //Adding components of topPane
        topPane.add(wallHelpLabel);
        
        add(topPane);
        
        //Adding components of add Wall Pane
        addWallPane.add(topLeftLabel);
        addWallPane.add(x1TextField);
        addWallPane.add(y1TextField);
        addWallPane.add(bottomRightLabel);
        addWallPane.add(x2TextField);
        addWallPane.add(y2TextField);
        addWallPane.add(addWall);
        
        add(addWallPane);
        
        
        //TODO remove Pane!!
        
        setPreferredSize(new Dimension(1024, 250));
        setBorder(border);
        //setBackground(Color.DARK_GRAY);
    }
}
