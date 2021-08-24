package com.tabwu.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tabwu.health.entity.CheckItem;
import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;
import com.tabwu.health.mapper.CheckItemMapper;
import com.tabwu.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemMapper checkItemMapper;

    @Override
    public Long addCheckItem(CheckItem checkItem) {
        return checkItemMapper.addCheckItem(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        Page<CheckItem> checkItems = checkItemMapper.queryCheckItemByCondition(queryPageBean.getQueryString());

        return new PageResult(checkItems.getTotal(),checkItems.getResult());
    }

    @Override
    public Long deleteCheckItem(Integer id) {
        Long along = checkItemMapper.checkStationCheckItemAndCheckGrop(id);

        if (along > 0) {
            throw new RuntimeException("该检测项已经被应用，不能被删除！");
        }
        return checkItemMapper.deleteCheckItem(id);
    }

    @Override
    public Long updateCheckItem(CheckItem checkItem) {
        return checkItemMapper.updateCheckItem(checkItem);
    }

    @Override
    public List<CheckItem> findAllCheckItem() {
        return checkItemMapper.findAllCheckItem();
    }
}
