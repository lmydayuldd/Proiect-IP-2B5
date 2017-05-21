package app;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Vasile Catana on 5/20/2017.
 */


public class XMLInput  extends ByteArrayInputStream{
    private DataInputStream dataInputStream;

    public XMLInput(InputStream inputStream)
    {
        super(new byte[11]); // harcoded
        this.dataInputStream = new DataInputStream(inputStream);
    }

    private void receive() throws IOException {
        byte[] data = new byte[500]; //harcoded .. to change..
        this.dataInputStream.read(data,0,500);
        this.buf = data;
    }

    public static void getXMLFile(InputStream inputStream, String pathToSave) throws ParserConfigurationException, IOException, SAXException {
        XMLInput xmlInput = new XMLInput(inputStream);
        xmlInput.receive();
        String str = new String(xmlInput.buf, StandardCharsets.UTF_8);
        str = str.replaceAll("[^\\x20-\\x7e]", "");
        File file = new File(pathToSave);
        PrintWriter out = new PrintWriter(file);
        out.println(str);
        out.close();
    }

}
