package com.example.demo.services;

import com.example.demo.models.ResponseModel;
import com.example.demo.models.Stores;
import com.example.demo.models.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseFacade {

    ResponseEntity<ResponseModel> registerUser(UserDetails userDetails);

    ResponseEntity<ResponseModel> getUser(Integer id);

    ResponseEntity<ResponseModel> switchVendor(Stores stores, Integer userID);

}
