package com.tabwu.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tabwu.health.entity.CheckGroup;
import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;
import com.tabwu.health.mapper.CheckGroupMapper;
import com.tabwu.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupMapper checkGroupMapper;

    @Override
    public void addCheckGroup(CheckGroup checkGroup, Integer[] checkitemIds) {
        Long aLong = checkGroupMapper.addCheckGroup(checkGroup);
        if (aLong > 0 && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("checkgroup_id", checkGroup.getId());
                map.put("checkitem_id", checkitemId);
                checkGroupMapper.setCheckItem_CheckGroup(map);
            }
        }
    }

    @Override
    public PageResult findCheckGroup(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckGroup> checkGroups = checkGroupMapper.findCheckGroup(queryPageBean.getQueryString());
        return new PageResult(checkGroups.getTotal(),checkGroups.getResult());
    }

    @Override
    public Long deleteCheckGroup(Integer checkGroup_id) {
        Long aLong = checkGroupMapper.CheckCheckGroupInCheckGroupCheckItem(checkGroup_id);
        System.out.println(aLong);
        if (aLong > 0) {
            return 0L;
        } else {
            checkGroupMapper.clearStationCheckGroupAndCheckItem(checkGroup_id);
            Long aLong1 = checkGroupMapper.deleteCheckGroup(checkGroup_id);
            if (aLong1 > 0) {
                return aLong1;
            } else {
                return 0L;
            }
        }
    }

    @Override
    public List<Integer> getCheckItemIdsBycheckGroupId(Integer checkGroup_id) {
        return checkGroupMapper.getCheckItemIdsBycheckGroupId(checkGroup_id);
    }

    @Override
    public Long updateCheckGroup(CheckGroup checkGroup, Integer[] checkitemIds) {
       checkGroupMapper.clearStationInCheckItemAndCheckGroup(checkGroup.getId());

        if (checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("checkgroup_id", checkGroup.getId());
                map.put("checkitem_id", checkitemId);
                checkGroupMapper.setCheckItem_CheckGroup(map);
            }
            return checkGroupMapper.updateCheckGroup(checkGroup);
        } else {
            return 0L;
        }
    }
}
