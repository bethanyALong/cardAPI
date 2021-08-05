package com.example.demo.services;

import com.example.demo.services.models.ErrorCodeEnum;
import com.example.demo.services.models.ResponseModel;
import com.example.demo.services.models.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.Arrays;
import java.util.List;

@Service
public class DatabaseFacadeImpl implements DatabaseFacade{

    @Autowired
    UserDetailsRepository userDetailsRepository;


    @Override
    public ResponseEntity<ResponseModel> registerUser(UserDetails userDetails) {
        ResponseModel responseModel = new ResponseModel();
        HttpStatus httpStatus;
        try {
            responseModel.details = userDetailsRepository.save(userDetails);
            responseModel.responseCode = ErrorCodeEnum.SUCCESS_CREATION.errorCode;
            responseModel.responseMessage = ErrorCodeEnum.SUCCESS_CREATION.errorMessage;
            httpStatus = HttpStatus.OK;
        } catch (TransactionSystemException e){
            responseModel.responseCode = ErrorCodeEnum.VALIDATION_FAILURE.errorCode;
            String message = e.getCause().getCause().getMessage();
            String validationFailures = validationValues(message);
            responseModel.responseMessage = ErrorCodeEnum.VALIDATION_FAILURE.errorMessage.replace("x", validationFailures);
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e){
            responseModel.responseCode = ErrorCodeEnum.DATABASE_FAILURE.errorCode;
            responseModel.responseMessage = ErrorCodeEnum.DATABASE_FAILURE.errorMessage.replace("x", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(responseModel, httpStatus);
    }

    public String validationValues(String message){
        List<String> inputString = Arrays.asList(message.split(","));
        String word = "propertyPath=";
        StringBuilder response = new StringBuilder("the following parameters: ");
        for (String s : inputString){
            if (s.contains(word)){
                String s1 = s.replace(word, "");
                response.append(s1).append(",");
            }
        }
        response.replace(response.length()-1, response.length(), ".");
        return response.toString();
    }

}
