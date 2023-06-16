package com.practise.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * 消息消费
 * @author longyulan
 * @date 2023/6/16
 */
@RocketMQMessageListener(topic = "${salesOrder.create.topic}", consumerGroup = "${salesOrder.create.consumer-group}")
public class SalesOrderConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        //相关业务操作
    }
}
