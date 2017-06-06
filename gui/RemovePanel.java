package gui;

import app.Modul3;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Created by Vic on 6/3/2017.
 */
public class RemovePanel extends JPanel {
    private XmlTable xtab;
    private JButton rb;
    public JLabel eroare;
    public JButton validateButton, xmlButton, removeTempButton;
    public JTable tempTable;
    public JScrollPane sc;
    
    
    public void buttons()
    {
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
                    Modul3.getXML(Modul3.PATH);
                    remove(sc);
                    JTree jt = xtab.makeTree();
                    sc = new JScrollPane(jt);
                    add(sc);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(RemovePanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(RemovePanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                
             
            }
        });
        
        xmlButton = new JButton("get XML schema");
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
    
    public RemovePanel(XmlTable arg) throws IOException, IOException, SAXException, ParserConfigurationException{
        this.xtab= arg;
         rb = new JButton("Remove selected element");
         
         buttons();
         
        eroare = new JLabel("No err so far");
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
        JTree jj = xtab.makeTree();
        //JFrame frame = new JFrame();
        //JPanel pane = new JPanel();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        jj.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jj.addTreeSelectionListener(new SelectionListener());
        jj.setEditable(true);
        sc = new JScrollPane(jj);
        sc.setPreferredSize(new Dimension(600,512));
        p1.add(sc);
        p1.setPreferredSize(new Dimension(624,512));
        rb.addActionListener(new ActionListener() {
            //Aici Victor!!!!!!
            @Override
            public void actionPerformed(ActionEvent e) {
                
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jj
                        .getLastSelectedPathComponent();
                if(selectedNode.isLeaf() == false)
                {
                    System.err.println("Not a primary component!");
                    return;
                }
                String selectedNodeName = selectedNode.toString();
                String roomName = selectedNode.getParent().toString();
                String floorName = selectedNode.getParent().getParent().toString();
                System.out.println("{" + selectedNodeName + ", \"room\" : \"" + roomName.trim() + "\", \"floor\": \"" + floorName.trim() + "\"}");
                String x = "{" + selectedNodeName + ", \"room\" : \"" + roomName.trim() + "\", \"floor\": \"" + floorName.trim() + "\"}";
                
                /*
                DeleteMethod del = new DeleteMethod("http://localhost:4500/delete");
                
                URL url = new URL("http://www.example.com/resource");
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setRequestProperty(
                "Content-Type", "application/x-www-form-urlencoded" );
                httpCon.setRequestMethod("DELETE");
                httpCon.set
                httpCon.connect();
                
                HttpClient httpClient = new HttpClient();
                int resp = httpClient.executeMethod(del);
                
                if(resp==200){}
                */
                String msg = getMsgFromDelete("http://localhost:4500/delete", x, 100, "POST");
                eroare.setText(msg);
                DefaultTreeModel model = (DefaultTreeModel) jj.getModel();
                DefaultMutableTreeNode mnode = (DefaultMutableTreeNode) jj.getLastSelectedPathComponent();
                model.removeNodeFromParent(mnode);
            }
        });
        p2.add(rb);
        add(p1);
        add(p2);
        
        add(eroare);
        add(validateButton);
        add(xmlButton);
        
        
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
                eroare.setText(msg);
            }});
        add(removeTempButton);
        
        String[] asd = {"Temporary element"};
        tempTable = new JTable( new DefaultTableModel(asd,0));//new String[0][0], asd);
        JScrollPane sc = new JScrollPane(tempTable);
        add(sc);
    }
}
