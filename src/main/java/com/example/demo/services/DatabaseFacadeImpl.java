package com.example.demo.services;

import com.example.demo.services.models.ErrorCodeEnum;
import com.example.demo.services.models.ResponseModel;
import com.example.demo.services.models.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class DatabaseFacadeImpl implements DatabaseFacade{

    @Autowired
    UserDetailsRepository userDetailsRepository;

    ResponseModel responseModel = new ResponseModel();

    HttpStatus httpStatus;

    @Override
    public ResponseEntity<ResponseModel> registerUser(UserDetails userDetails) {

        try {
            responseModel.userDetails = userDetailsRepository.save(userDetails);
            responseModel.errorCode = ErrorCodeEnum.SUCCESS_CREATION.errorCode;
            responseModel.errorMessage = ErrorCodeEnum.SUCCESS_CREATION.errorMessage;
            httpStatus = HttpStatus.OK;

        } catch (Exception e){
            responseModel.errorCode = ErrorCodeEnum.DATABASE_FAILURE.errorCode;
            responseModel.errorMessage = ErrorCodeEnum.DATABASE_FAILURE.errorMessage.replace("x", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(responseModel, httpStatus);

    }

    @Override
    public ResponseEntity<ResponseModel> deleteUser(UserDetails userDetails){
        try {
            UserDetails existingUser = userDetailsRepository.findByUserID(userDetails.getUserID()).orElse(null);
            userDetailsRepository.delete(existingUser);
            responseModel.errorCode = ErrorCodeEnum.SUCCESS_DELETION.errorCode;
            responseModel.errorMessage = ErrorCodeEnum.SUCCESS_DELETION.errorMessage;
            httpStatus = HttpStatus.OK;
        } catch (Exception e){
            responseModel.errorCode = ErrorCodeEnum.DATABASE_FAILURE.errorCode;
            responseModel.errorMessage = ErrorCodeEnum.DATABASE_FAILURE.errorMessage.replace("x", e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(responseModel, httpStatus);
    }

}
