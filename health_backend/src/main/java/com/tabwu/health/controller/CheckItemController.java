package com.tabwu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tabwu.health.constant.MessageConstant;
import com.tabwu.health.entity.CheckItem;
import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;
import com.tabwu.health.entity.Result;
import com.tabwu.health.service.CheckItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkItem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;

    private Result result = new Result();

    @RequestMapping("/add")
    public Result addCheckItem(@RequestBody CheckItem formData) {
        try {
            Long aLong = checkItemService.addCheckItem(formData);
            if (aLong <= 0) {
                result.setFlag(false);
                result.setMessage(MessageConstant.ADD_CHECKITEM_FAIL);
                return result;
            }
            result.setFlag(true);
            result.setMessage(MessageConstant.ADD_CHECKITEM_SUCCESS);
            result.setData(aLong);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return result;
    }

    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult pageResult = checkItemService.findPage(queryPageBean);
            result.setFlag(true);
            result.setMessage(MessageConstant.QUERY_CHECKITEM_SUCCESS);
            result.setData(pageResult);
        }catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        return result;
    }


    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Result deleteCheckItem(Integer id) {
        try {
            Long aLong = checkItemService.deleteCheckItem(id);
            if (aLong <= 0) {
                result.setFlag(false);
                result.setMessage(MessageConstant.DELETE_CHECKGROUP_FAIL);
                return result;
            }
            result.setFlag(true);
            result.setMessage(MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return result;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public Result checkItemUpdate(@RequestBody CheckItem formData) {
        try {
            Long aLong = checkItemService.updateCheckItem(formData);
            if (aLong <= 0) {
                result.setFlag(false);
                result.setMessage(MessageConstant.EDIT_CHECKITEM_FAIL);
                return result;
            }
            result.setFlag(true);
            result.setMessage(MessageConstant.EDIT_CHECKITEM_SUCCESS);
        }catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return result;
    }

    @RequestMapping("/findAll")
    public Result findAllCheckItem() {
        try {
            List<CheckItem> allCheckItem = checkItemService.findAllCheckItem();
            result.setFlag(true);
            result.setData(allCheckItem);
            result.setMessage(MessageConstant.QUERY_CHECKITEM_SUCCESS);
        }catch(Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        return result;
    }
}
