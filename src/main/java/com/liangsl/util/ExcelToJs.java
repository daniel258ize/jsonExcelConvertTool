package com.liangsl.util;


import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.liangsl.dto.JsonDetailedSplitDto;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import static com.liangsl.constant.Constants.LOOKUP_COL_READ;
import static com.liangsl.constant.Constants.VALUE_COL_READ;

/**
 * Convert excel to i18n js
 */
public class ExcelToJs {
    /**
     * @param filePath       json file path
     * @param fileName       json file name
     * @param outputFilePath The path to the generated excel
     */
    public static void excute(String filePath, String fileName, String outputFilePath) {
        List<JsonDetailedSplitDto> excelData = getExcelData(filePath + fileName);
        String json = excelListToJson(excelData);
        String jsString = getJsString(json);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(outputFilePath + fileName.replace(".", "_") + System.currentTimeMillis() + ".js");
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(jsString);
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
            InputStream is = new FileInputStream(new File(pathName));
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
            String[] keyList = lookupKey.split("\\.");
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
            tmpNode.put(keyList[keyList.length - 1], dto.getValue());
        }
        System.out.println(JSON.toJSONString(map, true));
        //将map中的数据拼接成js字符串

        return JSON.toJSONString(map, true);
    }

    public static String getJsString(String jsonString) {
        StringBuilder modifiedStr = new StringBuilder(); // 存放修改后的结果
        StringBuilder lineStr = new StringBuilder(); // 存放每行的结果
        for (int i = 0; i < jsonString.length() - 1; i++) {
            char c = jsonString.charAt(i);//当前字符
            char preC = 0;//前一个字符
            if (i > 0) {
                preC = jsonString.charAt(i - 1);
            }
            char prePC = 0;//前前一个字符
            if (i > 1) {
                prePC = jsonString.charAt(i - 2);
            }
            //每次换行的时候，就对当前行进行处理
            if (c == '\n') {
                //情况1：换行前是:{
                if (preC == '{' && prePC == ':') {
                    String[] keyValue = lineStr.toString().split("\\:");
                    if (keyValue.length > 0) {
                        String key = keyValue[0];
                        key.substring(0, key.length() - 1);
                        for (int k = 0; k < key.length(); k++) {
                            if (key.charAt(k) == '\t') {
                                modifiedStr.append(key.charAt(k));
                            } else if (key.charAt(k) != '"' && key.charAt(k - 1) != '\t') {
                                modifiedStr.append(key.charAt(k));
                            }
                        }
                        modifiedStr.append(':').append(' ').append('{');
                    }
                    //情况2：以单个{结尾（区别于情况1）
                } else if (lineStr.length() == 1 && preC == '{') {
                    //清空行
                    lineStr.setLength(0);
                    continue;
                    //情况3：以,结尾说明是最里层的key-value
                } else if (preC == ',' && prePC != '}') {
                    String[] keyValue = lineStr.toString().split("\":\"");
                    if (keyValue.length > 0) {
                        //key
                        String key = keyValue[0];
                        StringBuilder modifiedKey = new StringBuilder();
                        for (int k = 0; k < key.length(); k++) {
                            if (key.charAt(k) == '\t') {
                                modifiedKey.append(key.charAt(k));
                            } else if (key.charAt(k) != '"' && key.charAt(k - 1) != '\t') {
                                modifiedKey.append(key.charAt(k));
                            } else if (key.charAt(k) == '"' && key.contains(" ")) {
                                //如果key中有空格，需要用单引号包着key
                                modifiedKey.append("'");
                            }
                        }
                        if (key.contains(" ")) {
                            //如果key中有空格，需要用单引号包着key
                            modifiedKey.append("'");
                        }

                        //value
                        if (keyValue.length > 1) {
                            String value = keyValue[1];
                            if (StringUtils.isNotBlank(value) && value.contains("'")) {
                                value = "`" + value;
                                value = value.substring(0, value.length() - 2);
                                value = value + "`,";
                            } else {
                                value = "'" + value;
                                value = value.substring(0, value.length() - 2);
                                value = value + "',";
                            }
                            //替換被被转义的字符
                            value = value.replace("\\\"", "\"");
                            //替換被被转义的字符
                            value = value.replace("\\\\n", "\\n");
                            modifiedStr.append(modifiedKey).append(':').append(" ").append(value);
                        }
                    }
                    //情况4：包含:
                } else if (lineStr.toString().contains("\":\"")) {
                    String[] keyValue = lineStr.toString().split("\":\"");
                    if (keyValue.length > 0) {
                        //key
                        String key = keyValue[0];
                        StringBuilder modifiedKey = new StringBuilder();
                        for (int k = 0; k < key.length(); k++) {
                            if (key.charAt(k) == '\t') {
                                modifiedKey.append(key.charAt(k));
                            } else if (key.charAt(k) != '"' && key.charAt(k - 1) != '\t') {
                                modifiedKey.append(key.charAt(k));
                            } else if (key.charAt(k) == '"' && key.contains(" ")) {
                                //如果key中有空格，需要用单引号包着key
                                modifiedKey.append("'");
                            }
                        }
                        if (key.contains(" ")) {
                            //如果key中有空格，需要用单引号包着key
                            modifiedKey.append("'");
                        }

                        //value
                        if (keyValue.length > 1) {
                            String value = keyValue[1];
                            if (StringUtils.isNotBlank(value) && value.contains("'")) {
                                value = "`" + value;
                                value = value.substring(0, value.length() - 1);
                                //替換被被转义的字符
                                value = value.replace("\\\"", "\"");
                                //替換被被转义的字符
                                value = value.replace("\\\\n", "\\n");
                                modifiedStr.append(modifiedKey).append(':').append(" ").append(value).append('`').append(',');
                            } else {
                                value = "'" + value;
                                value = value.substring(0, value.length() - 1);
                                //替換被被转义的字符
                                value = value.replace("\\\"", "\"");
                                //替換被被转义的字符
                                value = value.replace("\\\\n", "\\n");
                                modifiedStr.append(modifiedKey).append(':').append(" ").append(value).append('\'').append(',');
                            }
                        }
                    }
                } else if (preC == '}') {
                    modifiedStr.append(lineStr);
                } else {
                    modifiedStr.append(lineStr);
                }
                //换行符
                modifiedStr.append('\r').append('\n');
                //清空行
                lineStr.setLength(0);
                //没换行的时候，就收集当前行的数据
            } else {
                lineStr.append(c);
            }
        }
        modifiedStr.append(lineStr).append(';');
        //给倒数第二个括号加上逗号
        /*StringBuilder finalStr = new StringBuilder(); // 存放最后的结果
        int num = 0;
        for (int i = modifiedStr.length(); i > 0; i--) {
            if (modifiedStr.charAt(i) == '}') {
                num++;
                if (num == 2) {
                    finalStr.append(modifiedStr, 0, i + 1);
                    finalStr.append(',');
                    finalStr.append(modifiedStr, i + 1, modifiedStr.length());
                }
            }
        }
        return finalStr.toString();*/
        return modifiedStr.toString();
    }

}
