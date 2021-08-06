package com.example.demo.services;

import com.example.demo.models.ErrorCodeEnum;
import com.example.demo.models.ResponseModel;
import com.example.demo.models.Stores;
import com.example.demo.models.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.Objects;

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

    @Override
    public ResponseEntity<ResponseModel> getUser(Integer id) {
        ResponseModel responseModel = new ResponseModel();
        HttpStatus httpStatus;
        try {
            UserDetails userDetails = userDetailsRepository.findByUserID(id).orElse(null);
            if (userDetails == null){
                responseModel.responseCode = ErrorCodeEnum.USER_NOT_FOUND.errorCode;
                responseModel.responseMessage = ErrorCodeEnum.USER_NOT_FOUND.errorMessage;
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            } else {
                responseModel.details = userDetails;
                responseModel.responseCode = ErrorCodeEnum.SUCCESS_GET.errorCode;
                responseModel.responseMessage = ErrorCodeEnum.SUCCESS_GET.errorMessage;
                httpStatus = HttpStatus.OK;
            }
        }catch (Exception e){
            responseModel.responseCode = ErrorCodeEnum.DATABASE_FAILURE.errorCode;
            responseModel.responseMessage = ErrorCodeEnum.DATABASE_FAILURE.errorMessage.replace("x", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(responseModel, httpStatus);
    }

    public String validationValues(String message){
        String[] inputString = message.split(",");
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

    @Override
    public ResponseEntity<ResponseModel> switchVendor(Stores stores, Integer userID) {
        ResponseEntity<ResponseModel> response = getUser(userID);
        if (Objects.requireNonNull(response.getBody()).details != null) {
            UserDetails user = (UserDetails) response.getBody().details;
            user.setStores(stores);
            response = registerUser(user);
            Stores updatedStores = ((UserDetails) response.getBody().details).getStores();
            response.getBody().details = updatedStores;
        }
        return response;
    }

}
