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
 * This class makes the panel that handles the door manager in the application.
 * With the elements on this panel the user should add or remove 
 * the doors in the building.
 * @author karina
 */
class DoorPanel extends JPanel{

    JComboBox <String> componentType;
    Border border = new BevelBorder(1);
    JLabel topLeftLabel, bottomRightLabel, doorHelpLabel;
    JTextField x1TextField, y1TextField, x2TextField, y2TextField;
    JButton addDoor;
    // JButton removeDoor;
    
    /**
     * Split the gui in three pane's to arrange them nicely.
     */
    JPanel topPane, addDoorPane, removeDoorPane;
    
    
    public DoorPanel() {
        
        // Setting up all the components
        setLayout(new FlowLayout());
        topLeftLabel = new JLabel("TopLeft Coordinates: ");
        //topLeftLabel.setForeground(Color.LIGHT_GRAY);
        bottomRightLabel = new JLabel("Bottom-Right Coordinates: ");
        //bottomRightLabel.setForeground(Color.LIGHT_GRAY);
        // Initializing Labels with html code to set font and break lines.
        doorHelpLabel = new JLabel("<html><h1>Add Door<br></h1>Fill up this form in order to add a door to the Application. </html>");
        x1TextField = new JTextField("X");
        x1TextField.setPreferredSize(new Dimension(50, 20));    
        y1TextField = new JTextField("Y");
        y1TextField.setPreferredSize(new Dimension(50, 20));
        x2TextField = new JTextField("X");
        x2TextField.setPreferredSize(new Dimension(50, 20));
        y2TextField = new JTextField("Y");
        y2TextField.setPreferredSize(new Dimension(50, 20));
        addDoor = new JButton("Add Door");
        
        // Starting to initialize the three inside Panels: topPane, addDoorPane and removeDoorPane
        topPane = new JPanel();
        topPane.setPreferredSize(new Dimension(1000, 100));
        addDoorPane = new JPanel();
        addDoorPane.setPreferredSize(new Dimension(1000, 100));
        removeDoorPane = new JPanel();
        
        //Adding components of topPane
        topPane.add(doorHelpLabel);
        
        add(topPane);
        
        //Adding components of add Door Pane
        addDoorPane.add(topLeftLabel);
        addDoorPane.add(x1TextField);
        addDoorPane.add(y1TextField);
        addDoorPane.add(bottomRightLabel);
        addDoorPane.add(x2TextField);
        addDoorPane.add(y2TextField);
        addDoorPane.add(addDoor);
        
        add(addDoorPane);
        
        setPreferredSize(new Dimension(1024, 250));
        setBorder(border);
    }
}
