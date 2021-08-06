package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test {

    @org.junit.jupiter.api.Test
    public void test(){
        int c = 1;
        assert c == 1;
    }
}
