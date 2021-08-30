package com.tabwu.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tabwu.health.entity.Member;
import com.tabwu.health.entity.Order;
import com.tabwu.health.entity.OrderSetting;
import com.tabwu.health.entity.Result;
import com.tabwu.health.mapper.MemberMapper;
import com.tabwu.health.mapper.OrderMapper;
import com.tabwu.health.mapper.OrderSettingMapper;
import com.tabwu.health.service.OrderService;
import com.tabwu.health.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MemberMapper memberMapper;

    private Result result;

    @Override
    public Result addOrderOnline(Map map) {
        String orderDate = (String) map.get("orderDate");

        /*try {
            Date date = DateUtils.parseString2Date(orderDate);
        } catch (Exception e) {
          e.printStackTrace();
          result.setData(false);
        }*/

        String telephone = (String) map.get("telephone");

        OrderSetting orderSetting = orderSettingMapper.checkOrderSettingNumberByOrderData(orderDate);
        //检查预约设置是否设置了，可预约数是否 小于 预约数
        if (orderSetting == null || orderSetting.getReservations() >= orderSetting.getNumber()) {
            result.setData(0);
        }

        //根据电话号 检查 该用户是否为 会员，不是则注册
        Member member = memberMapper.getMemberByPhone(telephone);
        if (member == null) {
            member.setName((String) map.get("name"));
            member.setFileNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.getRegTime(new Date());
            memberMapper.addMember(member);
        }


        Order order = new Order();
        order.setMemberId(member.getId());
        order.setOrderDate(new Date());
        order.setSetmealId( Integer.parseInt((String) map.get("setmealId")));

        //根据 用户id，套餐id，时间来检测 是否重复 预约
        List<Order> orderList = orderMapper.findOrderByCondition(order);

        if (orderList != null || orderList.size() > 0) {
            result.setData(0);
        }

        Long aLong = orderMapper.addOrderOnline(order);
        if (aLong > 0) {
            result.setData(order.getId());
        }
        return result;
    }

    @Override
    public Order findById(Integer orderId) {
        return orderMapper.findById(orderId);
    }


}
