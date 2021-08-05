package com.example.demo.api;

import com.example.demo.exceptions.InvalidAuthorisationException;
import com.example.demo.services.PaymentFacade;
import com.example.demo.services.models.UserDetails;
import com.example.demo.services.UserFacade;
import com.example.demo.services.models.ErrorCodeEnum;
import com.example.demo.services.models.ResponseModel;
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
        validateAuth(xAuthToken);
        ResponseEntity<ResponseModel> response = userFacade.registerUser(userDetails);
        return response;
    }

    @PostMapping("/vendor-switch")
    public ResponseEntity<ResponseModel> vendorSwitch(@RequestHeader(value = "x-auth-token", required = true)  String xAuthToken, @RequestBody(required = true) UserDetails userDetails){
        validateAuth(xAuthToken);
        ResponseEntity<ResponseModel> response = userFacade.registerUser(userDetails);
        return response;
    }




//    @PostMapping("/make-transaction")
//    public ResponseEntity<ResponseModel> makeTransaction(@RequestHeader(value = "x-auth-token", required = true)  String xAuthToken, @RequestBody(required = true) ){
//        ResponseEntity<ResponseModel> response;
//        response = validateAuth(xAuthToken);
//        if (response == null) {
//            response = paymentFacade.makePurchase(transactionDetails);
//        }
//        return response;
//    }



    public void validateAuth(String xAuthToken){
        if (xAuthToken == null
                || xAuthToken.isEmpty()
                || !AuthToken.equals(xAuthToken)) {
            throw new InvalidAuthorisationException();
        }

    }

    @ExceptionHandler(InvalidAuthorisationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ResponseModel> handleNoSuchElementFoundException(
            InvalidAuthorisationException exception
    ) {
        ResponseModel errorModel = new ResponseModel();
        errorModel.responseCode = ErrorCodeEnum.FOBIDDEN_REQUEST.errorCode;
        errorModel.responseMessage = ErrorCodeEnum.FOBIDDEN_REQUEST.errorMessage;
        return new ResponseEntity<>(errorModel, HttpStatus.FORBIDDEN);
    }


}
