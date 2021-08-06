package com.example.demo.api;

import com.example.demo.exceptions.InvalidAuthorisationException;
import com.example.demo.models.ErrorCodeEnum;
import com.example.demo.models.ResponseModel;
import com.example.demo.models.Stores;
import com.example.demo.models.UserDetails;
import com.example.demo.services.DatabaseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RestController
public class UserApiApplicationController implements WebMvcConfigurer {

    @Value("${xAuthToken}")
    private String AuthToken;

    @Autowired
    DatabaseFacade databaseFacade;

    @PostMapping("/register-user")
    public ResponseEntity<ResponseModel> registerUser(@RequestHeader(value = "x-auth-token", required = true) String xAuthToken, @RequestBody(required = true) UserDetails userDetails) {
        validateAuth(xAuthToken);
        return databaseFacade.registerUser(userDetails);
    }

    @PostMapping("/vendor-switch")
    public ResponseEntity<ResponseModel> vendorSwitch(@RequestHeader(value = "x-auth-token", required = true) String xAuthToken, @RequestHeader(value = "userID", required = true) Integer userID, @RequestBody(required = true) Stores stores) {
        validateAuth(xAuthToken);
        return databaseFacade.switchVendor(stores, userID);
    }

    public void validateAuth(String xAuthToken) {
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
        errorModel.responseCode = ErrorCodeEnum.FOBIDDEN_REQUEST.responseCode;
        errorModel.responseMessage = ErrorCodeEnum.FOBIDDEN_REQUEST.responseMessage;
        return new ResponseEntity<>(errorModel, HttpStatus.FORBIDDEN);
    }

}
