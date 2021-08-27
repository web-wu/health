package com.tabwu.health.mapper;

import com.github.pagehelper.Page;
import com.tabwu.health.entity.Setmeal;

import java.util.List;

public interface SetmealMapper {

    Page<Setmeal> getSetmeal(String queryString);

    Long addSetmeal(Setmeal setmeal);

    List<Setmeal> getAllSetmeal();

    Setmeal getSetmealById(Integer id);

    Setmeal getSetmealById_mobile(Integer id);
}
