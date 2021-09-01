package com.tabwu.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tabwu.health.service.MemberService;
import com.tabwu.health.service.OrderService;
import com.tabwu.health.service.ReportService;
import com.tabwu.health.service.SetmealService;
import com.tabwu.health.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;

    private Map<String,Object> map = new HashMap<>();

    @Override
    public Map<String,Object> getBusibessData() {
       try {
           //获得当前日期
           String today = DateUtils.parseDate2String(DateUtils.getToday());
           //获得本周一的日期
           String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
           //获得本月第一天的日期
           String firstDay4ThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());

           //今日新增会员数量
           Integer todayMember = memberService.getMemberByDate(today);
           //所有会员数量
           Integer allMember = memberService.getAllMember();
           //获得本周一前的所有数据
           Integer beforeThisWeekAllMember = memberService.getBeforeThisWeekAllMember(thisWeekMonday);
           //获得本月第一天以前的所有会员数量
           Integer beforeThisMonthAllMember = memberService.getBeforeThisMonthAllMember(firstDay4ThisMonth);

           //今日预约数量
           Integer todayOrder = orderService.getTodayOrder(today);
           //获得当前所有预约数量
           //Integer allOrder = orderService.getAllOrder();
           //获得本周之前的所有预约数量
           Integer  beforeThisWeekAllOrder = orderService.getBeforeThisWeekAllOrder(thisWeekMonday);
           //获得本月第一天以前的所有预约数量
           Integer beforeThisMonthAllOrder = orderService.getBeforeThisMonthAllOrder(firstDay4ThisMonth);
           //今日到诊数
           Integer todayNumberTrue = orderService.getTodayNumberTrue(today);
           //本周到诊数量
           Integer weekNumberTrue = orderService.getWeekNumberTrue(thisWeekMonday);
           //本月到诊数量
           Integer monthNumberTrue = orderService.getMonthNumberTrue(firstDay4ThisMonth);
           //获得热门的4条套餐
           List<Map> hotSetmeal =  orderService.getHotSetmeal();

           map.put("reportDate", today);
           map.put("todayNewMember", todayMember);
           map.put("totalMember", allMember);
           map.put("thisWeekNewMember", beforeThisWeekAllMember);
           map.put("thisMonthNewMember", beforeThisMonthAllMember);
           map.put("todayOrderNumber", todayOrder);
           map.put("todayVisitsNumber", todayNumberTrue);
           map.put("thisWeekOrderNumber", beforeThisWeekAllOrder);
           map.put("thisWeekVisitsNumber", weekNumberTrue);
           map.put("thisMonthOrderNumber", beforeThisMonthAllOrder);
           map.put("thisMonthVisitsNumber", monthNumberTrue);
           map.put("hotSetmeal", hotSetmeal);

           return map;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }
}
