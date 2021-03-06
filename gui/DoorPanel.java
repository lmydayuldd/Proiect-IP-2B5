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
 * This class makes the panel that handles the door manager in the application.
 * With the elements on this panel the user should add or remove the doors in
 * the building.
 *
 * @author karina
 */
class DoorPanel extends JPanel {

    JComboBox<String> componentType;
    Border border = new BevelBorder(1);
    JLabel errorNumberLabel, topLeftLabel, bottomRightLabel, wallHelpLabel, floorLabel, externalWallLabel, wallHelpLabel3, responseLabel, wallHelpLabel2, exitWayLabel, roomLabel, errorFloorLabel, errorStairLabel;
    JTextField x1TextField, y1TextField, x2TextField, y2TextField, floorField, roomTextField;
    JButton addWall;
    JCheckBox externalWall, exitWay;
    JTable walls;
    JScrollPane tableScrollPane = new JScrollPane();
    int exit, external;
    DefaultTableModel wallsTableModel;
    /**
     * Split the gui in three pane's to arrange them nicely.
     */
    JPanel topPane, addWallPane, removeWallPane;

    public DoorPanel() {

        // Setting up all the components
        setLayout(new FlowLayout());
        errorNumberLabel = new JLabel("");
        errorFloorLabel = new JLabel("");
        responseLabel = new JLabel("");
        errorStairLabel = new JLabel("");
        roomLabel = new JLabel("Room:");
        exitWayLabel = new JLabel("Exit way:");
        externalWallLabel = new JLabel("External door:");
        topLeftLabel = new JLabel("TopLeft Coordinates: ");
        floorLabel = new JLabel("Floor:");
        //topLeftLabel.setForeground(Color.LIGHT_GRAY);
        bottomRightLabel = new JLabel("Bottom-Right Coordinates");
        //bottomRightLabel.setForeground(Color.LIGHT_GRAY);
        // Initializing Labels with html code to set font and break lines.

        wallHelpLabel = new JLabel("<html><center><h1>Add Door<br></h1></center>Fill up this form in order to add a door to the Application. </html>");

        wallHelpLabel2 = new JLabel("      ");
        wallHelpLabel3 = new JLabel("                                     ");
        x1TextField = new JTextField("X");

        x1TextField.setPreferredSize(new Dimension(50, 20));
        floorField = new JTextField();
        floorField.setPreferredSize(new Dimension(50, 20));
        y1TextField = new JTextField("1");
        y1TextField.setPreferredSize(new Dimension(50, 20));
        x2TextField = new JTextField("1");
        x2TextField.setPreferredSize(new Dimension(50, 20));
        y2TextField = new JTextField("1");
        y2TextField.setPreferredSize(new Dimension(50, 20));
        roomTextField = new JTextField("c100");
        roomTextField.setPreferredSize(new Dimension(50, 20));
        externalWall = new JCheckBox();

        exitWay = new JCheckBox();
        exitWay.setPreferredSize(new Dimension(50, 20));
        externalWall.setPreferredSize(new Dimension(50, 20));
        addWall = new JButton("Add Door");

        // Starting to initialize the three inside Panels: topPane, addWallPane and removeWallPane
        topPane = new JPanel();
        topPane.setPreferredSize(new Dimension(600, 80));
        addWallPane = new JPanel();
        addWallPane.setPreferredSize(new Dimension(600, 100));
        removeWallPane = new JPanel();

        //Adding components of topPane
        topPane.add(wallHelpLabel);

        add(topPane);

        //Adding components of add Wall Pane
        addWallPane.add(floorLabel);
        addWallPane.add(floorField);
        addWallPane.add(roomLabel);
        addWallPane.add(roomTextField);
        addWallPane.add(topLeftLabel);
        addWallPane.add(x1TextField);
        addWallPane.add(y1TextField);
        addWallPane.add(bottomRightLabel);
        addWallPane.add(x2TextField);
        addWallPane.add(y2TextField);
        //addWallPane.add(wallHelpLabel2);
        addWallPane.add(externalWallLabel);
        addWallPane.add(externalWall);
        addWallPane.add(exitWayLabel);
        addWallPane.add(exitWay);
        //addWallPane.add(exitWayLabel);
        // addWallPane.add(exitWay);
        addWallPane.add(addWall);
        addWallPane.add(errorFloorLabel);
        addWallPane.add(errorStairLabel);

        addWallPane.add(errorNumberLabel);
        //addWallPane.add(wallHelpLabel3);
        addWallPane.add(responseLabel);
        add(addWallPane);

        setPreferredSize(new Dimension(600, 220));
        setBorder(border);
        //setBackground(Color.DARK_GRAY);

        addListeners();
    }

    private void addListeners() {
        addWall.addActionListener(new ActionListener() {
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
                    exit = 1;
                } else {
                    exit = 0;
                }
                responseLabel.setText("");
                repaint();
                String myRow[] = new String[7];
                myRow[0] = floorField.getText();
                myRow[1] = roomTextField.getText();
                myRow[2] = x1TextField.getText();
                myRow[3] = y1TextField.getText();
                myRow[4] = x2TextField.getText();
                myRow[5] = y2TextField.getText();
                myRow[6] = Integer.toString(external);

                if (floorField.getText().length() == 0) {
                    System.out.println("sdfasdf");
                    errorFloorLabel.setText("Introduceti etajul!");
                    errorFloorLabel.setForeground(Color.RED);
                    errorStairLabel.setText("");
                    errorNumberLabel.setText("");
                    repaint();
                } else if (roomTextField.getText().length() == 0) {
                    System.out.println("sdfasdf8");
                    errorFloorLabel.setText("");
                    errorStairLabel.setText("Introduceti numele camerei unde doriti sa adaugati usa!");
                    errorStairLabel.setForeground(Color.RED);
                    errorNumberLabel.setText("");
                    repaint();
                } else {

                    int ok = 1;
                    try {
                        int g = Integer.parseInt(floorField.getText());
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
                        errorNumberLabel.setText("");
                        repaint();
                        PostMethod post = new PostMethod("http://localhost:4500/add");

                        String x = "{\n"
                                + "        \"type\" : \"door\", \n"
                                + "        \"room\" : \"" + myRow[1] + "\",\n"
                                + "        \"x1\" : \"" + myRow[2] + "\" , \n"
                                + "        \"y1\" : \"" + myRow[3] + "\" ,\n"
                                + "        \"x2\" :  \"" + myRow[4] + "\" ,\n"
                                + "        \"y2\" :  \"" + myRow[5] + "\" ,\n"
                                + "        \"floor\" :  \"" + myRow[0] + "\" ,\n"
                                + "        \"isExitWay\" :  \"" + exit + "\" ,\n"
                                + "        \"isExterior\":  \"" + external + "\" \n"
                                + "\t\n"
                                + "}";

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
        }
        );
    }
}
