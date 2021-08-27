package com.tabwu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tabwu.health.constant.MessageConstant;
import com.tabwu.health.entity.PageResult;
import com.tabwu.health.entity.QueryPageBean;
import com.tabwu.health.entity.Result;
import com.tabwu.health.entity.Setmeal;
import com.tabwu.health.service.SetmealService;
import com.tabwu.health.utils.QiNiuUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    private Result result = new Result();

    @RequestMapping("/uploadImg")
    public Result uploadImg(@RequestParam("imgFile")MultipartFile imgFile) {

        try {
            String filename = imgFile.getOriginalFilename();
            int i = filename.lastIndexOf(".");
            String subName = filename.substring(i);
            String newFilename = UUID.randomUUID().toString() + subName;
            QiNiuUtil.uploadToQiniu(imgFile.getBytes(),newFilename);
            setmealService.addImgToRedis(newFilename);
            result.setFlag(true);
            result.setMessage(MessageConstant.PIC_UPLOAD_SUCCESS);
            result.setData(newFilename);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.PIC_UPLOAD_FAIL);
        }
        return result;
    }


    @RequestMapping("/findSetmeal")
    public Result getSetmeal(@RequestBody QueryPageBean params) {
        try {
            PageResult pageResult = setmealService.getSetmeal(params);
            result.setFlag(true);
            result.setMessage(MessageConstant.ADD_SETMEAL_SUCCESS);
            result.setData(pageResult);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.ADD_SETMEAL_FAIL);
        }
        return result;
    }

    @RequestMapping("/addSetmeal")
    public Result addSetmeal(@RequestBody Setmeal formData) {
        try {
            setmealService.addSetmeal(formData);
            result.setFlag(true);
            result.setMessage(MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.ADD_SETMEAL_FAIL);
        }
        return result;
    }

}
