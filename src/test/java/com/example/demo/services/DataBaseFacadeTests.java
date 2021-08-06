package com.example.demo.services;

import builders.RequestBuilder;
import com.example.demo.models.ResponseModel;
import com.example.demo.models.UserDetails;
import org.springframework.data.repository.Repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
public class DataBaseFacadeTests {

    @Autowired
    DatabaseFacade databaseFacade;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void test(){
        UserDetails savedInDB = entityManager.persist(userDetails);
        Optional<UserDetails> getFromDB = userDetailsRepository.findByUserID(userDetails.getUserID());

        assert getFromDB.equals(savedInDB);
    }

    RequestBuilder requestBuilder = new RequestBuilder();
    UserDetails userDetails = requestBuilder.getUserDetails();


    @Test
    void registerUserSuccess(){
        ResponseEntity<ResponseModel> response = databaseFacade.registerUser(userDetails);
        assert response.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    void registerUserFailureDueToDatabaseException(){
        when(userDetailsRepository.save(Mockito.any(UserDetails.class))).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void registerUserFailureDueToNullFirstName(){
        userDetails.setFirstName(null);
        when(userDetailsRepository.save(Mockito.any(UserDetails.class))).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void registerUserFailureDueToNullLastName(){
        userDetails.setLastName(null);
        when(userDetailsRepository.save(Mockito.any(UserDetails.class))).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void registerUserFailureDueToNullEmail(){
        userDetails.setEmailAddress(null);
        when(userDetailsRepository.save(Mockito.any(UserDetails.class))).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void registerUserFailureDueInvalidEmail(){
        userDetails.setEmailAddress("bethany");
        when(userDetailsRepository.save(Mockito.any(UserDetails.class))).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void registerUserFailureDueInvalidFirstName(){
        userDetails.setFirstName("");
        when(userDetailsRepository.save(Mockito.any(UserDetails.class))).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void getUserSuccess(){
        when(userDetailsRepository.findByUserID(Mockito.any(Integer.class)))
                .thenReturn(java.util.Optional.ofNullable(userDetails));
        assert databaseFacade.getUser(1).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    void getUserFailure(){
        when(userDetailsRepository.findByUserID(Mockito.any(Integer.class))).thenThrow(new RuntimeException());
        assert databaseFacade.getUser(1).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
