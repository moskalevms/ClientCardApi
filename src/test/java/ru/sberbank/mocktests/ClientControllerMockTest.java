package ru.sberbank.mocktests;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.adapter.JavaBeanBooleanPropertyBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.jest.HttpClientConfigBuilderCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.sberbank.entities.Client;
import ru.sberbank.repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClientControllerMockTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository mockRepository;

    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void init() {
        Client client = new Client();
        client.setClient_id(1L);
        client.setLogin("cbxcb");
        client.setPassword("gfbbxc");
        client.setFirstname("bxcbcb");
        client.setLastname("dfgdbdbbxb");
        when(mockRepository.findById(1L)).thenReturn(Optional.of(client));
    }


    @Test
    public void showAllclients() throws Exception {
        List<Client> clients = new ArrayList<>();

        Client client1 = new Client();
        client1.setClient_id(2L);
        client1.setLogin("vcbcbggfd");
        client1.setPassword("ouyo");
        client1.setFirstname("xcvxv");
        client1.setLastname("uyooiuo");


        Client client2 = new Client();
        client2.setClient_id(3L);
        client2.setLogin("yoioyo");
        client2.setPassword("cbncvcvbn");
        client2.setFirstname("iopip");
        client2.setLastname("xcvbxcbc");

        clients.add(client1);
        clients.add(client2);


        when(mockRepository.findAll()).thenReturn(clients);



        String expected = om.writeValueAsString(clients);

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/app/api/clients", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), false);

        verify(mockRepository, times(1)).findAll();


        /*
        mockMvc.perform(get("/clients"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].client_id", is(2)))
                .andExpect(jsonPath("$[0].login", is("vcbcbggfd")))
                .andExpect(jsonPath("$[0].password", is("ouyo")))
                .andExpect(jsonPath("$[0].firstname", is("xcvxv")))
                .andExpect(jsonPath("$[0].lastname", is("uyooiuo")))
                .andExpect(jsonPath("$[1].client_id", is(3)))
                .andExpect(jsonPath("$[1].login", is("yoioyo")))
                .andExpect(jsonPath("$[1].password", is("cbncvcvbn")))
                .andExpect(jsonPath("$[1].firstname", is("iopip")))
                .andExpect(jsonPath("$[1].lastname", is("xcvbxcbc")));

        verify(mockRepository, times(1)).findAll();
*/
    }


}