package com.example.demo.services;

import com.example.demo.models.ResponseModel;
import com.example.demo.models.Stores;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorFacade {

    ResponseEntity<ResponseModel> switchVendor(Stores stores, Integer userID);

}
