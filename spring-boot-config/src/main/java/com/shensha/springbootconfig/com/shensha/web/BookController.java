package com.shensha.springbootconfig.com.shensha.web;

import com.shensha.springbootconfig.com.shensha.entity.BookComponent;
import com.shensha.springbootconfig.com.shensha.entity.BookProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    BookProperties bookProperties;

    @Autowired
    BookComponent bookComponent;

    @RequestMapping("/sayHello")
    public String sayHello(){
        return "hello:"+bookComponent.getName()+""+bookComponent.getWriter();
    }
}
