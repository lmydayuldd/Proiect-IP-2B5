package app;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server for receiving xml file from module1
 * To do
 * - function to check if the received xml is valid
 * - add protocol messages like "Valid Xml received" , "Invalid Xml received"
 * - first recieve size of xml file, second receive the xml file
 * - ...
 */

public class Server {
    private static final int PORT = 1996;
    private ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public void run() throws IOException, ParserConfigurationException, SAXException {
        Socket socket = serverSocket.accept();
        System.out.println("Someone has connected");
        XMLInput.getXMLFile(socket.getInputStream(),"B:\\input2.txt");
    }
}
