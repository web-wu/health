package com.tabwu.health.mapper;

import com.github.pagehelper.Page;
import com.tabwu.health.entity.Setmeal;

public interface SetmealMapper {

    Page<Setmeal> getSetmeal(String queryString);

    Long addSetmeal(Setmeal setmeal);
}
