package com.example.demo.config;

import com.example.demo.services.DatabaseFacade;
import com.example.demo.services.DatabaseFacadeImpl;
import com.example.demo.services.VendorFacade;
import com.example.demo.services.VendorFacadeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class CardApiConfiguration {

    @Bean
    public DatabaseFacade databaseFacade() {
        return new DatabaseFacadeImpl();
    }

    @Bean
    public VendorFacade vendorFacade(){
        return new VendorFacadeImpl();
    }

}
