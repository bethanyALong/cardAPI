package com.example.demo.services;

import builders.RequestBuilder;
import com.example.demo.models.ErrorCodeEnum;
import com.example.demo.models.ResponseModel;
import com.example.demo.models.Stores;
import com.example.demo.models.UserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class DataBaseFacadeTests {

    @InjectMocks
    @Autowired
    DatabaseFacadeImpl databaseFacade;

    @Mock
    UserDetailsRepository userDetailsRepository;

    RequestBuilder requestBuilder = new RequestBuilder();
    final UserDetails userDetails = requestBuilder.getUserDetails();
    Stores stores = requestBuilder.getStores();

    @Test
    void registerUserSuccess(){
        given(userDetailsRepository.save(userDetails)).willReturn(userDetails);
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    void registerUserFailureDueToDataBaseException(){
        given(userDetailsRepository.save(any())).willThrow(new RuntimeException("Database issue"));
        ResponseEntity<ResponseModel> response = databaseFacade.registerUser(userDetails);
        assert response.getStatusCode().equals(HttpStatus.OK);
    }


    @Test
    void registerUserFailureDueToNullFirstName(){
        userDetails.setFirstName(null);
        given(userDetailsRepository.save(any())).willThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.BAD_REQUEST);
    }

    @Test
    void registerUserFailureDueToNullLastName(){
        userDetails.setLastName(null);
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.BAD_REQUEST);
    }

    @Test
    void registerUserFailureDueToNullEmail(){
        userDetails.setEmailAddress(null);
        given(userDetailsRepository.save(any())).willThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.BAD_REQUEST);
    }

    @Test
    void registerUserFailureDueInvalidEmail(){
        userDetails.setEmailAddress("bethany");
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.BAD_REQUEST);
    }

    @Test
    void registerUserFailureDueInvalidFirstName(){
        userDetails.setFirstName("");
        when(userDetailsRepository.save(Mockito.any(UserDetails.class))).thenThrow(new RuntimeException("Database issue"));
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getUserSuccess(){
        given(userDetailsRepository.findByUserID(anyInt())).willReturn(Optional.of(userDetails));
        assert databaseFacade.getUser(1).getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    void getUserFailure(){
        when(userDetailsRepository.findByUserID(Mockito.any(Integer.class))).thenThrow(new RuntimeException());
        assert databaseFacade.getUser(1).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void registerSwitchUserSuccess(){
        ResponseEntity<ResponseModel> response = databaseFacade.switchVendor(stores, 1);
        given(userDetailsRepository.save(userDetails)).willReturn(userDetails);
        given(userDetailsRepository.findByUserID(anyInt())).willReturn(java.util.Optional.ofNullable(userDetails));
        Stores responseStores = (Stores) response.getBody().details;
        assert response.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    void registerUserFailureDueToUserNotFound(){
        ResponseModel responseModel = new ResponseModel();
        responseModel.responseCode = ErrorCodeEnum.SUCCESS_GET.errorCode;
        responseModel.responseMessage = ErrorCodeEnum.SUCCESS_GET.errorMessage;
        responseModel.details = userDetails;
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<ResponseModel> responseDB = new ResponseEntity<ResponseModel>(responseModel, httpStatus);
        given(userDetailsRepository.save(userDetails)).willReturn(userDetails);
        given(userDetailsRepository.findByUserID(anyInt())).willReturn(java.util.Optional.ofNullable(userDetails));
        ResponseEntity<ResponseModel> response = databaseFacade.switchVendor(stores, 1);
        assert response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
