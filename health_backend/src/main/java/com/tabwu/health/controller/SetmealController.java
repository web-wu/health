package com.tabwu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tabwu.health.constant.MessageConstant;
import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;
import com.tabwu.health.entity.Result;
import com.tabwu.health.entity.Setmeal;
import com.tabwu.health.service.SetmealService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    private Result result = new Result();

    @RequestMapping("/findSetmeal")
    public Result getSetmeal(@RequestBody QueryPageBean params) {
        System.out.println(params);
        try {
            PageResult pageResult = setmealService.getSetmeal(params);
            result.setFlag(true);
            result.setMessage(MessageConstant.ADD_SETMEAL_SUCCESS);
            result.setData(pageResult);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.ADD_SETMEAL_FAIL);
        }
        return result;
    }
}
