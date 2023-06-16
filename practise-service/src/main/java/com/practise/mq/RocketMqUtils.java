package com.practise.mq;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;

/**
 * 消息工具类
 * @author longyulan
 * @date 2023/6/16
 */
@Component
public class RocketMqUtils {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 事务完成后再异步发送消息
     * @param topic
     * @param content
     */
    public void transactionalSend(String topic, String content) {
        boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();
        if(actualTransactionActive) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    //异步发送消息
                    asyncSend(topic, content);
                }
            });
        } else {
            asyncSend(topic, content);
        }
    }

    /**
     * 异步发送消息
     * @param topic
     * @param content
     */
    public void asyncSend(String topic, String content) {
        Message<String> message = MessageBuilder.withPayload(content).build();
        //异步发送消息
        rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                //消息发送成功，修改数据库中mq-log状态为成功
            }

            @Override
            public void onException(Throwable throwable) {
                //消息发送成功，修改数据库中mq-log状态为失败
            }
        });
    }

    /**
     * 异步延迟发送消息
     * @param topic 主题
     * @param content 消息内容
     * @param timeout 超时时间
     * @param delayLevel 延时消息等级分为18个：1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     */
    public void asyncDelaySend(String topic, String content, Long timeout, Integer delayLevel) {
        Message<String> message = MessageBuilder.withPayload(content).build();
        //异步发送消息
        rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                //消息发送成功，修改数据库中mq-log状态为成功
            }

            @Override
            public void onException(Throwable throwable) {
                //消息发送成功，修改数据库中mq-log状态为失败
            }
        }, timeout, delayLevel);
    }


}
