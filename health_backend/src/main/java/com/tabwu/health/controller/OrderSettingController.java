package com.tabwu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tabwu.health.constant.MessageConstant;
import com.tabwu.health.entity.OrderSetting;
import com.tabwu.health.entity.Result;
import com.tabwu.health.service.OrderSettingService;
import com.tabwu.health.utils.POI;
import com.tabwu.health.utils.POIUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;
    private Result result = new Result();

    @RequestMapping("/upload")
    public Result uploadOrderSettingData(@RequestParam("excelFile") MultipartFile excelFile) throws IOException {

        try {
            List<ArrayList<String>> arrayLists = POI.readExcel(excelFile);

            List<OrderSetting> orderSettings = new ArrayList<>();
            if (arrayLists != null && arrayLists.size() > 0) {

                for (ArrayList<String> arrayList : arrayLists) {
                    OrderSetting orderSetting = new OrderSetting(arrayList.get(0), Integer.parseInt(arrayList.get(1)));
                    orderSettings.add(orderSetting);
                }

                orderSettingService.addOrderSetting(orderSettings);
                result.setFlag(true);
                result.setMessage(MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
            } else {
                result.setFlag(false);
                result.setMessage(MessageConstant.IMPORT_ORDERSETTING_FAIL);
            }
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return result;
    }
}
