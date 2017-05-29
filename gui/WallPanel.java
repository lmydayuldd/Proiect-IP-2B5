/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


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
    JTextField x1TextField, y1TextField, x2TextField, y2TextField, floorField;
    JButton addWall;
    JTable walls;
    JScrollPane tableScrollPane = new JScrollPane();
    DefaultTableModel wallsTableModel;
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
        floorField = new JTextField("Floor");
        floorField.setPreferredSize(new Dimension(50, 20));
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
        addWallPane.setPreferredSize(new Dimension(1000, 50));
        removeWallPane = new JPanel();
        
        //Adding components of topPane
        topPane.add(wallHelpLabel);
        
        add(topPane);
        
        //Adding components of add Wall Pane
        addWallPane.add(floorField);
        addWallPane.add(topLeftLabel);
        addWallPane.add(x1TextField);
        addWallPane.add(y1TextField);
        addWallPane.add(bottomRightLabel);
        addWallPane.add(x2TextField);
        addWallPane.add(y2TextField);
        addWallPane.add(addWall);
        
        add(addWallPane);
        
        
        //TODO remove Pane!!
        
        removeWallPane = new JPanel();
        

        
        //setting up the bottom table
        String tableHeader[] = {"Floor", "X1", "Y1", "X2", "Y2"};
        wallsTableModel = new DefaultTableModel(null, tableHeader);
        wallsTableModel.addRow(tableHeader);
        walls = new JTable(wallsTableModel);
        walls.setPreferredScrollableViewportSize(new Dimension(500, 100));

        /*
        //Setting up the scroll pane in which we will put the Table
        tableScrollPane.setPreferredSize(new Dimension(600, 100));
        tableScrollPane.add(walls);
        */
        removeWallPane.add(new JScrollPane(walls));
        
        
        //Adding to the main frame the bottom panel which contains the table
        // with the walls data
        add(removeWallPane);
        setPreferredSize(new Dimension(1024, 450));
        setBorder(border);
        //setBackground(Color.DARK_GRAY);
        
        addListeners();
    }
    
    private void addListeners()
    {
                addWall.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    selectionButtonPressed();
            }

            private void selectionButtonPressed() {
                
                String myRow[] = new String[5];
                myRow[0] = floorField.getText();
                myRow[1] = x1TextField.getText();
                myRow[2] = y1TextField.getText();
                myRow[3] = x2TextField.getText();
                myRow[4] = y2TextField.getText();
                wallsTableModel.addRow(myRow);
            }
        });
    }
}
