package com.liangsl.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.liangsl.dto.JsDetailedSplitDto;
import com.liangsl.dto.SimpleMergeDto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Convert i18n js to excel
 * importance:
 * If the keys in the js file contain single quotes, you need to remove the single quotes manually before executing the program
 * e.g.'er_name':'YOU_HAVA' -> er_name:'YOU_HAVA'
 */
public class JsToExcelSplitKey {
    /**
     * @param filePath       js file path
     * @param fileName       js file name
     * @param outputFilePath The path to the generated excel
     */
    public static void excute(String filePath, String fileName, String outputFilePath) {
        String json = readWithFileInputStream(filePath + fileName);
        System.out.println(json);
        List<SimpleMergeDto> dataList = getData(json);
        System.out.println("Total count:" + dataList.size());
        String exportFileName = outputFilePath + fileName.replace(".", "_") + System.currentTimeMillis() + ".xlsx";
        writeExcel(dataList, exportFileName);
    }

    private static List<SimpleMergeDto> getData(String json) {
        List<SimpleMergeDto> data = new ArrayList<>();
        Stack<String> keyStack = new Stack<>();
        StringBuilder word = new StringBuilder();

        //给第一个{花括号前加上:冒号
        for (int i = 0; i < json.length(); i++) {
            if (json.charAt(i) == '{') {
                StringBuilder sb = new StringBuilder(json);
                sb.insert(i, ':');
                json = sb.toString();
                break;
            }
        }

        boolean isVal = false;
        boolean singleFlag = false;//value中单引号的标志
        boolean doubleFlag = false;//value中双引号的标志
        boolean psFlag = false;//注释行的标志
        boolean colonFlag = false;//冒号的标志
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);//当前字符
            char preC = '9';//当前字符的前一个字符
            if (i > 0) {
                preC = json.charAt(i - 1);//当前字符的前一个字符
            }

            //处理注释行//
            if (!isVal && c == '/' && json.charAt(i + 1) == '/') {
                psFlag = true;
                continue;
            }

            if (psFlag) {
                if (c == '\t') {
                    if (preC != ' ' && preC != '/' && preC != '\t') {
                        psFlag = false;
                    }
                }
                continue;
            }
            //没到冒号，不是值，遇到单引号(目的：处理key中包含单引号问题)
            if (!colonFlag && !isVal && c == '\'') {
                continue;
            }

            // 非值且是 : ,证明是key结束，入栈，且重置单词
            if (!isVal && c == ':') {
                String wordStr = word.toString();
                if (wordStr.startsWith(" ")) {
                    wordStr = wordStr.substring(1);
                }
                if (wordStr.startsWith("// ")) {
                    wordStr = wordStr.substring(3);
                }
                keyStack.push(wordStr);
                word = new StringBuilder();
                colonFlag = true;
                continue;
            }
            // 非值且是单引号，则是值的开始，修改标识 ,如果是\`符号开始，新增标志
            else if (colonFlag && !isVal && (c == '\'' || c == '`' || c == '"')) {
                isVal = true;
                word = new StringBuilder();
                singleFlag = c == '`';
                doubleFlag = c == '"';
                continue;
            }
            // 是值且是分号，则是值的结束，
            else if (colonFlag && isVal
                    && ((!singleFlag && !doubleFlag && c == '\'' && preC != '\\')
                    || (singleFlag && c == '`')
                    || (doubleFlag && c == '"'))) {
                String key = getKey(keyStack);
                String wordStr = word.toString();
                data.add(new SimpleMergeDto(key, wordStr));
                if (!keyStack.isEmpty()) {
                    keyStack.pop();
                }
                isVal = false;
                singleFlag = false;
                doubleFlag = false;
                colonFlag = false;
                word = new StringBuilder();
                continue;
            }
            if (!isVal && c == '}') {
                if (!keyStack.isEmpty()) {
                    keyStack.pop();
                }
                continue;
            }
            if (!isVal && (c == ',' || c == '{' || c == '\r' || c == '\n' || c == '\t')) {
                continue;
            }
            word.append(c);
        }


        return data;
    }

    private static String getKey(Stack<String> keyStack) {
        if (keyStack.isEmpty()) {
            return "";
        }
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < keyStack.size(); i++) {
            if (i == 0) {
                key.append(keyStack.get(i));
            } else {
                key.append(".").append(keyStack.get(i));
            }
        }
        return key.toString();
    }

    public static void writeExcel(List<SimpleMergeDto> list, String fileName) {
        ArrayList<JsDetailedSplitDto> jsDetailedSplitDtos = new ArrayList<>();
        int max = 0;
        for (SimpleMergeDto dto : list) {
            if (StringUtils.isNotBlank(dto.getKey())) {
                List<String> keyList = Arrays.asList(dto.getKey().split("\\."));
                HashMap<String, String> keyMap = new HashMap<>();
                for (int i = 0; i < keyList.size(); i++) {
                    keyMap.put("key" + (i + 1), keyList.get(i));
                }
                JsDetailedSplitDto jsDetailedSplitDto = JSON.parseObject(JSON.toJSONString(keyMap), JsDetailedSplitDto.class);
                jsDetailedSplitDto.setLookupKey(dto.getKey());
                jsDetailedSplitDto.setValue(dto.getValue());
                jsDetailedSplitDtos.add(jsDetailedSplitDto);
                if (max < keyList.size()) {
                    max = keyList.size();
                }
            }
        }
        System.out.println("Maximum key level：" + max);
        EasyExcel.write(fileName, JsDetailedSplitDto.class)
                .sheet("data")
                .doWrite(jsDetailedSplitDtos);
    }

    public static String readWithFileInputStream(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream input = new FileInputStream(fileName);
            Reader isr = new InputStreamReader(input, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
