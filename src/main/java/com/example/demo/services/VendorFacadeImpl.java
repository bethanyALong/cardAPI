package com.example.demo.services;

import com.example.demo.models.ResponseModel;
import com.example.demo.models.Stores;
import com.example.demo.models.SwitchOffThread;
import com.example.demo.models.UserDetails;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class VendorFacadeImpl implements  VendorFacade{

    @Autowired
    DatabaseFacade databaseFacade;

    @Override
    public ResponseEntity<ResponseModel> switchVendor(Stores stores, Integer userID) {
        ResponseEntity<ResponseModel> response = changeVendorStatus(stores, userID);
        SwitchOffThread closureThread = new SwitchOffThread(userID);
        closureThread.run();
        return response;
    }

    public ResponseEntity<ResponseModel> changeVendorStatus(Stores stores, Integer userID){
        ResponseEntity<ResponseModel> response = databaseFacade.getUser(userID);
        if (response.getBody().details != null) {
            UserDetails user = response.getBody().details;
            user.setStores(stores);
            response = databaseFacade.registerUser(user);
        }
        return response;
    }

}
