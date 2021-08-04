package com.example.demo.services;

import com.example.demo.services.models.ResponseModel;
import com.example.demo.services.models.TransactionDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionFacadeImpl implements TransactionFacade{

    @Override
    public ResponseEntity<ResponseModel> makePurchase(TransactionDetails transactionDetails) {
        return null;
    }
}
