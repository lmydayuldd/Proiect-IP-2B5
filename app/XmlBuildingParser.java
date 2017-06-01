package app;

/**
 * Created by Vasile Catana, Tamara Trifan, Cristina Ulinici  on 5/18/2017.
 * Modified a bit by Alex... small changes. Can't remomber exact what
 */

import  org.w3c.dom.*;
import  org.xml.sax.SAXException;
import  javax.xml.parsers.*;
import  java.io.*;

public class XmlBuildingParser {
    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	
    private int maxLength = 0;
    private int maxWidth = 0;
    private int maxFloor = 0;

    private static int STAIRS_NUMBER = 3;
	
    private final String pathXml;
    private static int[][][] rawMatrix = new int[Matrix.LEVEL_COUNT][Matrix.DIMENSION][Matrix.DIMENSION];

    public XmlBuildingParser(String pathXml)
    {
        maxLength = 500;
        maxWidth = 500;
        maxFloor = 6;
        this.pathXml = pathXml; // path where to read XML file
    }
/**
* parse a xml file and extract data
*/
//    public static void main(String[] argv) throws IOException, SAXException, ParserConfigurationException {
//        XmlBuildingParser xmlBuildingParser = new XmlBuildingParser("B:\\input.xml");
//        Matrix matrix = new Matrix();
//        matrix = xmlBuildingParser.getMatrix();
//
//        for (int etaj = 1; etaj<4; ++etaj)
//        {
//            for (int i=0; i<30; ++i)
//            {
//                for (int j=0; j<30; ++j)
//                {
//                    System.out.print(matrix.getCell(etaj,i,j).getFree()+" ");
//                }
//                System.out.println();
//            }
//            System.out.println("\n\n\n");
//        }
//
//    }

    public void parse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = factory.newDocumentBuilder();

        
        //TODO put 0 on door on rawMatrix!!!!

        //TODO Test with parsing xml file (check Matrix)
        
        StringBuilder xmlStringBuilder = new StringBuilder();
        File file = new File(this.pathXml);

        Document document = builder.parse(file);
        document.getDocumentElement().normalize();


        rawMatrix = new int[Matrix.LEVEL_COUNT][Matrix.DIMENSION][Matrix.DIMENSION];
	   
	 //set rawMatrix with 0
	 // rawMatrix is used to the process of builing the 3D matrix   
        for (int i=0; i<Matrix.LEVEL_COUNT; ++i)
        {
            for (int j=0; j<Matrix.DIMENSION; ++j)
            {
                for (int i1=0; i1<Matrix.DIMENSION; ++i1)
                {
                    rawMatrix[i][j][i1]=0;
                }
            }
        }
	//parse the XML elements

        NodeList nodeList = document.getElementsByTagName("floors");

