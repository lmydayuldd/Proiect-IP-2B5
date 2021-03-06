package gui;

import app.Modul3;
import static app.Modul3.PATH;
import app.XmlBuildingParser;
import com.google.gson.Gson;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Created by Vic on 6/3/2017.
 */
public class RemovePanel extends JPanel {

    private XmlTable xtab;
    private JButton rb;
    public JTextArea eroare;
    public JButton validateButton, xmlButton, removeTempButton, rollbackButton;
    public JTable tempTable;
    public JPanel p1;// = new JPanel();
    public JScrollPane sc;
    public JTree jj;
    public JPanel buttonPanel;

    public void buttons() {
        validateButton = new JButton("Validate changes!");
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    PostMethod post = new PostMethod("http://localhost:4500/finalSave");
                    HttpClient httpClient = new HttpClient();
                    int resp = httpClient.executeMethod(post);
                    eroare.setText(post.getResponseBodyAsString());
                    System.out.println("Am cerut validarea, iar raspunsul a fost: " + resp);
                    eroare.setText("Status: " + resp + " - " + post.getResponseBodyAsString());
                    Gson gson = new Gson();
                    HashMap<String, String> mp = gson.fromJson(post.getResponseBodyAsString(), HashMap.class);
                    eroare.setText(mp.get("data"));
                    if (resp == 200) {
                        eroare.setText("Validation OK!");
                        Modul3.getXML(Modul3.PATH);
                        p1.remove(sc);
                        jj = xtab.makeTree();
                        sc = new JScrollPane(jj);
                        sc.setPreferredSize(new Dimension(600, 312));
                        p1.add(sc);
                        p1.repaint();
                        p1.revalidate();
                        repaint();
                        DefaultTableModel tm = (DefaultTableModel) tempTable.getModel();
                        while (tm.getRowCount() > 0) {
                            tm.removeRow(0);
                        }
                        tm.setNumRows(0);
                        XmlBuildingParser x = new XmlBuildingParser(Modul3.PATH);
                        Modul3.currentMatrix = x.getMatrix();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(RemovePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(RemovePanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        xmlButton = new JButton("update XML schema");
        xmlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Modul3.getXML(Modul3.PATH);

                } catch (ProtocolException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public RemovePanel(XmlTable arg) throws IOException, IOException, SAXException, ParserConfigurationException {
        this.xtab = arg;
        rb = new JButton("Remove selected element");

        buttons();

        eroare = new JTextArea("No err so far");
        eroare.setPreferredSize(new Dimension(500, 50));
        eroare.setLineWrap(true);
        this.swing();
    }

    public static String getMsgFromDelete(String url, String json, int timeout, String method) {
        HttpURLConnection connection = null;
        try {

            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setRequestMethod(method);

            //set the sending type and receiving type to json
            connection.setRequestProperty("Content-Type", "application/json");
            //connection.setRequestProperty("Accept", "application/json");

            connection.setAllowUserInteraction(false);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            if (json != null) {
                //set the content length of the body
                connection.setRequestProperty("Content-length", json.getBytes().length + "");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);

                //send the json as body of the request
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(json.getBytes("UTF-8"));
                outputStream.close();
            }

            //Connect to the server
            connection.connect();

            int status = connection.getResponseCode();
            //Log.i("HTTP Client", "HTTP status code : " + status);
            switch (status) {
                case 200:
                case 201:
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    bufferedReader.close();
                    //Log.i("HTTP Client", "Received String : " + sb.toString());
                    //return received string
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            //Log.e("HTTP Client", "Error in http connection" + ex.toString());
        } catch (IOException ex) {
//        Log.e("HTTP Client", "Error in http connection" + ex.toString());
        } catch (Exception ex) {
            //      Log.e("HTTP Client", "Error in http connection" + ex.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception ex) {
                    //            Log.e("HTTP Client", "Error in http connection" + ex.toString());
                }
            }
        }
        return null;
    }

    public void swing() throws IOException, SAXException, ParserConfigurationException {
        jj = xtab.makeTree();
        //JFrame frame = new JFrame();
        //JPanel pane = new JPanel();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setLayout(new FlowLayout());
        p1 = new JPanel();
        JPanel p2 = new JPanel();
        jj.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jj.addTreeSelectionListener(new SelectionListener());
        jj.setEditable(true);
        sc = new JScrollPane(jj);
        setPreferredSize(new Dimension(620, 800));
        sc.setPreferredSize(new Dimension(600, 312));
        p1.add(sc);
        p1.setPreferredSize(new Dimension(620, 312));
        rb.addActionListener(new ActionListener() {
            //Aici Victor!!!!!!
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jj
                        .getLastSelectedPathComponent();
                String x = "";
                String req = "";

                if (selectedNode.isLeaf() == false) {
                    if (selectedNode.getDepth() == 1) {
                        req = "deleteRoom";
                        x = "{ \"name\" : \"" + selectedNode.toString() + "\"}";
                    }
                    if (selectedNode.getDepth() == 2) {
                        req = "deleteFloor";
                        x = "{ \"name\" : \"" + selectedNode.toString() + "\"}";
                    }
                } else {
                    String selectedNodeName = selectedNode.toString();
                    String roomName = selectedNode.getParent().toString();
                    String floorName = selectedNode.getParent().getParent().toString();
                    System.out.println("{" + selectedNodeName + ", \"room\" : \"" + roomName.trim() + "\", \"floor\": \"" + floorName.trim() + "\"}");
                    x = "{" + selectedNodeName + ", \"room\" : \"" + roomName.trim() + "\", \"floor\": \"" + floorName.trim() + "\"}";
                    req = "delete";
                }
                String msg = getMsgFromDelete("http://localhost:4500/" + req, x, 100, "POST");
                eroare.setText(msg);
                DefaultTreeModel model = (DefaultTreeModel) jj.getModel();
                DefaultMutableTreeNode mnode = (DefaultMutableTreeNode) jj.getLastSelectedPathComponent();
                model.removeNodeFromParent(mnode);
            }
        });

        p2.add(rb);
        add(p1);
        add(p2);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JPanel tmp = new JPanel();
        tmp.add(new JScrollPane(eroare));
        tmp.setSize(new Dimension(580, 50));
        add(tmp);
        buttonPanel.add(validateButton);

        removeTempButton = new JButton("Remove Temporary Item!");

        removeTempButton.addActionListener(new ActionListener() {
            //Aici Victor!!!!!!
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = tempTable.getSelectedRow();
                int y = tempTable.getSelectedColumn();
                String asd = (String) tempTable.getValueAt(x, y);
                System.out.println(asd);
                String msg = getMsgFromDelete("http://localhost:4500/delete", asd, 100, "POST");
                DefaultTableModel tm = (DefaultTableModel) tempTable.getModel();
                tm.removeRow(x);
                eroare.setText(msg);
            }
        });
        buttonPanel.add(removeTempButton);

        String[] asd = {"Temporary element"};
        tempTable = new JTable(new DefaultTableModel(asd, 0));//new String[0][0], asd);
        JScrollPane sc1 = new JScrollPane(tempTable);
        add(sc1);
        sc1.setPreferredSize(new Dimension(600, 330));
        JButton openUnity = new JButton("Open unity");
        openUnity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop dsk = Desktop.getDesktop();
                    dsk.open(new File(Modul3.RUN_PATH));
                } catch (IOException ex) {
                    Logger.getLogger(RemovePanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        buttonPanel.add(openUnity);
        rollbackButton = new JButton("Rollback");
        buttonPanel.add(rollbackButton);

        rollbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Modul3.rollback();
                //Clear temp table
                DefaultTableModel tm = (DefaultTableModel) tempTable.getModel();
                while (tm.getRowCount() > 0) {
                    tm.removeRow(0);
                }
                eroare.setText("Rollback success!");
                tm.setNumRows(0);
            }
        });

        buttonPanel.setSize(new Dimension(500, 200));
        add(buttonPanel);

        //Added tooltips
        validateButton.setToolTipText("<html>Checks if new building format is valid. <br>If so, it updates the building. This removes temporary data. <br>If not, error. Nothing is removed</html>");

        xmlButton.setToolTipText("Get latest XML schema.");

        rb.setToolTipText("Remove selected building element.");

        removeTempButton.setToolTipText("Remove building element from the temporary data table.");
    }

}
