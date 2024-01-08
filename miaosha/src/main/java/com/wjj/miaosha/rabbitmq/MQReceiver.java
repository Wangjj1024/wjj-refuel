package com.wjj.miaosha.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Description: 消息消费者
 * @Author: wjj
 * @CreateTime: 2024-01-08
 * @Version: 1.0
 */
@Service
@Slf4j
public class MQReceiver {
    @RabbitListener(queues = "queue")
    public void receive(Object msg) {
        log.info("接受消息：" + msg);
    }

}
