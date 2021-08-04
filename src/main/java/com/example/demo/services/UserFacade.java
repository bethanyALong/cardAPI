package com.example.demo.services;

import com.example.demo.services.models.ResponseModel;
import com.example.demo.services.models.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFacade {

    ResponseEntity<ResponseModel> registerUser(UserDetails userDetails);

    ResponseEntity<ResponseModel> deleteUser(UserDetails userDetails);
}
