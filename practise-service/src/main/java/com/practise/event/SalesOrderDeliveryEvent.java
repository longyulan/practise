package com.practise.event;

import org.springframework.context.ApplicationEvent;

/**
 * 销售订单完成事件
 *
 * @author longyulan
 * @date 2023/6/15
 */
public class SalesOrderDeliveryEvent<T> extends ApplicationEvent {

    public SalesOrderDeliveryEvent(T source) {
        super(source);
    }

}
