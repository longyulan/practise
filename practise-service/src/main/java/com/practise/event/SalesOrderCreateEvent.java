package com.practise.event;

import org.springframework.context.ApplicationEvent;

/**
 * 销售订单完成事件
 *
 * @author longyulan
 * @date 2023/6/15
 */
public class SalesOrderCreateEvent<T> extends ApplicationEvent {

    public SalesOrderCreateEvent(T source) {
        super(source);
    }

}
