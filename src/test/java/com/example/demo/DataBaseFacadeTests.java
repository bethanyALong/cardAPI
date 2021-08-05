package com.example.demo;

import builders.RequestBuilder;
import com.example.demo.services.DatabaseFacade;
import com.example.demo.models.UserDetails;
import com.example.demo.services.UserDetailsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;

@SpringBootTest
public class DataBaseFacadeTests {

    @Autowired
    DatabaseFacade databaseFacade;

    @Mock
    UserDetailsRepository userDetailsRepository;

    RequestBuilder requestBuilder = new RequestBuilder();
    UserDetails userDetails = requestBuilder.getUserDetails();


    @Test
    void registerUserSuccess(){
        when(userDetailsRepository.save(userDetails)).thenReturn(userDetails);
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    void registerUserFailureDueToDatabaseException(){
        when(userDetailsRepository.save(userDetails)).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void registerUserFailureDueToNullFirstName(){
        userDetails.setFirstName(null);
        when(userDetailsRepository.save(userDetails)).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void registerUserFailureDueToNullLastName(){
        userDetails.setLastName(null);
        when(userDetailsRepository.save(userDetails)).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void registerUserFailureDueToNullEmail(){
        userDetails.setEmailAddress(null);
        when(userDetailsRepository.save(userDetails)).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void registerUserFailureDueInvalidEmail(){
        userDetails.setEmailAddress("bethany");
        when(userDetailsRepository.save(userDetails)).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void registerUserFailureDueInvalidFirstName(){
        userDetails.setFirstName("");
        when(userDetailsRepository.save(userDetails)).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
