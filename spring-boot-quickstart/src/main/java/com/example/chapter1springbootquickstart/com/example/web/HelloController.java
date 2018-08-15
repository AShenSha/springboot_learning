package com.example.chapter1springbootquickstart.com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @RequestMapping(value = "sayHello",method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(){
        return "hello world";
    }
}
