package com.example.chapter1springbootquickstart.com.example.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloBookController {
    @RequestMapping("/book/sayHello")
    public String sayHello(){
        return "hello springboot";
    }
}
