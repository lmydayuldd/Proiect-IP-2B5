package DataRepresentationBackend.Controllers;

import DataRepresentationBackend.Models.Message;
import DataRepresentationBackend.Models.TemporaryData;
import DataRepresentationBackend.Models.TemporarySaveMessage;
import DataRepresentationBackend.Services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileAlreadyExistsException;
import java.sql.*;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 08-May-17.
 */
@RestController
public class HTTPController {
    @Autowired
    private DatabaseService databaseService;

    public Boolean isBadRequest(TemporaryData data) {
        if (data.getElementType() == null || data.getFloor() == null || data.getIsExitWay() == null
                || data.getIsExterior() == null || data.getRoom() == null || data.getX1() == null ||
                data.getX2() == null || data.getY1() == null || data.getY2() == null)
            return true;
        return false;
    }

    /* The method which takes everything from BUILDING_DATA and generates the XML in the desired output file */

    private void getXML(Connection connection) throws Exception {
        String SQL = "select * from BUILDING_DATA"; // Selecting everything from the working table
        String OUTPUTFILE = "D:\\coordonate.xml";

        Connection conn = connection;

        try {
            /* Executing the SQL statement */
            Statement statement = conn.createStatement();
            ResultSet queryeRS = statement.executeQuery(SQL);

            /* Building the XML DOM */
            Document xmlDoc = buildXML(queryeRS);

            /* Writing in the output file given OUTPUTFILE */
            File outputFile = new File(OUTPUTFILE);
            printDOM(xmlDoc, outputFile);

            conn.close();
        }   catch(FileAlreadyExistsException f){
            System.out.println("There is already a file with this name in this location");
        }

        catch (Exception e) {
            System.out.println("Really poor exception handling " + e.toString());
        }
    }

    /* The method that builds the XML */
    private static Document buildXML(ResultSet _queryRS) throws Exception
    {
        Document xmlDoc = new DocumentImpl();

        /* Creating the root element */

        Element rootElement = xmlDoc.createElement("building"); // Root element, our building
        xmlDoc.appendChild(rootElement);

        while(_queryRS.next())
        {

            Element bigElement = xmlDoc.createElement("element");

            /* Creating subelements within current element tag*/
            Element smallElement_type = xmlDoc.createElement("type");
            Element smallElement_x1 = xmlDoc.createElement("x1");
            Element smallElement_y1 = xmlDoc.createElement("y1");
            Element smallElement_x2 = xmlDoc.createElement("x2");
            Element smallElement_y2 = xmlDoc.createElement("y2");
            Element smallElement_floor = xmlDoc.createElement("floor");
            Element smallElement_isExterior = xmlDoc.createElement("isExterior");
            Element smallElement_isExit = xmlDoc.createElement("isExit");

            /* Populating the subelements with actual data*/
            smallElement_type.appendChild(xmlDoc.createTextNode(_queryRS.getString("type")));
            smallElement_x1.appendChild(xmlDoc.createTextNode(_queryRS.getString("x1")));
            smallElement_y1.appendChild(xmlDoc.createTextNode(_queryRS.getString("y1")));
            smallElement_x2.appendChild(xmlDoc.createTextNode(_queryRS.getString("x2")));
            smallElement_y2.appendChild(xmlDoc.createTextNode(_queryRS.getString("y2")));
            smallElement_floor.appendChild(xmlDoc.createTextNode(_queryRS.getString("floor")));
            smallElement_isExterior.appendChild(xmlDoc.createTextNode(_queryRS.getString("isExterior")));
            smallElement_isExit.appendChild(xmlDoc.createTextNode(_queryRS.getString("isExit")));

            /* Adding subelements to the bigElement*/
            bigElement.appendChild(smallElement_type);
            bigElement.appendChild(smallElement_x1);
            bigElement.appendChild(smallElement_y1);
            bigElement.appendChild(smallElement_x2);
            bigElement.appendChild(smallElement_y2);
            bigElement.appendChild(smallElement_floor);
            bigElement.appendChild(smallElement_isExterior);
            bigElement.appendChild(smallElement_isExit);

            rootElement.appendChild(bigElement);
        }
        return xmlDoc;
    }

    /* Method used by getXML to serialize the XML */
    private static void printDOM(Document _xmlDoc, File _outputFile) throws Exception
    {
        OutputFormat outputFormat = new OutputFormat("XML","UTF-8",true);
        FileWriter fileWriter = new FileWriter(_outputFile);

        XMLSerializer xmlSerializer = new XMLSerializer(fileWriter, outputFormat);

        xmlSerializer.asDOMSerializer();

        xmlSerializer.serialize(_xmlDoc.getDocumentElement());
    }

    @CrossOrigin
    @RequestMapping(value = "/checkExists", method = RequestMethod.POST)
    public ResponseEntity<Message> checkExistsData(@RequestBody TemporaryData data) {
        try {
            if (isBadRequest(data)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            if (databaseService.checkExistsData(data))
                return new ResponseEntity<>(new Message("Data exists in database."), HttpStatus.OK);
            return new ResponseEntity<>(new Message("Data does not exists in database."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Message("Check operation failed."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> addTemporaryData(@RequestBody TemporaryData data) {
        try {
            if (isBadRequest(data)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            databaseService.addData(data);
            databaseService.commit();
            return new ResponseEntity<>(new Message("Insert operation success."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Message("Insert operation failed. " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Message> deleteTemporaryData(@RequestBody TemporaryData data) {
        try {
            if (isBadRequest(data)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            databaseService.deleteData(data);
            databaseService.commit();
            return new ResponseEntity<>(new Message("Delete operation success."), HttpStatus.OK);
        } catch (OperationNotSupportedException ex) {
            return new ResponseEntity<>(new Message(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Message("Delete operation failed."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/finalSave", method = RequestMethod.POST)
    public ResponseEntity<TemporarySaveMessage> temporarySave() {
        try {
            return new ResponseEntity<>(new TemporarySaveMessage("Operation success."), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new TemporarySaveMessage("Delete operation failed."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
