package gui;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by Vic on 6/2/2017.
 */
public class SelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent se) {
        /*
        JTree tree = (JTree) se.getSource();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
                .getLastSelectedPathComponent();
        String selectedNodeName = selectedNode.toString();
        if (selectedNode.isLeaf()) {

            System.out.println(selectedNodeName);

        }
*/
    }
}
