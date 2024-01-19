package com.liangsl.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.liangsl.dto.JsonDetailedSplitDto;
import com.liangsl.dto.MultilingualFileDto;
import com.liangsl.dto.SimpleMergeDto;
import com.liangsl.dto.SplitMultilingualFilesDto;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.liangsl.constant.Constants.*;

/**
 * Split 1 excel into 3 excel
 */
public class SplitMultilingualFiles {

    public static void excute(String filePath, String fileName, String outputFilePath) {
        SplitMultilingualFilesDto splitMultilingualFilesDto = getExcelData(filePath + fileName);
        String engExportFileName = outputFilePath + "eng" + System.currentTimeMillis() + ".xlsx";
        writeExcel(splitMultilingualFilesDto.getEngJsonDetailedSplitList(), engExportFileName);

        String hkExportFileName = outputFilePath + "hk" + System.currentTimeMillis() + ".xlsx";
        writeExcel(splitMultilingualFilesDto.getHkJsonDetailedSplitList(), hkExportFileName);

        String cnExportFileName = outputFilePath + "cn" + System.currentTimeMillis() + ".xlsx";
        writeExcel(splitMultilingualFilesDto.getCnJsonDetailedSplitList(), cnExportFileName);
    }

    private static SplitMultilingualFilesDto getExcelData(String pathName) {

        XSSFWorkbook book;
        XSSFSheet sheet;
        XSSFRow row;
        SplitMultilingualFilesDto splitMultilingualFilesDto = new SplitMultilingualFilesDto();
        List<JsonDetailedSplitDto> engJsonDetailedSplitList = new ArrayList<>();
        List<JsonDetailedSplitDto> hkJsonDetailedSplitList = new ArrayList<>();
        List<JsonDetailedSplitDto> cnJsonDetailedSplitList = new ArrayList<>();
        splitMultilingualFilesDto.setEngJsonDetailedSplitList(engJsonDetailedSplitList);
        splitMultilingualFilesDto.setHkJsonDetailedSplitList(hkJsonDetailedSplitList);
        splitMultilingualFilesDto.setCnJsonDetailedSplitList(cnJsonDetailedSplitList);
        try {
            InputStream is = new FileInputStream(new File(pathName));
            book = new XSSFWorkbook(is);
            sheet = book.getSheetAt(0);
            System.out.println("LastRowNum" + sheet.getLastRowNum());
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    String key1 = row.getCell(KEY1_COL_READ).toString();
                    String key2 = row.getCell(KEY2_COL_READ).toString();
                    String key3 = row.getCell(KEY3_COL_READ).toString();
                    String key4 = row.getCell(KEY4_COL_READ).toString();
                    String key5 = row.getCell(KEY5_COL_READ).toString();
                    String key6 = row.getCell(KEY6_COL_READ).toString();
                    String key7 = row.getCell(KEY7_COL_READ).toString();
                    String lookupKey = row.getCell(LOOKUP_COL_READ).toString();
                    String eng = row.getCell(ENG_COL_READ).toString();
                    String hk = row.getCell(HK_COL_READ).toString();
                    String cn = row.getCell(CN_COL_READ).toString();
                    JsonDetailedSplitDto engJsonDetailedSplitDto = new JsonDetailedSplitDto();
                    JsonDetailedSplitDto hkJsonDetailedSplitDto = new JsonDetailedSplitDto();
                    JsonDetailedSplitDto cnJsonDetailedSplitDto = new JsonDetailedSplitDto();
                    engJsonDetailedSplitDto.setKey1(key1);
                    engJsonDetailedSplitDto.setKey2(key2);
                    engJsonDetailedSplitDto.setKey3(key3);
                    engJsonDetailedSplitDto.setKey4(key4);
                    engJsonDetailedSplitDto.setKey5(key5);
                    engJsonDetailedSplitDto.setKey6(key6);
                    engJsonDetailedSplitDto.setKey7(key7);
                    engJsonDetailedSplitDto.setLookupKey(lookupKey);
                    engJsonDetailedSplitDto.setValue(eng);

                    hkJsonDetailedSplitDto.setKey1(key1);
                    hkJsonDetailedSplitDto.setKey2(key2);
                    hkJsonDetailedSplitDto.setKey3(key3);
                    hkJsonDetailedSplitDto.setKey4(key4);
                    hkJsonDetailedSplitDto.setKey5(key5);
                    hkJsonDetailedSplitDto.setKey6(key6);
                    hkJsonDetailedSplitDto.setKey7(key7);
                    hkJsonDetailedSplitDto.setLookupKey(lookupKey);
                    hkJsonDetailedSplitDto.setValue(hk);

                    cnJsonDetailedSplitDto.setKey1(key1);
                    cnJsonDetailedSplitDto.setKey2(key2);
                    cnJsonDetailedSplitDto.setKey3(key3);
                    cnJsonDetailedSplitDto.setKey4(key4);
                    cnJsonDetailedSplitDto.setKey5(key5);
                    cnJsonDetailedSplitDto.setKey6(key6);
                    cnJsonDetailedSplitDto.setKey7(key7);
                    cnJsonDetailedSplitDto.setLookupKey(lookupKey);
                    cnJsonDetailedSplitDto.setValue(cn);

                    engJsonDetailedSplitList.add(engJsonDetailedSplitDto);
                    hkJsonDetailedSplitList.add(hkJsonDetailedSplitDto);
                    cnJsonDetailedSplitList.add(cnJsonDetailedSplitDto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        splitMultilingualFilesDto.setEngJsonDetailedSplitList(engJsonDetailedSplitList);
        splitMultilingualFilesDto.setHkJsonDetailedSplitList(hkJsonDetailedSplitList);
        splitMultilingualFilesDto.setCnJsonDetailedSplitList(cnJsonDetailedSplitList);
        return splitMultilingualFilesDto;
    }

    private static void writeExcel(List<JsonDetailedSplitDto> list, String fileName) {
        List<JsonDetailedSplitDto> jsonDetailedSplitDtos = new ArrayList<>();
        int max = 0;
        for (JsonDetailedSplitDto simpleMergeDto : list) {
            if (StringUtils.isNotBlank(simpleMergeDto.getLookupKey())) {
                List<String> keyList = Arrays.asList(simpleMergeDto.getLookupKey().split("\\."));
                if (max < keyList.size()) {
                    max = keyList.size();
                }
                HashMap<String, String> keyMap = new HashMap<String, String>();
                for (int i = 0; i < keyList.size(); i++) {
                    String fullKey = keyList.get(i);
                    if (fullKey.contains("||")) fullKey = fullKey.replace("||", ".");
                    keyMap.put("key" + (i + 1), fullKey);
                }
                JsonDetailedSplitDto jsonDetailedSplitDto = JSON.parseObject(JSON.toJSONString(keyMap), JsonDetailedSplitDto.class);
                jsonDetailedSplitDto.setLookupKey(simpleMergeDto.getLookupKey().replace("||", "."));
                jsonDetailedSplitDto.setValue(simpleMergeDto.getValue());
                jsonDetailedSplitDtos.add(jsonDetailedSplitDto);
            }
        }
        System.out.println("Maximum key level: " + max);
        EasyExcel.write(fileName, JsonDetailedSplitDto.class)
                .sheet("data")
                .doWrite(jsonDetailedSplitDtos);
    }
}
