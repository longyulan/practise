package com.practise.service;

import com.practise.model.salesorder.SalesOrderCreateReq;

/**
 * @author longyulan
 * @date 2023/6/15
 */
public interface SalesOrderService {
    String create(SalesOrderCreateReq req);
}
