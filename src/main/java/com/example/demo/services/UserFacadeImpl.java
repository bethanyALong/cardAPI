package com.example.demo.services;

import com.example.demo.services.models.ResponseModel;
import com.example.demo.services.models.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    DatabaseFacade databaseFacade;

    @Override
    public ResponseEntity<ResponseModel> registerUser(UserDetails userDetails) {
        return databaseFacade.registerUser(userDetails);
    }

    @Override
    public ResponseEntity<ResponseModel> deleteUser(UserDetails userDetails) {
        return databaseFacade.deleteUser(userDetails);

    }
}
