package com.tabwu.health.service;

import com.tabwu.health.entity.Order;
import com.tabwu.health.entity.Result;

import java.util.Map;

public interface OrderService {

    Result addOrderOnline(Map map);

    Order findById(Integer orderId);
}
