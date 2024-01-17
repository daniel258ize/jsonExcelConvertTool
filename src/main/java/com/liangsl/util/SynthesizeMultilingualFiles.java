package com.liangsl.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.liangsl.dto.JsonDetailedSplitDto;
import com.liangsl.dto.MultilingualFileDto;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.liangsl.constant.Constants.*;

/**
 * Merge 3 output Excels into 1 excel
 */
public class SynthesizeMultilingualFiles {
    public static void excute(String filePath, String fileNameEn, String fileNameHk, String fileNameCn, String outputFilePath) {
        List<JsonDetailedSplitDto> excelDataEn = getExcelData(filePath + fileNameEn);
        List<JsonDetailedSplitDto> excelDataHk = getExcelData(filePath + fileNameHk);
        List<JsonDetailedSplitDto> excelDataCn = getExcelData(filePath + fileNameCn);
        //合并多语言excel
        List<MultilingualFileDto> dataList = mergeMultilingualData(excelDataEn, excelDataHk, excelDataCn);
        //导出文件
        String exportFileName = outputFilePath + "app_i18n" + System.currentTimeMillis() + ".xlsx";
        writeExcel(dataList, exportFileName);
    }

    private static List<MultilingualFileDto> mergeMultilingualData(List<JsonDetailedSplitDto> excelDataEn, List<JsonDetailedSplitDto> excelDataHk, List<JsonDetailedSplitDto> excelDataCn) {
        List<MultilingualFileDto> multilingualFileDtos = new ArrayList<>();
        //粤语
        LinkedHashMap<String, String> hkMap = new LinkedHashMap<>();
        //中文
        LinkedHashMap<String, String> cnMap = new LinkedHashMap<>();
        //将excelDataHk装入map
        for (JsonDetailedSplitDto hk : excelDataHk) {
            hkMap.put(hk.getLookupKey(), hk.getValue());
        }

        //将excelDataCn装入map
        for (JsonDetailedSplitDto cn : excelDataCn) {
            cnMap.put(cn.getLookupKey(), cn.getValue());
        }

        for (JsonDetailedSplitDto en : excelDataEn) {
            MultilingualFileDto multilingualFileDto = new MultilingualFileDto();
            String hkValue = hkMap.get(en.getLookupKey());
            String cnValue = cnMap.get(en.getLookupKey());
            multilingualFileDto.setKey1(en.getKey1());
            multilingualFileDto.setKey2(en.getKey2());
            multilingualFileDto.setKey3(en.getKey3());
            multilingualFileDto.setKey4(en.getKey4());
            multilingualFileDto.setKey5(en.getKey5());
            multilingualFileDto.setKey6(en.getKey6());
            multilingualFileDto.setKey7(en.getKey7());
            multilingualFileDto.setLookupKey(en.getLookupKey());
            multilingualFileDto.setEng(en.getValue());
            multilingualFileDto.setHk(hkValue);
            multilingualFileDto.setCn(cnValue);
            multilingualFileDtos.add(multilingualFileDto);
        }

        return multilingualFileDtos;
    }


    public static List<JsonDetailedSplitDto> getExcelData(String pathName) {
        XSSFWorkbook book;
        XSSFSheet sheet;
        JSONArray jsons;
        XSSFRow row;
        List<JsonDetailedSplitDto> jsonDetailedSplitDtos = new ArrayList<>();
        try {
            InputStream is = new FileInputStream(new File(pathName));
            book = new XSSFWorkbook(is);
            sheet = book.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    JsonDetailedSplitDto jsonDetailedSplitDto = new JsonDetailedSplitDto();
                    jsonDetailedSplitDto.setKey1(row.getCell(KEY1_COL_READ).getStringCellValue());
                    jsonDetailedSplitDto.setKey2(row.getCell(KEY2_COL_READ).getStringCellValue());
                    jsonDetailedSplitDto.setKey3(row.getCell(KEY3_COL_READ).getStringCellValue());
                    jsonDetailedSplitDto.setKey4(row.getCell(KEY4_COL_READ).getStringCellValue());
                    jsonDetailedSplitDto.setKey5(row.getCell(KEY5_COL_READ).getStringCellValue());
                    jsonDetailedSplitDto.setKey6(row.getCell(KEY6_COL_READ).getStringCellValue());
                    jsonDetailedSplitDto.setKey7(row.getCell(KEY7_COL_READ).getStringCellValue());
                    jsonDetailedSplitDto.setLookupKey(row.getCell(LOOKUP_COL_READ).getStringCellValue());
                    jsonDetailedSplitDto.setValue(row.getCell(VALUE_COL_READ).getStringCellValue());
                    jsonDetailedSplitDtos.add(jsonDetailedSplitDto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonDetailedSplitDtos;
    }

    public static void writeExcel(List<MultilingualFileDto> list, String fileName) {
        EasyExcel.write(fileName, MultilingualFileDto.class)
                .sheet("data")
                .doWrite(list);
    }
}
