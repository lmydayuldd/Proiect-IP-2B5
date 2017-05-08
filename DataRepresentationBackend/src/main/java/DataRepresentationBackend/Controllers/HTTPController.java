package DataRepresentationBackend.Controllers;

import DataRepresentationBackend.Models.Message;
import DataRepresentationBackend.Models.TemporaryData;
import DataRepresentationBackend.Models.Test;
import DataRepresentationBackend.Services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 08-May-17.
 */
@RestController
public class HTTPController {
    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<Test> getTest() {
        Test response = new Test();
        response.dummyNumber = databaseService.test();
        return new ResponseEntity<Test>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> addTemporaryData(@RequestBody TemporaryData data) {
        try {
            databaseService.addData(data);
            databaseService.commit();
            return new ResponseEntity<Message>(new Message("Insert operation success!"), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<Message>(new Message("Insert operation failed!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> deleteTemporaryData(@RequestBody TemporaryData data) {
        try {
            databaseService.deleteData(data);
            databaseService.commit();
            return new ResponseEntity<Message>(new Message("Delete operation success!"), HttpStatus.OK);
        }
        catch (OperationNotSupportedException ex) {
            return new ResponseEntity<Message>(new Message(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<Message>(new Message("Delete operation failed!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
