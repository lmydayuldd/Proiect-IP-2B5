/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import app.Modul3;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * This class makes the panel that handles the stair manager in the application.
 * With the elements on this panel the user should add or remove the stairs in
 * the building.
 *
 * @author karina
 */
class StairPanel extends JPanel {

    JComboBox<String> componentType;
    Border border = new BevelBorder(1);
    JLabel topLeftLabel, bottomRightLabel, stairHelpLabel, roomLabel, floorLabel, responseLabel, externalLabel, wallHelpLabel2, wallHelpLabel3, exitWayLabel, errorFloorLabel, errorStairLabel, errorNumberLabel;
    JTextField x1TextField, y1TextField, x2TextField, y2TextField, roomTextField, floorTextField;
    JButton addStair;
    JCheckBox externalWall, exitWay;
    int external, exitway;

    /**
     * Split the gui in three pane's to arrange them nicely.
     */
    JPanel topPane, addStairPane, removeStairPane;

    public StairPanel() {

        // Setting up all the components
        setLayout(new FlowLayout());
        responseLabel = new JLabel("");
        wallHelpLabel3 = new JLabel("                          ");
        errorNumberLabel = new JLabel("");
        errorFloorLabel = new JLabel("");
        errorStairLabel = new JLabel("");
        floorLabel = new JLabel("Floor:");
        roomLabel = new JLabel("Stair name:");
        externalLabel = new JLabel("Extern stairs");
        exitWayLabel = new JLabel("Exit way stairs");
        wallHelpLabel2 = new JLabel("              ");
        topLeftLabel = new JLabel("TopLeft Coordinates: ");
        //topLeftLabel.setForeground(Color.LIGHT_GRAY);
        bottomRightLabel = new JLabel("Bottom-Right Coordinates: ");
        //bottomRightLabel.setForeground(Color.LIGHT_GRAY);
        // Initializing Labels with html code to set font and break lines.
        stairHelpLabel = new JLabel("<html><center><h1>Add Stair<br></h1></center>Fill up this form in order to add stairs to the Application. </html>");
        x1TextField = new JTextField("X");
        x1TextField.setPreferredSize(new Dimension(50, 20));
        y1TextField = new JTextField("Y");
        y1TextField.setPreferredSize(new Dimension(50, 20));
        x2TextField = new JTextField("X");
        x2TextField.setPreferredSize(new Dimension(50, 20));
        y2TextField = new JTextField("Y");
        y2TextField.setPreferredSize(new Dimension(50, 20));
        floorTextField = new JTextField();
        floorTextField.setPreferredSize(new Dimension(50, 20));
        roomTextField = new JTextField("Stair1");
        roomTextField.setPreferredSize(new Dimension(50, 20));
        externalWall = new JCheckBox();

        exitWay = new JCheckBox();
        exitWay.setPreferredSize(new Dimension(50, 20));
        externalWall.setPreferredSize(new Dimension(50, 20));
        addStair = new JButton("Add Stair");

        // Starting to initialize the three inside Panels: topPane, addStairPane and removeStairPane
        topPane = new JPanel();
        topPane.setPreferredSize(new Dimension(600, 80));
        addStairPane = new JPanel();
        addStairPane.setPreferredSize(new Dimension(600, 100));
        removeStairPane = new JPanel();

        //Adding components of topPane
        topPane.add(stairHelpLabel);

        add(topPane);

        //Adding components of add Stair Pane
        addStairPane.add(floorLabel);
        addStairPane.add(floorTextField);
        addStairPane.add(roomLabel);
        addStairPane.add(roomTextField);
        addStairPane.add(topLeftLabel);
        addStairPane.add(x1TextField);
        addStairPane.add(y1TextField);
        addStairPane.add(bottomRightLabel);
        addStairPane.add(x2TextField);
        addStairPane.add(y2TextField);
        //addStairPane.add(wallHelpLabel2);
        addStairPane.add(externalLabel);
        addStairPane.add(externalWall);
        addStairPane.add(wallHelpLabel2);
        addStairPane.add(exitWayLabel);
        addStairPane.add(exitWay);
        addStairPane.add(addStair);
        addStairPane.add(errorFloorLabel);
        addStairPane.add(errorStairLabel);
        addStairPane.add(errorNumberLabel);
        //addStairPane.add(wallHelpLabel3);
        addStairPane.add(responseLabel);
        add(addStairPane);
        addListeners();
        setPreferredSize(new Dimension(600, 220));
        setBorder(border);
        //setBackground(Color.DARK_GRAY);
    }

