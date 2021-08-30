package com.tabwu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tabwu.health.constant.MessageConstant;
import com.tabwu.health.entity.Result;
import com.tabwu.health.entity.Setmeal;
import com.tabwu.health.service.SetmealService;
import com.tabwu.health.utils.SMSUtil;
import com.tabwu.health.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealController_mobile {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    private Result result = new Result();

    @RequestMapping("/getSetmeal")
    public Result getSetmealList() {
        try {
            List<Setmeal> allSetmeal = setmealService.getAllSetmeal();
            result.setFlag(true);
            result.setMessage(MessageConstant.QUERY_SETMEAL_SUCCESS);
            result.setData(allSetmeal);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.QUERY_SETMEAL_FAIL);
        }
        return result;
    }


    @RequestMapping("/findById")
    public Result findSetmealById(@RequestParam("id") Integer id) {
        try {
            Setmeal setmeal = setmealService.findSetmealById(id);
            result.setFlag(true);
            result.setMessage(MessageConstant.QUERY_SETMEAL_SUCCESS);
            result.setData(setmeal);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.QUERY_SETMEAL_FAIL);
        }
        return result;
    }

    @RequestMapping("/getSetmealById_mobile")
    public Result getSetmealById_mobile(@RequestParam("id") Integer id) {
        try {
            Setmeal setmeal = setmealService.getSetmealById_mobile(id);
            result.setFlag(true);
            result.setMessage(MessageConstant.QUERY_SETMEAL_SUCCESS);
            result.setData(setmeal);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.QUERY_SETMEAL_FAIL);
        }
        return result;
    }


    @RequestMapping("/sendvalidateCode")
    public Result sendvalidateCode(@RequestParam("telephone") String telephone) {

        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);

        Boolean aBoolean = SMSUtil.sendSMSShortMessage(telephone, validateCode.toString());

        if (aBoolean) {
            jedisPool.getResource().setex(telephone + MessageConstant.SENDTYPE_ORDER,60 * 5,validateCode.toString());
        }

        result.setFlag(true);

        result.setMessage(MessageConstant.SEND_VALIDATECODE_SUCCESS);

        return result;
    }
}
