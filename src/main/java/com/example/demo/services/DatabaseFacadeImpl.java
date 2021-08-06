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

import static com.example.demo.models.Constants.*;

@Service
public class DatabaseFacadeImpl implements DatabaseFacade {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public ResponseEntity<ResponseModel> registerUser(UserDetails userDetails) {
        ResponseModel responseModel = new ResponseModel();
        HttpStatus httpStatus;
        try {
            responseModel.details = userDetailsRepository.save(userDetails);
            responseModel.responseCode = ErrorCodeEnum.SUCCESS_CREATION.responseCode;
            responseModel.responseMessage = ErrorCodeEnum.SUCCESS_CREATION.responseMessage;
            httpStatus = HttpStatus.OK;
        } catch (TransactionSystemException e) {
            responseModel.responseCode = ErrorCodeEnum.VALIDATION_FAILURE.responseCode;
            String message = e.getCause().getCause().getMessage();
            String validationFailures = validationValues(message);
            responseModel.responseMessage = ErrorCodeEnum.VALIDATION_FAILURE.responseMessage.replace("%x", validationFailures);
            httpStatus = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            responseModel.responseCode = ErrorCodeEnum.DATABASE_FAILURE.responseCode;
            responseModel.responseMessage = ErrorCodeEnum.DATABASE_FAILURE.responseMessage;
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
            if (userDetails == null) {
                responseModel.responseCode = ErrorCodeEnum.USER_NOT_FOUND.responseCode;
                responseModel.responseMessage = ErrorCodeEnum.USER_NOT_FOUND.responseMessage;
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            } else {
                responseModel.details = userDetails;
                responseModel.responseCode = ErrorCodeEnum.SUCCESS_GET.responseCode;
                responseModel.responseMessage = ErrorCodeEnum.SUCCESS_GET.responseMessage;
                httpStatus = HttpStatus.OK;
            }
        } catch (Exception e) {
            responseModel.responseCode = ErrorCodeEnum.DATABASE_FAILURE.responseCode;
            responseModel.responseMessage = ErrorCodeEnum.DATABASE_FAILURE.responseMessage;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(responseModel, httpStatus);
    }

    @Override
    public ResponseEntity<ResponseModel> switchVendor(Stores stores, Integer userID) {
        ResponseEntity<ResponseModel> response = getUser(userID);
        if (Objects.requireNonNull(response.getBody()).details != null) {
            ResponseModel responseModel = new ResponseModel();
            responseModel.responseCode = ErrorCodeEnum.SUCCESS_UPDATE.responseCode;
            responseModel.responseMessage = ErrorCodeEnum.SUCCESS_UPDATE.responseMessage;
            UserDetails user = (UserDetails) response.getBody().details;
            user.setStores(stores);
            ResponseEntity<ResponseModel> responseUpdated = registerUser(user);
            if (responseUpdated.getBody().details != null) {
                Stores updatedStores = ((UserDetails) responseUpdated.getBody().details).getStores();
                responseModel.details = updatedStores;
                return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
            } else {
                return responseUpdated;
            }
        }
        return response;
    }

    public String validationValues(String message) {
        String[] inputString = message.split(COMMA);
        String word = PROPERTY_PATH;
        StringBuilder response = new StringBuilder(PARAMETER_LIST);
        for (String s : inputString) {
            if (s.contains(word)) {
                String s1 = s.replace(word, DEAD_SPACE);
                response.append(s1).append(COMMA);
            }
        }
        response.replace(response.length() - 1, response.length(), FULL_STOP);
        return response.toString();
    }
}
