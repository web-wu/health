package com.tabwu.health.service;

import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;

public interface SetmealService {
    PageResult getSetmeal(QueryPageBean params);
}
