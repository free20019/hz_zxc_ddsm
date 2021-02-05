package com.twkf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.twkf.dao")
public class HzZxcDdsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HzZxcDdsmApplication.class, args);
    }

}
