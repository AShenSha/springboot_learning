package com.shensha.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by shensha on 2018/9/23.
 * 通过@RabbitListener注解定义hello列队的监听，并用@RabbitHandler指定消息的处理方法
 */
@Component
//@RabbitListener
public class Receiver {

    @RabbitListener(queues="queue")
    public void process(String hello){
        System.out.println("Receiver:"+hello);
    }
}
