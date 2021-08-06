package com.example.demo.api;

import builders.RequestBuilder;
import builders.ResponseBuilder;
import com.example.demo.models.ResponseModel;
import com.example.demo.models.Stores;
import com.example.demo.models.UserDetails;
import com.example.demo.services.DatabaseFacade;
import com.example.demo.services.UserDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserApiApplicationController.class)
@RunWith(SpringRunner.class)
public class CardAPIApplicationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @MockBean
    DatabaseFacade databaseFacade;


    RequestBuilder requestBuilder = new RequestBuilder();
    ResponseBuilder responseBuilder = new ResponseBuilder();
    ResponseEntity<ResponseModel> successResponse = responseBuilder.getSuccessResponseRegister();
    UserDetails userDetails = requestBuilder.getUserDetails();
    Stores stores = requestBuilder.getStores();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void registerUserSuccess() throws Exception {
        String jsonInString = objectMapper.writeValueAsString(userDetails);
        this.mockMvc
                .perform(post("/register-user")
                .content(jsonInString)
                .contentType("application/json")
                .header("x-auth-token", "test"))
                .andExpect(status().isOk());
    }

    @Test
    public void registerUserInvalidHeader() throws Exception {
        String jsonInString = objectMapper.writeValueAsString(userDetails);
        this.mockMvc
                .perform(post("/register-user")
                        .content(jsonInString)
                        .contentType("application/json")
                        .header("x-auth-token", "fail"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void switchVendorSuccess() throws Exception {
        String jsonInString = objectMapper.writeValueAsString(stores);
        this.mockMvc
                .perform(post("/vendor-switch")
                        .content(jsonInString)
                        .contentType("application/json")
                        .header("x-auth-token", "test")
                        .header("userID", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void switchVendorFailureDueToInvalidInput() throws Exception {
        stores = null;
        String jsonInString = objectMapper.writeValueAsString(stores);
        this.mockMvc
                .perform(post("/vendor-switch")
                        .content(jsonInString)
                        .contentType("application/json")
                        .header("x-auth-token", "test")
                        .header("userID", 1))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void switchVendorFailureDueToInvalidAuthToken() throws Exception {
        String jsonInString = objectMapper.writeValueAsString(stores);
        this.mockMvc
                .perform(post("/vendor-switch")
                        .content(jsonInString)
                        .contentType("application/json")
                        .header("x-auth-token", "fail")
                        .header("userID", 1))
                .andExpect(status().isForbidden());
    }

}
