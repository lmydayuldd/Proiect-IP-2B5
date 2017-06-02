package DataRepresentationBackend.Controllers;

import CustomExceptions.DataNotValidException;
import DataRepresentationBackend.Models.Message;
import DataRepresentationBackend.Models.SingleObject;
import DataRepresentationBackend.Models.TemporaryData;
import DataRepresentationBackend.Models.TemporarySaveMessage;
import DataRepresentationBackend.Services.DatabaseService;
import DataRepresentationBackend.Services.GetXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tablerepresentation.ElementManager;
import tablerepresentation.TableElement;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileAlreadyExistsException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 08-May-17.
 */
@RestController
public class HTTPController {
    @Autowired
    private DatabaseService databaseService;

    @CrossOrigin
    @RequestMapping(value = "/checkExists", method = RequestMethod.POST)
    public ResponseEntity<Message> checkExistsData(@RequestBody TemporaryData data) {
        try {
            if (!data.isValid())
                return new ResponseEntity<>(new Message("Invalid Json format."), HttpStatus.BAD_REQUEST);
            if (!data.containValidData())
                return new ResponseEntity<>(new Message("Invalid Json values."), HttpStatus.BAD_REQUEST);
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
            // to check error cannot insert null when "room" : ""
            if (!data.isValid())
                return new ResponseEntity<>(new Message("Invalid Json format."), HttpStatus.BAD_REQUEST);
            if (databaseService.checkExistsData(data))
                return new ResponseEntity<>(new Message("Element already exists."), HttpStatus.CONFLICT);
            if (!data.containValidData())
                return new ResponseEntity<>(new Message("Invalid Json values."), HttpStatus.BAD_REQUEST);
            databaseService.addData(data);
            databaseService.commit();
            return new ResponseEntity<>(new Message("Insert operation success."), HttpStatus.OK);
        } catch (SQLException sqlEx) {
            return new ResponseEntity<>(new Message("Room name must be unique."), HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Message("Insert operation failed. " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Message> deleteTemporaryData(@RequestBody TemporaryData data) {
        try {
            if (!data.isValid())
                return new ResponseEntity<>(new Message("Invalid Json format."), HttpStatus.BAD_REQUEST);
            if (!data.containValidData())
                return new ResponseEntity<>(new Message("Invalid Json values."), HttpStatus.BAD_REQUEST);
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
    @RequestMapping(value = "/deleteRoom", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Message> deleteRoom(@RequestBody SingleObject room) {
        try {
            if (!room.isValid())
                return new ResponseEntity<>(new Message("Invalid Json format."), HttpStatus.BAD_REQUEST);
            databaseService.deleteRoom(room);
            databaseService.commit();
            return new ResponseEntity<>(new Message("Delete operation success."), HttpStatus.OK);
        } catch (OperationNotSupportedException ex) {
            return new ResponseEntity<>(new Message(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Message("Delete operation failed."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteFloor", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Message> deleteFloor(@RequestBody SingleObject floor) {
        try {
            if (!floor.isValid())
                return new ResponseEntity<>(new Message("Invalid Json format."), HttpStatus.BAD_REQUEST);
            databaseService.deleteFloor(floor);
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
            ArrayList<TableElement> building = databaseService.getTemporaryDataTable();
            ElementManager elementManager = new ElementManager(building);
            try {
                elementManager.validateElements();
            } catch (DataNotValidException e) {
                return new ResponseEntity<>(new TemporarySaveMessage("Building is invalid.", e.getMessage()), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new TemporarySaveMessage("Another exception throwed by validate Elements.", e.getMessage()), HttpStatus.OK);
            }
            try {
                databaseService.replicateData();
                return new ResponseEntity<>(new TemporarySaveMessage("Operation success."), HttpStatus.OK);
            } catch (Exception ex) {
                return new ResponseEntity<>(new TemporarySaveMessage("Error at replicate data in database" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(new TemporarySaveMessage("Final save operation failed." + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/getXML", method = RequestMethod.GET)
    public HttpEntity<?> getXML() {
        GetXML xmlEntity = new GetXML();

        try {
            String xml = xmlEntity.getXML();
            byte[] documentBody = xml.getBytes();
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "xml"));
            header.setContentLength(documentBody.length);
            return new HttpEntity<>(documentBody, header);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Message("Get XML operation failed" + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/getTemporaryData", method = RequestMethod.GET)
    ResponseEntity<?> getTemporaryData() {
        try {
            ArrayList<TemporaryData> data = databaseService.getTemporaryDataElements();
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Message("Get temporary data failed " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