        for (int i=0; i<nodeList.getLength(); ++i)
        {
            Node node = nodeList.item(i);

            NodeList nodeListFloor = document.getElementsByTagName("floor");


            for (int i1=0; i1<nodeListFloor.getLength(); ++i1)
            {
                Node nodeFloor = nodeListFloor.item(i1);
                Element elementFloor = (Element) nodeFloor;
                Attr attr = ((Element) nodeFloor).getAttributeNode("number");
                Integer floor = Integer.parseInt(attr.getValue());

                maxFloor = Integer.max(maxFloor,floor);

                NodeList nodeListRoom = elementFloor.getElementsByTagName("room");

                for (int i2=0; i2<nodeListRoom.getLength(); ++i2) {
                    Node nodeRoom = nodeListRoom.item(i2);
                    Element elementRoom = (Element) nodeRoom;

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

                        int x1 = (Integer.parseInt(elementType.getElementsByTagName("x1").item(0).getTextContent().toString().trim()))*10;
                        int y1 = (Integer.parseInt(elementType.getElementsByTagName("y1").item(0).getTextContent().toString().trim()))*10;
                        int x2 = (Integer.parseInt(elementType.getElementsByTagName("x2").item(0).getTextContent().toString().trim()))*10;
                        int y2 = (Integer.parseInt(elementType.getElementsByTagName("y2").item(0).getTextContent().toString().trim()))*10;
                        //System.out.println(x1+" "+y1+" "+x2+" "+y2);
                        if (type.equals("wall") || type.equals("stairs"))
                        {
                            fillWall(x1,y1,x2,y2,floor,1);
                        }

                        if (type.equals("door") && !isStairs)
                        {
                            fillWall(x1,y1,x2,y2,floor,0);
                        }

                       // System.out.println(isStairs+" sdaj,njasdkadsjsjakjdsa");
                        if (type.equals("door") && isStairs)
                        {
                            fillWall(x1,y1,x2,y2,floor,STAIRS_NUMBER);
                           // System.out.println("sunt aici, gaseste-ma");
                            //Tamara's Magic  Number
                        }
                    }
                }
            }
        }
        System.out.println("Am iesit din parse");
    }
	
    private int getX(int x1, int y1, int x2, int y2, int y){
	return ( (y-y1)*(x2-x1) / (y2-y1) + x1 ); // some formula for nerds
    }

    private int getY(int x1, int y1, int x2, int y2, int x){
        return ( ((x-x1))*((y2-y1)) / ((x2-x1)) + y1 ); // some formula for nerds
    }
	/*
		We use Equation of a line to create the walls
		(x - x1) / (x2 -x1 ) = (y - y1) / (y2 - y1)
	*/
    public void fillWall( int x1, int y1, int x2, int y2, int floor, int value) {
        if (Math.abs(y2 - y1) > Math.abs(x2 - x1)) {
           // if (y1 > y2) {
                //y1 = swap(y2, y2 = y1); //@Vasile - check this; @Alex - checked
                int a1, a2;
                a2 = Math.max(y1, y2);
                a1 = Math.min(y1, y2);

           // }
            for (int y = a1; y <= a2; ++y) {
                rawMatrix[floor][getX(x1, y1, x2, y2, y)][y] = value;
                }
            }
         else {
         //   if (x1>x2) {
              // x1 = swap(x2, x2 = x1); //@Vasile - check this; @Alex - checked
            int a1, a2;
            a2 = Math.max(x1, x2);
            a1 = Math.min(x1, x2);
          //  }
            for (int x = a1; x <= a2; ++x) {
                rawMatrix[floor][x][getY(x1, y1, x2, y2, x)] = value;
                }
            }
    }
	

    public int getRawMatrixCell(int x, int y, int z){
        return rawMatrix[z][x][y];
    }

    public int[][][] getRawMatrix(){
        return rawMatrix;
    }



    /*
       In rawMatrix:
       1 - for wall
       0 - free space
       
       toMatrix - transfrom data from rawMatrix to Matrix()
     */

    public Matrix toMatrix(int[][][] a)
    {
        Matrix matrix = new Matrix();

        for (int etaj=0; etaj<maxFloor; ++etaj)
        {
            for (int i=0; i<maxWidth; ++i)
            {
                for (int j=0; j<maxLength; ++j)
                {
                    int x = 0; // value for setting wall positioning

                    if (i != 0) {
                         if (a[etaj][i-1][j] == 1)
                         {
                             x+=8; // west wall
                         }
                    }

                    if (i != maxWidth - 1) {
                        if (a[etaj][i+1][j] == 1)
                        {
                            x+=16; // east wall
                        }
                    }

                    if (j != 0) {
                        if (a[etaj][i][j-1] == 1 )
                        {
                            x+=4; // south wall
                        }
                    }

                    if (j != maxLength - 1) {
                        if (a[etaj][i][j+1] == 1)
                        {
                            x+=32; // north wall
                        }

                    }

                    if (a[etaj][i][j]==STAIRS_NUMBER)
                    {
                        if (etaj != maxFloor)
                        {
                            x+=2;
                        }
                        if (etaj != 0)
                        {
                            x+=1;
                        }
                    }
                    //System.out.println("Cellll"+i+" " + j + " " + etaj + " valoare: "+ x);
                    int isFree = 1;
                    if (a[etaj][i][j] == 1) {
                        isFree = 0;
                    }
                    Cell cell = new Cell(x,isFree);
                    matrix.setCell(cell,etaj,i,j); // setting our value
                }
            }
        }

        return  matrix;
    }

    public Matrix getMatrix() throws IOException, SAXException, ParserConfigurationException {
        parse();
        return toMatrix(rawMatrix);
    }
   //swap method
   // if it is confusing, check the call 
    private static int swap(int x, int y)
    {
	    return x;
    }

}
