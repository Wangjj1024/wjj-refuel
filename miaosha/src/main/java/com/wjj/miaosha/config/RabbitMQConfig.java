package com.wjj.miaosha.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description: RabbitMQ配置类
 * @Author: wjj
 * @CreateTime: 2024-01-08
 * @Version: 1.0
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("queue", true);
    }
}
