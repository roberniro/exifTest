package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostConstruct
    public void init() throws Exception {
        PhotoReadService photoReadService = new PhotoReadService();
        photoReadService.readExifAndReturnGPS("src/main/resources/static/test.jpg");
    }

}
