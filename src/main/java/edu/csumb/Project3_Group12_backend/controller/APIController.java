package edu.csumb.Project3_Group12_backend.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {
    @GetMapping("/")
    public String test(){
        return "It works!";
    }
}
