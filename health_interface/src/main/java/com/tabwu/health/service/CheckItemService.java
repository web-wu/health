package com.tabwu.health.service;

import com.tabwu.health.entity.CheckItem;
import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;

import java.util.List;

public interface CheckItemService {
    Long addCheckItem(CheckItem checkItem);

    PageResult findPage(QueryPageBean queryPageBean);

    Long deleteCheckItem(Integer id);

    Long updateCheckItem(CheckItem checkItem);

    List<CheckItem> findAllCheckItem();
}
