package com.practise.event;

/**
 * 事件生产者
 * @author longyulan
 * @date 2023/6/15
 */
public interface SalesOrderEventProducer {
    /**
     * 销售单创建生产者
     * @param createEvent
     */
    void salesOrderProducer(SalesOrderCreateEvent createEvent);
}
