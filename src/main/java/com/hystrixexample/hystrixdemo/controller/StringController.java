package com.hystrixexample.hystrixdemo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StringController {

    @HystrixCommand(fallbackMethod = "getString_fallback")
    @GetMapping("/string")
    public ResponseEntity<String> getString(){
        return new RestTemplate().getForEntity("http://localhost:6543/string", String.class);
    }

    public ResponseEntity<String> getString_fallback(){
        return new ResponseEntity<>("fallback execution", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