    private void addListeners() {
        addStair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    selectionButtonPressed();
                } catch (IOException ex) {
                    Logger.getLogger(WallPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            private void selectionButtonPressed() throws IOException {
                if (externalWall.isSelected()) {
                    external = 1;
                } else {
                    external = 0;
                }
                if (exitWay.isSelected()) {
                    exitway = 1;
                } else {
                    exitway = 0;
                }
                responseLabel.setText("");
                if(roomTextField.getText().indexOf("Stair") != 0)
                {
                 
                    responseLabel.setText("Stair name must begin with \"Stair\".");
                    responseLabel.setForeground(Color.red);
                    repaint();
                    return;
                }
                repaint();
                if (floorTextField.getText().length() == 0) {
                    //  System.out.println("sdfasdf");
                    errorFloorLabel.setText("Introduceti etajul!");
                    errorFloorLabel.setForeground(Color.RED);
                    errorStairLabel.setText("");
                    errorNumberLabel.setText("");
                    repaint();
                } else if (roomTextField.getText().length() == 0) {
                    //  System.out.println("sdfasdf");
                    errorStairLabel.setText("Introduceti numele scarii!");
                    errorStairLabel.setForeground(Color.RED);
                    errorFloorLabel.setText("");
                    errorNumberLabel.setText("");
                    repaint();
                } else {
                    int ok = 1;
                    try {
                        int g = Integer.parseInt(floorTextField.getText());
                        int g2 = Integer.parseInt(x1TextField.getText());
                        int g3 = Integer.parseInt(y1TextField.getText());
                        int g4 = Integer.parseInt(x2TextField.getText());
                        int g5 = Integer.parseInt(y2TextField.getText());
                    } catch (NumberFormatException e) {
                        errorNumberLabel.setText("Coordonatele si numarul etajului trebuie sa fie numerice!");
                        errorNumberLabel.setForeground(Color.RED);
                        errorFloorLabel.setText("");
                        errorStairLabel.setText("");
                        repaint();
                        ok = 0;
                    }
                    if (ok == 1) {

                        errorFloorLabel.setText("");
                        errorStairLabel.setText("");
                        repaint();
                        PostMethod post = new PostMethod("http://localhost:4500/add");

                        String x = "{\n"
                                + "        \"type\" : \"stair\", \n"
                                + "        \"room\" : \"Stair" + roomTextField.getText() + "\",\n"
                                + "        \"x1\" : \"" + x1TextField.getText() + "\" , \n"
                                + "        \"y1\" : \"" + y1TextField.getText() + "\" ,\n"
                                + "        \"x2\" :  \"" + x2TextField.getText() + "\" ,\n"
                                + "        \"y2\" :  \"" + y2TextField.getText() + "\" ,\n"
                                + "        \"floor\" :  \"" + floorTextField.getText() + "\" ,\n"
                                + "        \"isExitWay\" :  \"" + exitway + "\" ,\n"
                                + "        \"isExterior\":  \"" + external + "\" \n"
                                + "\t\n }";

                        BufferedReader br = null;
                        post.setRequestHeader("Content-type", "application/json");
                        post.setRequestBody(x);
                        try {
                            HttpClient httpClient = new HttpClient();
                            int resp = httpClient.executeMethod(post);

                            if (resp == 200) {
                                responseLabel.setText("Inserarea a avut loc cu succes!");
                                responseLabel.setForeground(Color.GREEN);
                                repaint();
                                DefaultTableModel tm = (DefaultTableModel) Modul3.mf.rpane.tempTable.getModel();
                                String[] tmp = new String[1];
                                tmp[0] = x;
                                tm.addRow(tmp);
                            } else if (resp == 400) {
                                responseLabel.setText("ERROR! Valorile introduse sunt invalide!");
                                responseLabel.setForeground(Color.RED);
                                repaint();
                            } else if (resp == 409) {
                                responseLabel.setText("ERROR! Elementul deja exista!");
                                responseLabel.setForeground(Color.RED);
                                repaint();
                            } else {
                                responseLabel.setText("ERROR!");
                                responseLabel.setForeground(Color.RED);
                                repaint();
                            }

                        } catch (Exception e) {
                            System.err.println(e);
                        } finally {
                            post.releaseConnection();
                            if (br != null) {
                                try {
                                    br.close();
                                } catch (Exception fe) {
                                }
                            }
                        }

                    }
                }
            }
        });
    }
}
