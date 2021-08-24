package com.tabwu.health.service;

import com.tabwu.health.entity.CheckGroup;
import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;

import java.util.List;

public interface CheckGroupService {
    void addCheckGroup(CheckGroup checkGroup, Integer[] checkitemIds);

    PageResult findCheckGroup(QueryPageBean queryPageBean);

    Long deleteCheckGroup(Integer checkGroup_id);

    List<Integer> getCheckItemIdsBycheckGroupId(Integer checkGroup_id);

    Long updateCheckGroup(CheckGroup checkGroup,Integer[] checkitemIds);
}
