package com.tabwu.health.mapper;

import com.github.pagehelper.Page;
import com.tabwu.health.entity.CheckItem;

import java.util.List;

public interface CheckItemMapper {

    Long addCheckItem(CheckItem checkItem);

    Page<CheckItem> queryCheckItemByCondition(String queryString);

    Long deleteCheckItem(Integer id);

    Long checkStationCheckItemAndCheckGrop(Integer checkitem_id);

    Long updateCheckItem(CheckItem checkItem);

    List<CheckItem> findAllCheckItem();

    CheckItem findCheckItemById(Integer id);
}
