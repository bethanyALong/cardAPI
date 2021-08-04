package com.example.demo.api;

import com.example.demo.services.PaymentFacade;
import com.example.demo.services.models.UserDetails;
import com.example.demo.services.UserFacade;
import com.example.demo.services.models.ErrorCodeEnum;
import com.example.demo.services.models.ResponseModel;
import com.example.demo.services.models.TransactionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RestController
public class CardApiApplicationController implements WebMvcConfigurer {

    @Value("${xAuthToken}")
    private String AuthToken;

    @Autowired
    PaymentFacade paymentFacade;

    @Autowired
    UserFacade userFacade;


    @PostMapping("/register-user")
    public ResponseEntity<ResponseModel> registerUser(@RequestHeader(value = "x-auth-token", required = true)  String xAuthToken, @RequestBody(required = true) UserDetails userDetails){
        ResponseEntity<ResponseModel> response;
        response = validateAuth(xAuthToken);
        if (response == null){
            response = userFacade.registerUser(userDetails);}
        return response;
    }

    @PostMapping("/delete-user")
    public ResponseEntity<ResponseModel> deleteUser(@RequestHeader(value = "x-auth-token", required = true)  String xAuthToken, @RequestBody(required = true) UserDetails userDetails){
        ResponseEntity<ResponseModel> response;
        response = validateAuth(xAuthToken);
        if (response == null) {
            response = userFacade.deleteUser(userDetails);
        }
        return response;
    }

    @PostMapping("/make-transaction")
    public ResponseEntity<ResponseModel> makeTransaction(@RequestHeader(value = "x-auth-token", required = true)  String xAuthToken, @RequestBody(required = true) TransactionDetails transactionDetails){
        ResponseEntity<ResponseModel> response;
        response = validateAuth(xAuthToken);
        if (response == null) {
            response = paymentFacade.makePurchase(transactionDetails);
        }
        return response;
    }



    public ResponseEntity<ResponseModel> validateAuth(String xAuthToken){
        // Validate x-auth-token if it's provided
        if (xAuthToken != null
                && !xAuthToken.isEmpty()
                && AuthToken.equals(xAuthToken)) {
            return null;
        }  else {
            ResponseModel errorModel = new ResponseModel();
            errorModel.errorCode = ErrorCodeEnum.FOBIDDEN_REQUEST.errorCode;
            errorModel.errorMessage = ErrorCodeEnum.FOBIDDEN_REQUEST.errorMessage;
            return new ResponseEntity<>(errorModel, HttpStatus.FORBIDDEN);
        }

    }


}
