package com.tabwu.health.service;

import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;
import com.tabwu.health.entity.Setmeal;

import java.util.List;

public interface SetmealService {
    PageResult getSetmeal(QueryPageBean params);

    void addImgToRedis(String filename);

    void addSetmeal(Setmeal setmeal);

    void twoSetDiff();

    List<Setmeal> getAllSetmeal();

    Setmeal findSetmealById(Integer id);

    Setmeal getSetmealById_mobile(Integer id);
}
