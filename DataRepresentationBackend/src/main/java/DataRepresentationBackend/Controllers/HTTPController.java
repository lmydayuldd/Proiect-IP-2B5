package DataRepresentationBackend.Controllers;

import DataRepresentationBackend.Models.Message;
import DataRepresentationBackend.Models.TemporaryData;
import DataRepresentationBackend.Models.TemporarySaveMessage;
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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Message> addTemporaryData(@RequestBody TemporaryData data) {
        try {
            if (data.getElementType() == null || data.getFloor() == null || data.getIsExitWay() == null
                    || data.getIsExterior() == null || data.getRoom() == null || data.getX1() == null ||
                    data.getX2() == null || data.getY1() == null || data.getY2() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            databaseService.addData(data);
            databaseService.commit();
            return new ResponseEntity<>(new Message("Insert operation success!"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Message("Insert operation failed!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Message> deleteTemporaryData(@RequestBody TemporaryData data) {
        try {
            if (data.getElementType() == null || data.getFloor() == null || data.getIsExitWay() == null
                    || data.getIsExterior() == null || data.getRoom() == null || data.getX1() == null ||
                    data.getX2() == null || data.getY1() == null || data.getY2() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            databaseService.deleteData(data);
            databaseService.commit();
            return new ResponseEntity<>(new Message("Delete operation success!"), HttpStatus.OK);
        } catch (OperationNotSupportedException ex) {
            return new ResponseEntity<>(new Message(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Message("Delete operation failed!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/temporarysave", method = RequestMethod.POST)
    public ResponseEntity<TemporarySaveMessage> temporarySave() {
        try {
            return new ResponseEntity<>(new TemporarySaveMessage("Operation success!"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new TemporarySaveMessage("Delete operation failed!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
