package com.tabwu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tabwu.health.entity.Result;
import com.tabwu.health.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
//    private OrderSettingService orderSettingService;
    private Result result = new Result();

    @RequestMapping("/upload")
    public Result uploadOrderSettingData(@RequestParam("excelFile") MultipartFile excelFile) throws IOException {
        List<String[]> excel = POIUtils.readExcel(excelFile);
        return result;
    }
}
