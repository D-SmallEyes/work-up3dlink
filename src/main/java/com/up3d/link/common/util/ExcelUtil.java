package com.up3d.link.common.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * excel表格工具
 */
public class ExcelUtil {

    public static List<Map<String, Object>> readExcel(String path) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        File file = new File(path);
        //判断文件是否存在
        if (!file.isFile() || !file.exists()) {
            return new ArrayList<>();
        }

        Workbook wb = null;
        try {
            String extension = FilenameUtils.getExtension(file.getName());
            if ("xls".equals(extension)) {
                FileInputStream inputStream = null;

                inputStream = new FileInputStream(file);

                wb = new HSSFWorkbook(inputStream);
            } else if ("xlsx".equals(extension)) {
                wb = new XSSFWorkbook(file);
            } else {
                System.out.println("文件类型错误");
                return new ArrayList<>();
            }

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

        //开始解析
        Sheet sheet = wb.getSheetAt(0);

        //遍历行
        for (int index = sheet.getFirstRowNum(); index <= sheet.getLastRowNum(); index++) {
            Map<String, Object> record = new HashMap<>();
            //获取当前行的内容
            Row row = sheet.getRow(index);
            if (row == null) {
                continue;
            }

            for (int column = row.getFirstCellNum(); column < row.getLastCellNum(); column++) {
                if (row.getCell(column) == null) {
                    System.out.println(String.format("index %s,column %s is empty", index, column));
                    continue;
                }
                record.put(String.valueOf(column), row.getCell(column).toString());
            }
            mapList.add(record);
        }

        return mapList;

    }

    /**
     * 通过模板创建excel
     *
     * @param excelSheetAdapters 适配器数据
     * @param inputStream        输入模板流
     * @param outputStream       输出结果流
     */
    public static void createExcelFromTemple(List<ExcelSheetAdapter> excelSheetAdapters, InputStream inputStream, OutputStream outputStream) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            for (int i = 0; i < excelSheetAdapters.size(); i++) {
                ExcelSheetAdapter excelSheetAdapter = excelSheetAdapters.get(i);

                //工作表名字
                if (excelSheetAdapter.getSheetName() != null) {
                    wb.setSheetName(i, excelSheetAdapter.getSheetName());
                }

                //工作表行列
                XSSFSheet sheet = wb.getSheetAt(i);

                createRowAndCell(sheet, excelSheetAdapter);

                //工作表特殊cell
                createSpecialCell(sheet, excelSheetAdapter.getExcelSheetCols());
            }
            wb.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createSpecialCell(XSSFSheet sheet, List<ExcelSheetCol> excelSheetCols) {
        for (ExcelSheetCol excelSheetCol : excelSheetCols) {
            XSSFRow row = sheet.getRow(excelSheetCol.getRow());
            if (row == null) {
                row = sheet.createRow(excelSheetCol.getRow());
            }
            XSSFCell cell = row.getCell(excelSheetCol.getCol());
            if (cell == null) {
                cell = row.createCell(excelSheetCol.getCol());
            }
            setCellJavaTypeToExcelType(cell, excelSheetCol.getData());
        }
    }

    /**
     * 填入行和单元格
     *
     * @param sheet
     * @param excelSheetAdapter
     */
    private static void createRowAndCell(XSSFSheet sheet, ExcelSheetAdapter excelSheetAdapter) {
        for (int rowIdx = excelSheetAdapter.getFirstRow(); rowIdx < excelSheetAdapter.getLastRow(); rowIdx++) {
            int index = rowIdx - excelSheetAdapter.getFirstRow();
            if (index >= excelSheetAdapter.getData().size()) {
                break;
            }
            createCell(sheet.createRow(rowIdx), excelSheetAdapter, excelSheetAdapter.getData().get(index));
        }
    }

    /**
     * 填入单元格
     *
     * @param row
     * @param excelSheetAdapter
     * @param rowDataIdx
     */
    private static void createCell(XSSFRow row, ExcelSheetAdapter excelSheetAdapter, LinkedHashMap<String, Object> rowDataIdx) {
        for (int cellIdx = excelSheetAdapter.getFirstCol(); cellIdx < excelSheetAdapter.getLastCol(); cellIdx++) {
            setCellJavaTypeToExcelType(row.createCell(cellIdx), rowDataIdx.get(String.valueOf(cellIdx)));
        }
    }

