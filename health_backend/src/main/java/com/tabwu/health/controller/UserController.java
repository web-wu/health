package com.tabwu.health.controller;

import com.tabwu.health.constant.MessageConstant;
import com.tabwu.health.entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private Result result = new Result();

    @RequestMapping("/getUsername")
    public Result getUserInfo() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            result.setFlag(true);
            result.setMessage(MessageConstant.GET_USERNAME_SUCCESS);
            result.setData(user.getUsername());
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.GET_USERNAME_FAIL);
        }
        return result;
    }
}
