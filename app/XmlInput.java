package app;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Vasile Catana on 5/20/2017.
 * This class recieves a byte array and interprets it as xml file.
 */


public class XmlInput extends ByteArrayInputStream {
    private DataInputStream dataInputStream;

    public XmlInput(InputStream inputStream) {
        super(new byte[11]); // harcoded
        this.dataInputStream = new DataInputStream(inputStream);
    }

    /**
     * reads binary data from socket
     */
    private void receive() throws IOException {
        byte[] data = new byte[500]; //harcoded .. to change..
        this.dataInputStream.read(data, 0, 500);
        this.buf = data;
    }


    /**
     * Saves bytes recieved via @param inputStream in file @param pathToSave
     */
    public static void getXMLFile(InputStream inputStream, String pathToSave) throws ParserConfigurationException, IOException, SAXException {
        XmlInput xmlInput = new XmlInput(inputStream);
        xmlInput.receive();
        String str = new String(xmlInput.buf, StandardCharsets.UTF_8);
        str = str.replaceAll("[^\\x20-\\x7e]", "");
        File file = new File(pathToSave);
        PrintWriter out = new PrintWriter(file);
        out.println(str);
        out.close();
    }

}
