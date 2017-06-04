package gui;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Vic on 6/3/2017.
 */
public class RemovePanel extends JPanel {
    private XmlTable xtab;
    private JButton rb;
    public RemovePanel(XmlTable arg) throws IOException, IOException, SAXException, ParserConfigurationException{
        this.xtab= arg;
         rb = new JButton("Remove selected element");
        this.swing();
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
        JScrollPane sc = new JScrollPane(jj);
        sc.setPreferredSize(new Dimension(600,512));
        p1.add(sc);
        p1.setPreferredSize(new Dimension(624,512));
        rb.addActionListener(new ActionListener() {
            //Aici Victor!!!!!!
            @Override
            public void actionPerformed(ActionEvent e) {
                
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jj
                        .getLastSelectedPathComponent();
                String selectedNodeName = selectedNode.toString();
                String roomName = null;
                System.out.println("{" + selectedNodeName + ", room = " + roomName + "}");
                
                DefaultTreeModel model = (DefaultTreeModel) jj.getModel();
                DefaultMutableTreeNode mnode = (DefaultMutableTreeNode) jj.getLastSelectedPathComponent();
                model.removeNodeFromParent(mnode);

            }
        });
        p2.add(rb);
        add(p1);
        add(p2);
        
    }
}
