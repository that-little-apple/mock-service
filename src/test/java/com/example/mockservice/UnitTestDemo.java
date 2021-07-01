package com.example.mockservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

import static java.lang.System.out;

@SpringBootTest
public class UnitTestDemo {
    @Autowired
    private DataSource dataSource;
    @Test
    void test(){
        out.println("hello world!! datasource is "+dataSource);
    }
}