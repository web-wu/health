package com.tabwu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tabwu.health.constant.MessageConstant;
import com.tabwu.health.entity.Order;
import com.tabwu.health.entity.Result;
import com.tabwu.health.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;

    @Autowired
    private JedisPool jedisPool;

    private Result result;

    @RequestMapping("/addOrderOnline")
    public Result addOrderOnline(@RequestBody Map map) {

        String telephone = (String) map.get("telephone");

        String validateCode = jedisPool.getResource().get(telephone + MessageConstant.SENDTYPE_ORDER);

        if (validateCode == null || !validateCode.equals(map.get("validateCode"))) {
            result.setFlag(false);
            result.setMessage(MessageConstant.ORDER_FULL);
        }

        map.put("orderType",Order.ORDERTYPE_WEIXIN);

        try {
            Result result1 = orderService.addOrderOnline(map);
            Integer orderId = (Integer) result1.getData();
            if (orderId > 0) {
                result.setFlag(true);
                result.setMessage(MessageConstant.ORDER_SUCCESS);
                result.setData(orderId);
            } else {
                result.setFlag(false);
                result.setMessage(MessageConstant.ORDER_FULL);
            }
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.ORDER_FULL);
        }

        return result;
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam("orderId") Integer orderId) {
        try {
            Order order = orderService.findById(orderId);
            if (order == null) {
                result.setFlag(false);
                result.setMessage(MessageConstant.QUERY_ORDER_FAIL);
            } else  {
                result.setFlag(true);
                result.setMessage(MessageConstant.QUERY_ORDER_SUCCESS);
                result.setData(order);
            }
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.QUERY_ORDER_FAIL);
        }

        return result;
    }
}
