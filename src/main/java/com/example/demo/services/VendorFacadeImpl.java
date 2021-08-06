package com.example.demo.services;

import com.example.demo.models.ResponseModel;
import com.example.demo.models.Stores;
import com.example.demo.models.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VendorFacadeImpl implements  VendorFacade{

    @Autowired
    DatabaseFacade databaseFacade;

    @Override
    public ResponseEntity<ResponseModel> switchVendor(Stores stores, Integer userID) {
        ResponseEntity<ResponseModel> response = databaseFacade.getUser(userID);
        if (Objects.requireNonNull(response.getBody()).details != null) {
            UserDetails user = (UserDetails) response.getBody().details;
            user.setStores(stores);
            response = databaseFacade.registerUser(user);
            Stores updatedStores = ((UserDetails) response.getBody().details).getStores();
            response.getBody().details = updatedStores;
        }
        return response;
    }

}
