package com.practise.controller;

import com.practise.model.ApiResult;
import com.practise.model.salesorder.SalesOrderCreateReq;
import com.practise.service.SalesOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单类
 * @author longyulan
 * @date 2023/6/15
 */
@RestController
@RequestMapping("/salesOrder")
public class SalesOrderController {

    @Resource
    private SalesOrderService salesOrderService;

    @PostMapping("/create")
    public ApiResult<String> create(@RequestBody SalesOrderCreateReq req) {
        String salesOrderCode = salesOrderService.create(req);
        return ApiResult.ok(salesOrderCode);
    }
}
