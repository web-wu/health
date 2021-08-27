package com.tabwu.health.mapper;

import com.github.pagehelper.Page;
import com.tabwu.health.entity.CheckGroup;
import com.tabwu.health.entity.Setmeal;

import java.util.HashMap;
import java.util.List;

public interface CheckGroupMapper {

    Long addCheckGroup(CheckGroup checkGroup);

    void setCheckItem_CheckGroup(HashMap hashMap);

    Page<CheckGroup> findCheckGroup(String querySring);

    Long CheckCheckGroupInCheckGroupCheckItem(Integer checkGroup_id);

    void clearStationCheckGroupAndCheckItem(Integer checkGroup_id);
    Long deleteCheckGroup(Integer checkGroup_id);

    List<Integer> getCheckItemIdsBycheckGroupId(Integer checkGroup_id);

    Long clearStationInCheckItemAndCheckGroup(Integer checkGroup_id);

    Long updateCheckGroup(CheckGroup checkGroup);

    CheckGroup findCheckGroupById(Integer id);

}
