package com.liangsl.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.liangsl.dto.JsonDetailedSplitDto;
import com.liangsl.dto.SimpleMergeDto;
import org.apache.poi.util.StringUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsonToExcelSplitKey {
    public static void excute(String filePath, String fileName, String outputFilePath) {
        String json = readWithFileInputStream(filePath + fileName);
        JSONObject jsonObject = JSON.parseObject(json, Feature.OrderedField);
        List<SimpleMergeDto> data = new ArrayList<>();
        getData(data, new StringBuilder(), jsonObject);
        System.out.println("Total count:" + data.size());

        String exportFileName = outputFilePath + fileName.replace(".", "_") + System.currentTimeMillis() + ".xlsx";
        writeExcel(data, exportFileName);
    }

    private static void getData(List<SimpleMergeDto> list, StringBuilder key, JSONObject jsonObj) {
        StringBuilder k = new StringBuilder();
        for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {
            String fullKey = entry.getKey();
            if(org.apache.commons.lang3.StringUtils.endsWith(fullKey, ".")){
                fullKey = org.apache.commons.lang3.StringUtils.removeEnd(fullKey, ".");
                fullKey = fullKey+"||";
            }
            if ("".equals(key.toString())) {
                k.append(fullKey);
            } else {
                k = new StringBuilder(key);
                k.append(".").append(fullKey);
            }
            Object o = entry.getValue();
            if (o instanceof JSONObject) {
                getData(list, k, (JSONObject) o);
            } else {
                list.add(new SimpleMergeDto(k.toString(), entry.getValue().toString()));
            }
            k = new StringBuilder();
        }
    }

    private static void writeExcel(List<SimpleMergeDto> list, String fileName) {
        List<JsonDetailedSplitDto> jsonDetailedSplitDtos = new ArrayList<>();
        int max = 0;
        for (SimpleMergeDto simpleMergeDto : list) {
            if (StringUtils.isNotBlank(simpleMergeDto.getKey())) {
                List<String> keyList = Arrays.asList(simpleMergeDto.getKey().split("\\."));
                if (max < keyList.size()) {
                    max = keyList.size();
                }
                HashMap<String, String> keyMap = new HashMap<String, String>();
                for (int i = 0; i < keyList.size(); i++) {
                    String fullKey = keyList.get(i);
                    if(fullKey.contains("||")) fullKey = fullKey.replace("||", ".");
                    keyMap.put("key" + (i + 1), fullKey);
                }
                JsonDetailedSplitDto jsonDetailedSplitDto = JSON.parseObject(JSON.toJSONString(keyMap), JsonDetailedSplitDto.class);
                jsonDetailedSplitDto.setLookupKey(simpleMergeDto.getKey().replace("||", "."));
                jsonDetailedSplitDto.setValue(simpleMergeDto.getValue());
                jsonDetailedSplitDtos.add(jsonDetailedSplitDto);
            }
        }
        System.out.println("Maximum key level: " + max);
        EasyExcel.write(fileName, JsonDetailedSplitDto.class)
                .sheet("data")
                .doWrite(jsonDetailedSplitDtos);
    }

    private static String readWithFileInputStream(String fileName) {
        String jsonString;
        StringBuilder sb = new StringBuilder();
        try {
            InputStream input = new FileInputStream(fileName);
            Reader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString = sb.toString();
    }


}
