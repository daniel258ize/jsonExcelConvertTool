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

public class ExcelToJs {

    /*   public static void main(String[] args) {
           List<JsonDetailedSplitDto> excelData = getExcelData("D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - ch.xlsx");
           String json = excelListToJson(excelData);
           String jsString = getJsString(json);
           FileWriter fileWriter = null;
           BufferedWriter bufferedWriter = null;
           try {
               fileWriter = new FileWriter("F:\\min_winnie\\source\\APP (st)_JW20231229 - ch.txt");
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
   */
    public static void excute(String pathName, String fileName) {
        List<JsonDetailedSplitDto> excelData = getExcelData(pathName);
        String json = excelListToJson(excelData);
        String jsString = getJsString(json);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
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
                    /*jsonDetailedSplitDto.setKey1(row.getCell(0).getStringCellValue());
                    jsonDetailedSplitDto.setKey2(row.getCell(1).getStringCellValue());
                    jsonDetailedSplitDto.setKey3(row.getCell(2).getStringCellValue());
                    jsonDetailedSplitDto.setKey4(row.getCell(3).getStringCellValue());
                    jsonDetailedSplitDto.setKey5(row.getCell(4).getStringCellValue());
                    jsonDetailedSplitDto.setKey6(row.getCell(5).getStringCellValue());*/
                    jsonDetailedSplitDto.setLookupKey(row.getCell(0).getStringCellValue());
                    jsonDetailedSplitDto.setValue(row.getCell(1).getStringCellValue());
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
            if (c == '\n') {
                //:{
                if (preC == '{' && prePC == ':') {
                    String[] keyList = lineStr.toString().split("\\:");
                    if (keyList.length > 0) {
                        String key = keyList[0];
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
                    //
                    //以{结尾
                } else if (lineStr.length() == 1 && preC == '{') {
                    //清空行
                    lineStr.setLength(0);
                    continue;
                    //,
                } else if (preC == ',' && prePC != '}') {
                    String[] keyList = lineStr.toString().split("\":\"");
                    if (keyList.length > 0) {
                        String key = keyList[0];
                        for (int k = 0; k < key.length(); k++) {
                            if (key.charAt(k) == '\t') {
                                modifiedStr.append(key.charAt(k));
                            } else if (key.charAt(k) != '"' && key.charAt(k - 1) != '\t') {
                                modifiedStr.append(key.charAt(k));
                            }
                        }

                        if (keyList.length > 1) {
                            String value = keyList[1];
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
                            modifiedStr.append(':').append(" ").append(value);
                        }
                    }
                } else if (lineStr.toString().contains("\":\"")) {
                    String[] keyList = lineStr.toString().split("\":\"");
                    if (keyList.length > 0) {
                        String key = keyList[0];
                        for (int k = 0; k < key.length(); k++) {
                            if (key.charAt(k) == '\t') {
                                modifiedStr.append(key.charAt(k));
                            } else if (key.charAt(k) != '"' && key.charAt(k - 1) != '\t') {
                                modifiedStr.append(key.charAt(k));
                            }
                        }

                        if (keyList.length > 1) {
                            String value = keyList[1];
                            if (StringUtils.isNotBlank(value) && value.contains("'")) {
                                value = "`" + value;
                                value = value.substring(0, value.length() - 1);
                                //替換被被转义的字符
                                value = value.replace("\\\"", "\"");
                                //替換被被转义的字符
                                value = value.replace("\\\\n", "\\n");
                                modifiedStr.append(':').append(" ").append(value).append('`').append(',');
                            } else {
                                value = "'" + value;
                                value = value.substring(0, value.length() - 1);
                                //替換被被转义的字符
                                value = value.replace("\\\"", "\"");
                                //替換被被转义的字符
                                value = value.replace("\\\\n", "\\n");
                                modifiedStr.append(':').append(" ").append(value).append('\'').append(',');
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
