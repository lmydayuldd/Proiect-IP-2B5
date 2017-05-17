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
    public void testWillAlwaysReturnTrue() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void addDataShouldReturnInsertOperationSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"elementType\" : 6,\n" +
                        "\t\"room\" : \"Timisoara\",\n" +
                        "\t\"x1\" : 1,\n" +
                        "\t\"y1\" : 2,\n" +
                        "\t\"x2\" : 3,\n" +
                        "\t\"y2\" : 4,\n" +
                        "\t\"floor\" : 5,\n" +
                        "\t\"isExitWay\" : 3,\n" +
                        "\t\"isExterior\": 4\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Insert operation success."))
                ;
    }

    @Test
    public void checkDatShouldReturnTrueIfThisDataExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/checkExists")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"elementType\" : 6,\n" +
                        "\t\"room\" : \"Timisoara\",\n" +
                        "\t\"x1\" : 1,\n" +
                        "\t\"y1\" : 2,\n" +
                        "\t\"x2\" : 3,\n" +
                        "\t\"y2\" : 4,\n" +
                        "\t\"floor\" : 5,\n" +
                        "\t\"isExitWay\" : 3,\n" +
                        "\t\"isExterior\": 4\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Data exists in database."))
                ;
    }
}
