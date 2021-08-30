package com.tabwu.health.mapper;

import com.tabwu.health.entity.OrderSetting;

public interface OrderSettingMapper {

    void addOrderSetting(OrderSetting orderSetting);

    Long checkOrderSettingByOrderDate(String orderDate);

    void updateOrderSetting(OrderSetting orderSetting);

    OrderSetting checkOrderSettingNumberByOrderData(String orderDate);
}
