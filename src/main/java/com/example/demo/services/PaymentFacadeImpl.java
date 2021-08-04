package com.example.demo.services;

import com.example.demo.services.models.ErrorCodeEnum;
import com.example.demo.services.models.ResponseModel;
import com.example.demo.services.models.TransactionDetails;
import com.example.demo.services.models.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentFacadeImpl implements PaymentFacade{

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public ResponseEntity<ResponseModel> makePurchase(TransactionDetails transactionDetails) {
        ResponseModel responseModel = new ResponseModel();
        HttpStatus httpStatus = HttpStatus.ACCEPTED;
//        try {
//            UserDetails user = userDetailsRepository.findById(transactionDetails.ge()).orElse(null);
//            user.addTransaction(transactionDetails);
//            userDetailsRepository.save(user);
//            responseModel.errorCode = ErrorCodeEnum.SUCCESS_CREATION.errorCode;
//            responseModel.errorMessage = ErrorCodeEnum.SUCCESS_CREATION.errorMessage;
//            httpStatus = HttpStatus.OK;
//        } catch (Exception e){
//            responseModel.errorCode = ErrorCodeEnum.DATABASE_FAILURE.errorCode;
//            responseModel.errorMessage = ErrorCodeEnum.DATABASE_FAILURE.errorMessage.replace("x", e.getMessage());
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        }

        return new ResponseEntity<>(responseModel, httpStatus);
        // check balance payable > 0
        // get user database details
        // check balance
        // get new balance
        // updata database with new balance
        // return success or failure with new balance

    }
}
