package com.tabwu.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tabwu.health.entity.OrderSetting;
import com.tabwu.health.mapper.OrderSettingMapper;
import com.tabwu.health.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingMapper orderSettingMapper;

    @Override
    public void addOrderSetting(List<OrderSetting> excelList) {
        if (excelList != null && excelList.size() > 0) {
            for (OrderSetting orderSetting : excelList) {
                Long aLong = orderSettingMapper.checkOrderSettingByOrderDate(orderSetting.getOrderDate());

                if (aLong > 0) {
                    orderSettingMapper.updateOrderSetting(orderSetting);
                } else {
                    orderSettingMapper.addOrderSetting(orderSetting);
                }
            }
        }
    }
}
