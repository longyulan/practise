package com.practise.event.listener;

import com.practise.event.SalesOrderCreateEvent;
import com.practise.event.SalesOrderDeliveryEvent;
import com.practise.model.salesorder.event.SalesOrderCreateEventModel;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 销售单监听器
 * @author longyulan
 * @date 2023/6/15
 */
@Service
public class SalesOrderListener {

    /**
     * 只监听满足条件的事件
     * @param createEvent
     */
    @Async
    @TransactionalEventListener(value = SalesOrderCreateEvent.class, condition = "#root.args[0].order.id=='001'")
    public void salesOrderCreateEvent(SalesOrderCreateEvent createEvent) {
        SalesOrderCreateEventModel source = (SalesOrderCreateEventModel)createEvent.getSource();
        //扣减采购额度
        //..
    }

    /**
     * 记录日志(同时监听多个事件)
     * @param createEvent
     */
    @Async
    @TransactionalEventListener(classes = {SalesOrderCreateEvent.class, SalesOrderDeliveryEvent.class})
    public void logListener(SalesOrderCreateEvent createEvent) {
        SalesOrderCreateEventModel source = (SalesOrderCreateEventModel)createEvent.getSource();
        //记录日志
        //..
    }
}
