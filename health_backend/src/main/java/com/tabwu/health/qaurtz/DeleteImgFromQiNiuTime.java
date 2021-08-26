package com.tabwu.health.qaurtz;

import com.tabwu.health.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;


public class DeleteImgFromQiNiuTime {
    @Autowired
    private SetmealService setmealService;

    public void deleteImg() {
        setmealService.twoSetDiff();
    }
}
