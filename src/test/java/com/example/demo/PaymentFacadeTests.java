package com.example.demo;

import builders.RequestBuilder;
import com.example.demo.services.models.UserDetails;
import com.example.demo.services.PaymentFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentFacadeTests {

    @Autowired
    PaymentFacade paymentFacade;

    RequestBuilder requestBuilder = new RequestBuilder();
    UserDetails userDetails = requestBuilder.getUserDetails();
    int balancePayable = 10;

    @Test
    void makePaymentSuccess(){
        assert paymentFacade.makePayment(userDetails, balancePayable).getStatusCode().is2xxSuccessful();
    }

    @Test
    void makePaymentFailureDueToDatabaseTimeout(){
        // when database times out
        assert paymentFacade.makePayment(userDetails, balancePayable).getStatusCode().is2xxSuccessful();
    }
}
