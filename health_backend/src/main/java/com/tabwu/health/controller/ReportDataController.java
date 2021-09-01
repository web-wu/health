package com.tabwu.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tabwu.health.constant.MessageConstant;
import com.tabwu.health.entity.Result;
import com.tabwu.health.service.ReportService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportDataController {
    private Result result = new Result();
    @Reference
    private ReportService reportService;

    @RequestMapping("/getBusinessReportData")
    public Result businessReport() {
        try {
            Map map = reportService.getBusibessData();
            result.setFlag(true);
            result.setMessage(MessageConstant.GET_BUSINESS_REPORT_SUCCESS);
            result.setData(map);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
        return result;
    }


    @RequestMapping("/exportBusinessReport")
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map map = reportService.getBusibessData();

            String templateRealPath = request.getSession().getServletContext().getRealPath("template") + File.separator + "report_template.xlsx";

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(new File(templateRealPath)));

            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);

            List<Map> hotSetmeal = (List<Map>) map.get("hotSetmeal");

            sheet.getRow(2).getCell(5).setCellValue((String) map.get("reportDate"));

            sheet.getRow(4).getCell(5).setCellValue((Integer) map.get("todayNewMember"));

            sheet.getRow(4).getCell(7).setCellValue((Integer) map.get("totalMember"));

            sheet.getRow(5).getCell(5).setCellValue((Integer) map.get("thisWeekNewMember"));

            sheet.getRow(5).getCell(7).setCellValue((Integer) map.get("thisMonthNewMember"));

            sheet.getRow(7).getCell(5).setCellValue((Integer) map.get("todayOrderNumber"));

            sheet.getRow(7).getCell(7).setCellValue((Integer) map.get("todayVisitsNumber"));

            sheet.getRow(8).getCell(5).setCellValue((Integer) map.get("thisWeekOrderNumber"));

            sheet.getRow(8).getCell(7).setCellValue((Integer) map.get("thisWeekVisitsNumber"));

            sheet.getRow(9).getCell(5).setCellValue((Integer) map.get("thisMonthOrderNumber"));

            sheet.getRow(9).getCell(7).setCellValue((Integer) map.get("thisMonthVisitsNumber"));

            int rowNum = 12;

            for (Map setmeal : hotSetmeal) {
                sheet.getRow(rowNum).getCell(4).setCellValue((String) setmeal.get("name"));
                sheet.getRow(rowNum).getCell(5).setCellValue((Long) setmeal.get("setmeal_count"));
                BigDecimal proportion = (BigDecimal) setmeal.get("proportion");
                sheet.getRow(rowNum).getCell(6).setCellValue(proportion.doubleValue());
                rowNum++;
            }

            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition","attachment;filename=report_business.xlsx");
            xssfWorkbook.write(outputStream);


            outputStream.flush();
            outputStream.close();
            xssfWorkbook.close();

            /*result.setFlag(true);
            result.setMessage(MessageConstant.EXPORT_BUSINESS_EXCEL_REPORT_SUCCESS);*/
            return null;
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.EXPORT_BUSINESS_EXCEL_REPORT_FAIL);
            return result;
        }
    }



    @RequestMapping("/exportBusinessReportPDF")
    public Result exportBusinessReportPDF(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map result = reportService.getBusibessData();

            String jrxmlPath = request.getSession().getServletContext().getRealPath("template") + File.separator + "health_business.jrxml";
            String jasperPath = request.getSession().getServletContext().getRealPath("template") + File.separator + "health_business.jasper";

            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");


            JasperCompileManager.compileReportToFile(jrxmlPath,jasperPath);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath, result, new JRBeanCollectionDataSource(hotSetmeal));

            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/pdf");
            response.setHeader("content-Disposition","attachment;filename=health_business.pdf");

            JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);

            return null;
        } catch (Exception e) {
            result.setFlag(false);
            result.setMessage(MessageConstant.EXPORT_BUSINESS_EXCEL_REPORT_FAIL);
            return result;
        }
    }
}
