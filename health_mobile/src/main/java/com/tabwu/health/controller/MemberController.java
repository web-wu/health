package com.tabwu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.tabwu.health.constant.MessageConstant;
import com.tabwu.health.entity.Member;
import com.tabwu.health.entity.Result;
import com.tabwu.health.service.MemberService;
import com.tabwu.health.utils.DateUtils;
import com.tabwu.health.utils.SMSUtil;
import com.tabwu.health.utils.ValidateCodeUtils;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    private Result result;

    @Autowired
    private JedisPool jedisPool;
    @Reference
    private MemberService memberService;

    @RequestMapping("/send4Login")
    public Result loginValidateCode(@RequestParam("telephone") String telephone) {
        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);

        Boolean aBoolean = SMSUtil.sendSMSMemberLogin(telephone, validateCode.toString());
        if (!aBoolean) {
            result.setFlag(false);
            result.setMessage(MessageConstant.SEND_VALIDATECODE_FAIL);
        } else {
            jedisPool.getResource().setex(telephone + MessageConstant.SENDTYPE_LOGIN,60*3,validateCode.toString());
            result.setFlag(true);
            result.setMessage(MessageConstant.SEND_VALIDATECODE_SUCCESS);
        }

        return result;
    }

    @RequestMapping("/memberLogin")
    public Result memberLogin(@RequestBody Map map, HttpServletResponse response) {
        String telephone = (String) map.get("telephone");
        String validateCode = (String) map.get("validateCode");

        String validateCodeInRedis = jedisPool.getResource().get(telephone + MessageConstant.SENDTYPE_LOGIN);

        if (validateCodeInRedis == null || !validateCodeInRedis.equals(validateCode)) {
            result.setFlag(false);
            result.setMessage(MessageConstant.LOGIN_FAIL);
        } else {
            Member member = memberService.getMemberByPhone(telephone);

            if (member == null) {
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date());
                memberService.addMember(member);
            }

            String memberStr = JSON.toJSON(member).toString();
            jedisPool.getResource().setex(telephone,60*30,memberStr);

            Cookie cookie = new Cookie("login_member_telephone", telephone);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24*30);
            response.addCookie(cookie);

            result.setFlag(true);
            result.setMessage(MessageConstant.LOGIN_SUCCESS);
        }

        return result;
    }
}
