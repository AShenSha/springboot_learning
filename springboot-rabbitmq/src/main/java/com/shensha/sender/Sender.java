package com.shensha.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by shensha on 2018/9/23.
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        String context = "hello"+new Date();
        System.out.println("Sender:"+context);
        this.amqpTemplate.convertAndSend("hello",context);
    }
}
