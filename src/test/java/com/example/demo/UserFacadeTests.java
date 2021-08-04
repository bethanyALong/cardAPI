package com.example.demo;

import builders.RequestBuilder;
import com.example.demo.services.DatabaseFacade;
import com.example.demo.services.models.UserDetails;
import com.example.demo.services.UserFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserFacadeTests {

    @Autowired
    UserFacade userFacade;

    RequestBuilder requestBuilder = new RequestBuilder();

    @Autowired
    DatabaseFacade databaseFacade;

    @Test
    void registerUserSuccess(){
        UserDetails userDetails = requestBuilder.getUserDetails();
        assert userFacade.registerUser(userDetails).getStatusCode().is2xxSuccessful();
    }

    @Test
    void updateUserSuccess(){
        UserDetails userDetails = requestBuilder.getUserDetails();
        assert userFacade.updateUser(userDetails).getStatusCode().is2xxSuccessful();
    }

    @Test
    void deleteUserSuccess(){
        UserDetails userDetails = requestBuilder.getUserDetails();
        assert userFacade.deleteUser(userDetails).getStatusCode().is2xxSuccessful();
    }
}
