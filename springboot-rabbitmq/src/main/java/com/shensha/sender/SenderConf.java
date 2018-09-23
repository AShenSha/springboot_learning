package com.shensha.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shensha on 2018/9/23.
 */
@Configuration
public class SenderConf {
    @Bean
    public Queue queue() {
        return new Queue("queue");
    }
}
