package com.tabwu.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;
import com.tabwu.health.entity.Setmeal;
import com.tabwu.health.mapper.SetmealMapper;
import com.tabwu.health.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public PageResult getSetmeal(QueryPageBean params) {
        PageHelper.startPage(params.getCurrentPage(),params.getPageSize());

        Page<Setmeal> setmeals = setmealMapper.getSetmeal(params.getQueryString());

        return new PageResult(setmeals.getTotal(),setmeals.getResult());
    }
}
