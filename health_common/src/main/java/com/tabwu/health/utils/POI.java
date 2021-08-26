package com.tabwu.health.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class POI {

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    private final static String DATE_FORMAT = "yyyy/MM/dd";

    public static List<ArrayList<String>> readExcel(MultipartFile file) throws IOException {
        checkFile(file);

        Workbook workbook = getWorkbook(file);

        List<ArrayList<String>> rowList = null;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);

            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            rowList = new ArrayList<>();
            for (int j = firstRowNum + 1; j <= lastRowNum; j++) {
                Row row = sheet.getRow(j);
                short firstCellNum = row.getFirstCellNum();
                short lastCellNum = row.getLastCellNum();

                ArrayList<String> cellList = new ArrayList<>();
                for (int k = firstCellNum; k < lastCellNum; k++) {
                    Cell cell = row.getCell(k);
                    String cellValue = dataTypeConvert(cell);
                    cellList.add(cellValue);
                }
                rowList.add(cellList);
            }
        }
        return rowList;
    }

    public static void checkFile(MultipartFile file) throws IOException {
        //判断文件是否存在
        if(null == file){
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){
            throw new IOException(fileName + "不是excel文件");
        }
    }



    public static Workbook getWorkbook(MultipartFile file) {
        Workbook workbook = null;
        try {
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            if (filename.endsWith(xls)) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (filename.endsWith(xlsx)) {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
        return workbook;
    }


    public static String dataTypeConvert(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //时间格式处理
        String dataFormatString = cell.getCellStyle().getDataFormatString();
        if(dataFormatString.equals("m/d/yy")){
            cellValue = new SimpleDateFormat(DATE_FORMAT).format(cell.getDateCellValue());
            return cellValue;
        }
        //数字格式处理 设置为string 类型读取，防止 1 读成 1.0
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }

        // 以下是判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue() + "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
                cellValue = cell.getCellFormula() + "";
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                cellValue = "";
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }

        return cellValue;
    }
}
