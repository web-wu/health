package com.tabwu.health.mapper;

import com.tabwu.health.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    Long addOrderOnline(Order order);

    List<Order> findOrderByCondition(Order order);

    Order findById(Integer orderId);

    Integer getTodayOrder(String today);

    Integer getAllOrder();

    Integer getBeforeThisWeekAllOrder(String thisWeekMonday);

    Integer getBeforeThisMonthAllOrder(String firstDay4ThisMonth);

    Integer getTodayNumberTrue(String today);

    Integer getWeekNumberTrue(String thisWeekMonday);

    Integer getMonthNumberTrue(String firstDay4ThisMonth);

    List<Map> getHotSetmeal();
}
