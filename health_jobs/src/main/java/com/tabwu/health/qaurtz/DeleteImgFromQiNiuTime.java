package com.tabwu.health.qaurtz;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tabwu.health.service.SetmealService;


public class DeleteImgFromQiNiuTime {
    @Reference
    private SetmealService setmealService;

    public void deleteImg() {
        setmealService.twoSetDiff();
    }
}
