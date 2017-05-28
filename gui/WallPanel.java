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
    JLabel topLeftLabel, bottomRightLabel;
    JTextField x1TextField, y1TextField, x2TextField, y2TextField;
    
    public WallPanel() {
        setLayout(new FlowLayout());
        topLeftLabel = new JLabel("TopLeft Coordinates: ");
        topLeftLabel.setForeground(Color.LIGHT_GRAY);
        bottomRightLabel = new JLabel("Bottom-Right Coordinates");
        bottomRightLabel.setForeground(Color.LIGHT_GRAY);
        x1TextField = new JTextField("X   ");
        y1TextField = new JTextField("Y   ");
        x2TextField = new JTextField("X   ");
        y2TextField = new JTextField("Y   ");
        
        add(topLeftLabel);
        add(x1TextField);
        add(y1TextField);
        add(bottomRightLabel);
        add(x2TextField);
        add(y2TextField);
        setPreferredSize(new Dimension(1024, 250));
        setBorder(border);
        setBackground(Color.DARK_GRAY);
    }
    
    
    
}
