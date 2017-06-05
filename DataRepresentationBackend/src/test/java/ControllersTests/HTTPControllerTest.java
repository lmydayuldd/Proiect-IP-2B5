package ControllersTests;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import DataRepresentationBackend.DataRepresentationBackendApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Project name DataRepresentationBackend.
 * Created by Turcu Nicusor on 17-May-17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataRepresentationBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HTTPControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void addDataShouldReturnInsertOperationSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"type\" : \"door\",\n" +
                        "\t\"room\" : \"x\",\n" +
                        "\t\"x1\" : 1,\n" +
                        "\t\"y1\" : 2,\n" +
                        "\t\"x2\" : 3,\n" +
                        "\t\"y2\" : 3,\n" +
                        "\t\"floor\" : 0,\n" +
                        "\t\"isExitWay\" : 2,\n" +
                        "\t\"isExterior\" : 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Insert operation success."))
        ;
    }

    @Test
    public void checkDataShouldReturnTrueIfThisDataExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/checkExists")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"type\" : \"door\",\n" +
                        "\t\"room\" : \"x\",\n" +
                        "\t\"x1\" : 1,\n" +
                        "\t\"y1\" : 2,\n" +
                        "\t\"x2\" : 3,\n" +
                        "\t\"y2\" : 3,\n" +
                        "\t\"floor\" : 0,\n" +
                        "\t\"isExitWay\" : 2,\n" +
                        "\t\"isExterior\" : 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Data exists in database."))
        ;
    }

    @Test
    public void deleteDataShouldReturnOKRequestIfDataCanBeDeleted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"type\" : \"door\",\n" +
                        "\t\"room\" : \"x\",\n" +
                        "\t\"x1\" : 1,\n" +
                        "\t\"y1\" : 2,\n" +
                        "\t\"x2\" : 3,\n" +
                        "\t\"y2\" : 3,\n" +
                        "\t\"floor\" : 0\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void checkDatShouldReturnFalseIfThisDataDoesNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/checkExists")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"type\" : \"door\",\n" +
                        "\t\"room\" : \"testtesttestetest\",\n" +
                        "\t\"x1\" : 1,\n" +
                        "\t\"y1\" : 2,\n" +
                        "\t\"x2\" : 3,\n" +
                        "\t\"y2\" : 3,\n" +
                        "\t\"floor\" : 0,\n" +
                        "\t\"isExitWay\" : 2,\n" +
                        "\t\"isExterior\" : 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Data does not exists in database."))
        ;
    }

    @Test
    public void deleteDataShouldReturnBadRequestIfDataDoesNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"type\" : \"erdhgfg\",\n" +
                        "\t\"room\" : \"x\",\n" +
                        "\t\"x1\" : 1,\n" +
                        "\t\"y1\" : 2,\n" +
                        "\t\"x2\" : 3,\n" +
                        "\t\"y2\" : 3,\n" +
                        "\t\"floor\" : 0,\n" +
                        "\t\"isExitWay\" : 2,\n" +
                        "\t\"isExterior\" : 3\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void temporarySaveShouldReturnSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/finalSave")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        ;
    }

    //region test related to invalid jsons
    @Test
    public void checkDataShouldReturnBadRequestIfJsonIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/checkExists")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void addDataShouldReturnBadRequestIfJsonIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"type\" : 6,\n" +
                        "\t\"room\" : \"Timisoara\",\n" +
                        "\t\"x1\" : 1,\n" +
                        "\t\"y1\" : 2,\n" +
                        "\t\"x2\" : 4,\n" +
                        "\t\"y2\" : 5,\n" +
                        "\t\"floor\" : 5,\n" +
                        "\t\"isExitWy\" : 3,\n" +
                        "\t\"isExterior\": 4\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void deleteDataShouldReturnBadRequestIfDataJsonIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"type\" : 6,\n" +
                        "\t\"room\" : \"Timisoara\",\n" +
                        "\t\"x1\" : 1,\n" +
                        "\t\"y1\" : 2,\n" +
                        "\t\"x2\" : 3,\n" +
                        "\t\"y2\" : 4,\n" +
                        "\t\"floor\" : 5,\n" +
                        "\t\"isExitWay\" : 3,\n" +
                        "\t\"isEfsafrior\": 214\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void deleteRoomShouldReturnBadRequestIfDataJsonIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"nme\" : \"sdad\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void deleteFloorShouldReturnBadRequestIfDataJsonIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/deleteFloor")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"nme\" : \"sdad\"\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
        ;
    }
    //endregion

    //region test related to http method
    @Test
    public void checkExistsShouldReturnMethodNotAllowedIfWrongHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/checkExists")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
        ;
    }

    @Test
    public void addShouldReturnMethodNotAllowedIfWrongHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
        ;
    }

    @Test
    public void deleteReturnMethodNotAllowedIfWrongHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
        ;
    }

    @Test
    public void temporarySaveShouldReturnMethodNotAllowedIfWrongHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/finalSave")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
        ;
    }

    @Test
    public void getXmlShouldReturnMethodNotAllowedIfWrongHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/getXML")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
        ;
    }

    @Test
    public void deleteRoomShouldReturnMethodNotAllowedIfWrongHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/deleteRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
        ;
    }

    @Test
    public void deleteFloorShouldReturnMethodNotAllowedIfWrongHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/deleteFloor")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
        ;
    }

    @Test
    public void getTemporaryDataShouldReturnMethodNotAllowedIfWrongHttpRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/getTemporaryData")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed())
        ;
    }
    //endregion
}
