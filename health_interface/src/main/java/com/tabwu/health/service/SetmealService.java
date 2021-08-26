package com.tabwu.health.service;

import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;
import com.tabwu.health.entity.Setmeal;

public interface SetmealService {
    PageResult getSetmeal(QueryPageBean params);

    void addImgToRedis(String filename);

    void addSetmeal(Setmeal setmeal);

    void twoSetDiff();
}
