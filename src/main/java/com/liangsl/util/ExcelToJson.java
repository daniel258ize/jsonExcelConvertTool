package com.liangsl.util;

import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.liangsl.dto.JsonDetailedSplitDto;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import static com.liangsl.constant.Constants.LOOKUP_COL_READ;
import static com.liangsl.constant.Constants.VALUE_COL_READ;

/**
 * Convert excel to i18n json
 */
public class ExcelToJson {
    /**
     *
     * @param filePath json file path
     * @param fileName json file name
     * @param outputFilePath The path to the generated excel
     */
    public static void excute(String filePath, String fileName, String outputFilePath) {
        List<JsonDetailedSplitDto> excelData = getExcelData(filePath + fileName);
        String json = excelListToJson(excelData);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(outputFilePath + fileName.replace(".", "_") + System.currentTimeMillis() + ".json");
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static List<JsonDetailedSplitDto> getExcelData(String pathName) {
        XSSFWorkbook book;
        XSSFSheet sheet;
        JSONArray jsons;
        XSSFRow row;
        List<JsonDetailedSplitDto> jsonDetailedSplitDtos = new ArrayList<>();
        try {
            InputStream is = new FileInputStream(pathName);
            book = new XSSFWorkbook(is);
            sheet = book.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    JsonDetailedSplitDto jsonDetailedSplitDto = new JsonDetailedSplitDto();
                    jsonDetailedSplitDto.setLookupKey(row.getCell(LOOKUP_COL_READ).getStringCellValue());
                    jsonDetailedSplitDto.setValue(row.getCell(VALUE_COL_READ).getStringCellValue());
                    jsonDetailedSplitDtos.add(jsonDetailedSplitDto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jsonDetailedSplitDtos.get(jsonDetailedSplitDtos.size() - 1));
        return jsonDetailedSplitDtos;
    }

    public static String excelListToJson(List<JsonDetailedSplitDto> jsonDetailedSplitDtos) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (JsonDetailedSplitDto dto : jsonDetailedSplitDtos) {
            String lookupKey = dto.getLookupKey();
            //判断key是否为空
            if (StringUtils.isBlank(lookupKey)) {
                continue;
            }
            // a.b.c:1
            // a.b.e:2
            // a.f:22
            LinkedHashMap<String, Object> tmpNode = map;
            boolean hasFollowingDot = org.apache.commons.lang3.StringUtils.endsWith(lookupKey, ".");
            if (hasFollowingDot) org.apache.commons.lang3.StringUtils.removeEnd(lookupKey, ".");
            String[] keyList = org.apache.commons.lang3.StringUtils.split(lookupKey, '.');
            for (int j = 0; j < keyList.length - 1; j++) {
                String key = keyList[j];
                Object o = tmpNode.get(key);
                //如果找不到，就要新增节点
                if (Objects.isNull(o)) {
                    LinkedHashMap<String, Object> nextNode = new LinkedHashMap<>();
                    tmpNode.put(key, nextNode);
                    tmpNode = nextNode;
                    //如果找到节点，就向后移动一个节点
                } else if (o instanceof LinkedHashMap) {
                    tmpNode = (LinkedHashMap<String, Object>) o;
                }
            }
            String lastKey = keyList[keyList.length - 1];
            if (hasFollowingDot) lastKey += ".";
            tmpNode.put(lastKey, dto.getValue());
        }
//        System.out.println(map);
        System.out.println(JSON.toJSONString(map, true));
        return JSON.toJSONString(map, true);
    }

}
