package com.tabwu.health.service;

import com.tabwu.health.entity.Order;
import com.tabwu.health.entity.Result;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Result addOrderOnline(Map map);

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
