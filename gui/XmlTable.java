package gui;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import org.w3c.dom.Document;

/**
 * Created by Vic on 6/2/2017.
 */
public class XmlTable {
    private String xmlpath;

    public XmlTable(String path) {
        this.xmlpath = path;
    }

    public JTree makeTree() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        StringBuilder xmlStringBuilder = new StringBuilder();
        File file = new File(this.xmlpath);

        Document document = builder.parse(file);
        document.getDocumentElement().normalize();
        DefaultMutableTreeNode floors = new DefaultMutableTreeNode("floors");
        JTree tree = new JTree(floors);

        //parse
        //get floors element
        NodeList nodeList = document.getElementsByTagName("floors");

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node node = nodeList.item(i);
            //get floor element
            NodeList nodeListFloor = document.getElementsByTagName("floor");
            for (int i1=0; i1<nodeListFloor.getLength(); ++i1)
            {
                Node nodeFloor = nodeListFloor.item(i1);
                Element elementFloor = (Element) nodeFloor;
                Attr attr = ((Element) nodeFloor).getAttributeNode("number");
                Integer floor = Integer.parseInt(attr.getValue());
                DefaultMutableTreeNode fl = new DefaultMutableTreeNode(floor);
                Enumeration<DefaultMutableTreeNode> e = floors.depthFirstEnumeration();
                Integer check = 0;
                DefaultMutableTreeNode aux = new DefaultMutableTreeNode();
                while (e.hasMoreElements()) {
                    aux=e.nextElement();
                    if(aux.toString().equals(fl.toString())) {
                        check = 1;
                        fl=aux;
                    }
                }
                if(check==0) {
                    floors.add(fl);
                }
                //maxFloor = Integer.max(maxFloor,floor);

                NodeList nodeListRoom = elementFloor.getElementsByTagName("room");

                for (int i2=0; i2<nodeListRoom.getLength(); ++i2) {
                    Node nodeRoom = nodeListRoom.item(i2);

                    Element elementRoom = (Element) nodeRoom;
                    Node nName = elementRoom.getElementsByTagName("name").item(0);
                    String name = nName.getTextContent();
                    DefaultMutableTreeNode rr = new DefaultMutableTreeNode(name);
                    Enumeration<DefaultMutableTreeNode> ee = fl.depthFirstEnumeration();
                    Integer chk = 0;
                    DefaultMutableTreeNode auxr = new DefaultMutableTreeNode();
                    while (ee.hasMoreElements()) {
                        auxr=ee.nextElement();
                        if(auxr.toString().equals(rr.toString())) {
                            chk = 1;
                            rr=auxr;
                        }
                    }
                    if(chk==0) {
                        fl.add(rr);
                        //floors.add(fl);
                    }

                    //Element elementRoom = (Element) nodeRoom;

                    NodeList nodeListType = elementRoom.getElementsByTagName("type");

                    Node     nodeName = elementRoom.getElementsByTagName("name").item(0);

                    boolean isStairs = false;

                    if (nodeName.getTextContent().toLowerCase().contains(new String("Stairs").toLowerCase()))
                    {
                        isStairs = true;
                    }

                    for (int i3 = 0; i3<nodeListType.getLength(); ++i3)
                    {
                        Node nodeType = nodeListType.item(i3);
                        Element elementType = (Element) nodeType;
                        Attr attrType = ((Element) nodeType).getAttributeNode("name");

                        String type = attrType.getValue();

                        int x1 = (Integer.parseInt(elementType.getElementsByTagName("x1").item(0).getTextContent().toString().trim()));
                        int y1 = (Integer.parseInt(elementType.getElementsByTagName("y1").item(0).getTextContent().toString().trim()));
                        int x2 = (Integer.parseInt(elementType.getElementsByTagName("x2").item(0).getTextContent().toString().trim()));
                        int y2 = (Integer.parseInt(elementType.getElementsByTagName("y2").item(0).getTextContent().toString().trim()));

                        DefaultMutableTreeNode point;
                        String[] arguments;
                        arguments = new String[]{type, String.valueOf(x1), String.valueOf(y1), String.valueOf(x2), String.valueOf(y2)};
                        point = new DefaultMutableTreeNode("type: " + arguments[0] +", x1: "+ arguments[1] +  ", y1: " + arguments[2] + ", x2: " + arguments[3] + ", y2: " + arguments[4]);
                        rr.add(point);
                        if (type.equals("wall") || type.equals("stairs"))
                        {
                            //          fillWall(x1,y1,x2,y2,floor,1);

                        }

                        if (type.equals("door") && !isStairs)
                        {
                            //          fillWall(x1,y1,x2,y2,floor,0);
                        }

                        // System.out.println(isStairs+" sdaj,njasdkadsjsjakjdsa");
                        if (type.equals("door") && isStairs)
                        {
                            //          fillWall(x1,y1,x2,y2,floor,STAIRS_NUMBER);
                            // System.out.println("sunt aici, gaseste-ma");
                            //Tamara's Magic  Number
                        }
                    }
                }

            }

        }
        System.out.println("Am iesit din parse");
        return tree;
    }
}
