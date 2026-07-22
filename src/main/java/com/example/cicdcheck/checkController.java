package com.example.cicdcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class checkController {
    @GetMapping("/ping")
    public String checkApi(){
        return "pong v3";
    }
    @GetMapping("/charu")
    public String checkApi1(){
        return "helloo BEBA!!!!!\uD83E\uDEE0 ";
    }
}
