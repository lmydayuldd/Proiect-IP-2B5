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
 *
 * @author alexandru
 */
class WallPanel extends JPanel{

    JComboBox <String> componentType;
    Border border = new BevelBorder(1);
    JLabel topLeftLabel, bottomRightLabel, wallHelpLabel;
    JTextField x1TextField, y1TextField, x2TextField, y2TextField;
    JButton addWall;
    
    public WallPanel() {
        setLayout(new FlowLayout());
        topLeftLabel = new JLabel("TopLeft Coordinates: ");
        //topLeftLabel.setForeground(Color.LIGHT_GRAY);
        bottomRightLabel = new JLabel("Bottom-Right Coordinates");
        //bottomRightLabel.setForeground(Color.LIGHT_GRAY);
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
        
        add(wallHelpLabel);
        add(topLeftLabel);
        add(x1TextField);
        add(y1TextField);
        add(bottomRightLabel);
        add(x2TextField);
        add(y2TextField);
        add(addWall);
        setPreferredSize(new Dimension(1024, 250));
        setBorder(border);
        //setBackground(Color.DARK_GRAY);
    }
    
    
    
}
