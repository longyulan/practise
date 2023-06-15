package com.practise.service.impl;

import com.practise.event.SalesOrderCreateEvent;
import com.practise.event.SalesOrderEventProducer;
import com.practise.model.salesorder.SalesOrderCreateReq;
import com.practise.model.salesorder.event.SalesOrderCreateEventModel;
import com.practise.service.SalesOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 销售单Service
 * @author longyulan
 * @date 2023/6/15
 */
@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    @Resource
    private SalesOrderEventProducer salesOrderEventProducer;

    @Override
    public String create(SalesOrderCreateReq req) {
        //业务逻辑省略
        //...

        //发送销售订单创建事件
        SalesOrderCreateEventModel eventModel = new SalesOrderCreateEventModel();
        salesOrderEventProducer.salesOrderProducer(new SalesOrderCreateEvent(eventModel));
        return null;
    }
}
