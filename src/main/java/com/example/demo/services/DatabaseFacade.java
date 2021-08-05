package com.example.demo.services;

import com.example.demo.services.models.ResponseModel;
import com.example.demo.services.models.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseFacade {

    ResponseEntity<ResponseModel> registerUser(UserDetails userDetails);

}
