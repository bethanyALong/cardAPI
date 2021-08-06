package com.example.demo.services;

import builders.RequestBuilder;
import com.example.demo.models.ResponseModel;
import com.example.demo.models.Stores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
//@SpringBootTest
@Import(VendorFacadeImpl.class)
public class VendorFacadeTests {

    @Autowired
    VendorFacadeImpl vendorFacade;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    RequestBuilder requestBuilder = new RequestBuilder();
    Stores stores = requestBuilder.getStores();

    @org.junit.jupiter.api.Test
    public void test(){
        int c = 1;
        assert c == 1;
    }

    @Test
    void registerUserSuccess(){
        ResponseEntity<ResponseModel> response = vendorFacade.switchVendor(stores, 34);
        Stores responseStores = (Stores) response.getBody().details;
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert responseStores.getAsda();
    }

    @Test
    void registerUserFailureDueToUserNotFound(){
        ResponseEntity<ResponseModel> response = vendorFacade.switchVendor(stores, 34);
        assert response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
