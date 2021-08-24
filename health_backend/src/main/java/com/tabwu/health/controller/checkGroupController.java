package com.tabwu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tabwu.health.constant.MessageConstant;
import com.tabwu.health.entity.CheckGroup;
import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;
import com.tabwu.health.entity.Result;
import com.tabwu.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkGroup")
public class checkGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    private Result result = new Result();


    @RequestMapping("/add")
    public Result addCheckGroup(@RequestBody CheckGroup formData, Integer[] checkitemIds) {
        try {
            checkGroupService.addCheckGroup(formData, checkitemIds);
            result.setFlag(true);
            result.setMessage(MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return result;
    }

    @RequestMapping("/findCheckGroup")
    public Result findCheckGroup(@RequestBody QueryPageBean queryPageBean) {
        try {
            PageResult checkGroups = checkGroupService.findCheckGroup(queryPageBean);
            result.setFlag(true);
            result.setData(checkGroups);
            result.setMessage(MessageConstant.QUERY_CHECKGROUP_SUCCESS);
        } catch(Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        return result;
    }

    @RequestMapping(value = "/deleteCheckGroup",method = RequestMethod.DELETE)
    public Result deleteCheckGroup(Integer checkGroup_id){
        try {
            Long aLong = checkGroupService.deleteCheckGroup(checkGroup_id);
            if (aLong > 0) {
                result.setFlag(true);
                result.setMessage(MessageConstant.DELETE_CHECKGROUP_SUCCESS);
            } else {
                result.setFlag(false);
                result.setMessage(MessageConstant.DELETE_CHECKGROUP_FAIL);
            }
        }catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return result;
    }

    @RequestMapping("/getCheckItemIds")
    public Result getCheckItemIds(Integer checkGroup_id) {
        try {
            List<Integer> checkItemIds = checkGroupService.getCheckItemIdsBycheckGroupId(checkGroup_id);
            result.setFlag(true);
            result.setMessage("查询检测项id成功！！！");
            result.setData(checkItemIds);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage("查询检测项id失败！！！");
        }
        return result;
    }

    @RequestMapping("/updateCheckGroup")
    public Result updateCheckGroup(@RequestBody CheckGroup formData,Integer[] checkitemIds) {
        try {
            checkGroupService.updateCheckGroup(formData,checkitemIds);
            result.setFlag(true);
            result.setMessage(MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.EDIT_CHECKGROUP_FAIL);
        }

        return result;
    }
}
