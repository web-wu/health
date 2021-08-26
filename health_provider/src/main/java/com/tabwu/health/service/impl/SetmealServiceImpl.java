package com.tabwu.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tabwu.health.constant.MessageConstant;
import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;
import com.tabwu.health.entity.Setmeal;
import com.tabwu.health.mapper.SetmealMapper;
import com.tabwu.health.service.SetmealService;
import com.tabwu.health.utils.QiNiuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public PageResult getSetmeal(QueryPageBean params) {
        PageHelper.startPage(params.getCurrentPage(),params.getPageSize());

        Page<Setmeal> setmeals = setmealMapper.getSetmeal(params.getQueryString());

        return new PageResult(setmeals.getTotal(),setmeals.getResult());
    }

    @Override
    public void addImgToRedis(String filename) {
        try {
            jedisPool.getResource().sadd(MessageConstant.PIC_IMG_SET,filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSetmeal(Setmeal setmeal) {
        Long aLong = setmealMapper.addSetmeal(setmeal);
        if (aLong > 0) {
            jedisPool.getResource().sadd(MessageConstant.PIC_VALID_IMG_SET,setmeal.getImg());
        }
    }

    @Override
    public void twoSetDiff() {
        try {
            Set<String> sdiff = jedisPool.getResource().sdiff(MessageConstant.PIC_IMG_SET, MessageConstant.PIC_VALID_IMG_SET);
            for (String imgfile : sdiff) {
                QiNiuUtil.deleteFileFromQiniu(imgfile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
