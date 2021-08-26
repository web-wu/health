package com.tabwu.health.service;

import com.tabwu.health.entity.OrderSetting;

import java.util.List;

public interface OrderSettingService {
    void addOrderSetting(List<OrderSetting> excelList);
}
