package com.practise.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 销售事件生产者
 *
 * @author longyulan
 * @date 2023/6/15
 */
@Service
public class SalesOrderEventProducerImpl implements SalesOrderEventProducer {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void salesOrderProducer(SalesOrderCreateEvent createEvent) {
        applicationEventPublisher.publishEvent(createEvent);
    }
}
