package DataRepresentationBackend.Controllers;

import DataRepresentationBackend.Models.Test;
import DataRepresentationBackend.Services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