    public static void setCellJavaTypeToExcelType(XSSFCell cell, Object celData) {
        if (celData == null) {
            return;
        }

        if (celData instanceof Boolean) {
            cell.setCellValue((Boolean) celData);
        }
        if (celData instanceof Double) {
            cell.setCellValue((Double) celData);
        }

        if (celData instanceof Date) {
            cell.setCellValue((Date) celData);
        }

        cell.setCellValue(String.valueOf(celData));
    }


    public static void createSingleSheetExcel(LinkedHashMap<String, Object> title, List<LinkedHashMap<String, Object>> list, OutputStream outputStream) {
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("sheet1");
            XSSFRow firstRow = sheet.createRow(0);

            //放入title行
            putTitleInRow(title, firstRow);

            for (int rowIndex = 0; rowIndex < list.size(); rowIndex++) {
                XSSFRow row = sheet.createRow(rowIndex + 1);
                //依据title行放入数据行
                putDataInRow(title, row, list.get(rowIndex));
            }
            wb.write(outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void putDataInRow(LinkedHashMap<String, Object> title, XSSFRow row, Map<String, Object> data) {
        int cellIndexData = 0;
        for (String key : title.keySet()) {
            Object value = data.get(key);
            if (value == null) {
                value = "";
            }
            row.createCell(cellIndexData).setCellValue(String.valueOf(value));
            cellIndexData++;
        }
    }

    private static void putTitleInRow(LinkedHashMap<String, Object> title, XSSFRow firstRow) {
        if (title == null){
            return;
        }
        int cellIndex = 0;
        for (String key : title.keySet()) {
            Object value = title.get(key);
            firstRow.createCell(cellIndex).setCellValue(String.valueOf(value));
            cellIndex++;
        }
    }


    public static void createLabStaffExcel(LinkedHashMap<String, Object> title, OutputStream outputStream, Map<String, String[]> map) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("sheet1");
            HSSFRow firstRow = sheet.createRow(0);
            putTitleInRow(title, firstRow);

            setHSSFValidation(sheet, map.get("sex"), 1, 500, 2, 2);// 第一列的前501行都设置为选择列表形式.
            //setHSSFPrompt(sheet, "promt Title", "prompt Content",0, 1, 1, 1);// 第二列的前501行都设置提示.


            wb.write(outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void putTitleInRow(LinkedHashMap<String, Object> title, HSSFRow firstRow) {
        if (title == null){
            return;
        }
        int cellIndex = 0;
        for (String key : title.keySet()) {
            Object value = title.get(key);
            firstRow.createCell(cellIndex).setCellValue(String.valueOf(value));
            cellIndex++;
        }
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet,
                                              String[] textlist, int firstRow, int endRow, int firstCol,
                                              int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);
        sheet.addValidationData(data_validation_list);
        return sheet;
    }

    /**
     * 设置单元格上提示
     *
     * @param sheet         要设置的sheet.
     * @param promptTitle   标题
     * @param promptContent 内容
     * @param firstRow      开始行
     * @param endRow        结束行
     * @param firstCol      开始列
     * @param endCol        结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle,
                                          String promptContent, int firstRow, int endRow, int firstCol, int endCol) {
        // 构造constraint对象
        DVConstraint constraint = DVConstraint.createCustomFormulaConstraint("BB1");
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_view = new HSSFDataValidation(regions, constraint);
        data_validation_view.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(data_validation_view);
        return sheet;
    }

    @Data
    public static class ExcelSheetAdapter {
        private String sheetName;
        private int firstRow;
        private int firstCol;
        private List<ExcelSheetCol> excelSheetCols = new ArrayList<>();

        private LinkedHashMap<String, Object> title = new LinkedHashMap<>();
        private List<LinkedHashMap<String, Object>> data = new ArrayList<>();

        public int getLastRow() {
            return firstRow + 1 + data.size();
        }

        public int getLastCol() {
            if (data.size() == 0) {
                return firstCol + title.size();
            }

            return firstCol + Math.max(title.size(), data.get(0).size());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExcelSheetCol {
        private int row;
        private int col;
        private String data;
    }


}
