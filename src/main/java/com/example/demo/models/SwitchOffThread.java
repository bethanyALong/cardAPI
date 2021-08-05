package com.example.demo.models;

import com.example.demo.services.VendorFacade;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

public class SwitchOffThread implements Runnable{

    @Autowired
    VendorFacade vendorFacade;

    Integer userID;

    public SwitchOffThread(Integer userID){
        this.userID = userID;
    }

    @SneakyThrows
    @Override
    public void run() {
        Thread.sleep(10);
        vendorFacade.changeVendorStatus(new Stores(), userID);
    }
    }
