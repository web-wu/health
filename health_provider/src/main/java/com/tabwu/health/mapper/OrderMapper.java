package com.tabwu.health.mapper;

import com.tabwu.health.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    Long addOrderOnline(Order order);

    List<Order> findOrderByCondition(Order order);

    Order findById(Integer orderId);
}
