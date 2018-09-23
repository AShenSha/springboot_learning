package com.shensha.springbootaophello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(@RequestParam String name){
        return "Hello"+name;
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "goHello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
