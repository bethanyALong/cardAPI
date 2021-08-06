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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @MockBean
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
        assert databaseFacade.registerUser(userDetails).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
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
        when(userDetailsRepository.findByUserID(anyInt())).thenReturn(java.util.Optional.ofNullable(userDetails));
        ResponseEntity<ResponseModel> response = databaseFacade.switchVendor(stores, 1);
        assert response.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    void registerUserFailureDueToUserNotFound(){
        ResponseModel responseModel = new ResponseModel();
        responseModel.responseCode = ErrorCodeEnum.SUCCESS_GET.responseCode;
        responseModel.responseMessage = ErrorCodeEnum.SUCCESS_GET.responseMessage;
        responseModel.details = userDetails;
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<ResponseModel> responseDB = new ResponseEntity<ResponseModel>(responseModel, httpStatus);
        when(userDetailsRepository.save(Mockito.any(UserDetails.class))).thenReturn(userDetails);
        when(userDetailsRepository.findByUserID(anyInt())).thenReturn(null);
        assert databaseFacade.switchVendor(stores, 1).getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void testValidationMapper(){
        String message = "Validation failed for classes [com.example.demo.models.UserDetails] during persist time for groups [javax.validation.groups.Default, ]\n" +
                "List of constraint violations:[\n" +
                "\tConstraintViolationImpl{interpolatedMessage='must match \"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])\"', propertyPath=emailAddress, rootBeanClass=class com.example.demo.models.UserDetails, messageTemplate='{javax.validation.constraints.Pattern.message}'}\n" +
                "]";
        assert databaseFacade.validationValues(message).equals("the following parameter(s):  emailAddress.");
    }

}
