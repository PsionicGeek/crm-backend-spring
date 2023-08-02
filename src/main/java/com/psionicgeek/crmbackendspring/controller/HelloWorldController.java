package com.psionicgeek.crmbackendspring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("api/v1")
    String helloWorld(){
        return "Hello World";
    }

}
