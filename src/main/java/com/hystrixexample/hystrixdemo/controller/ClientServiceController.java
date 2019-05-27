package com.hystrixexample.hystrixdemo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientServiceController {

    @HystrixCommand(fallbackMethod = "getIntegers_fallback")
    @GetMapping("/numbers")
    public ResponseEntity<Integer[]> getIntegers(){
        ResponseEntity<Integer[]> responseEntity = new RestTemplate().getForEntity("http://localhost:6543/numbers", Integer[].class);
        return responseEntity;
    }

    ResponseEntity<Integer[]> getIntegers_fallback(){
        Integer[] l = new Integer[1];
        l[0] = -1;
        return new ResponseEntity<>(l, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
