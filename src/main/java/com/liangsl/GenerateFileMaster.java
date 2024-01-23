package com.liangsl;

import com.liangsl.util.*;


public class GenerateFileMaster {
    public static void main(String[] args) {
        /*
         * Snippets: Convert i18n json to excel
         */
//        String filePath11 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\ori\\web i18n 0901\\";
//        String outputFilePath11 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\excel\\web i18n 0901\\";
//
//        String fileName11 = "en-us.json";
//        JsonToExcelSplitKey.excute(filePath11, fileName11, outputFilePath11);
//
//        String fileName12 = "zh-cn.json";
//        JsonToExcelSplitKey.excute(filePath11, fileName12, outputFilePath11);
//
//        String fileName13 = "zh-hk.json";
//        JsonToExcelSplitKey.excute(filePath11, fileName13, outputFilePath11);


        /* Snippets: Convert i18n js to excel
         *
         * importance:
         * If the keys in the js file contain single quotes, you need to remove the single quotes manually before executing the program
         * e.g.'er_name':'YOU_HAVA' -> er_name:'YOU_HAVA'
         */

//        String filePath21 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\ori\\app i18n 0901\\";
//        String outputFilePath21 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\excel\\app i18n 0901\\";
//
//        String fileName21 = "en.js";
//        JsToExcelSplitKey.excute(filePath21, fileName21, outputFilePath21);
////
//        String fileName22 = "zh_cn.js";
//        JsToExcelSplitKey.excute(filePath21, fileName22, outputFilePath21);
//
//        String fileName23 = "zh_hk.js";
//        JsToExcelSplitKey.excute(filePath21, fileName23, outputFilePath21);

        /*
         * Snippets: Convert excel to i18n json
         */
//        String filePath31 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\ori\\ksk i18n 08012024\\self\\";
//        String outputFilePath31 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\ori\\ksk i18n 08012024\\self\\";
//
//        String fileName31 = "WEB (st)_JW20231228  - cn.xlsx";
//        ExcelToJson.excute(filePath31, fileName31, outputFilePath31);
//
//        String fileName32 = "WEB (st)_JW20231228  - en.xlsx";
//        ExcelToJson.excute(filePath31, fileName32, outputFilePath31);
//
//        String fileName33 = "WEB (st)_JW20231228  - hk.xlsx";
//        ExcelToJson.excute(filePath31, fileName33, outputFilePath31);


        /*
         * Snippets:Convert excel to i18n js
         */
        String filePath41 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\excel\\app i18n 0901\\";
        String outputFilePath41 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\target\\app i18n 0901\\";

        String fileName41 = "en_js1704789448583.xlsx";
        ExcelToJs.excute(filePath41, fileName41, outputFilePath41);

//        String fileName42 = "cn-us_json1704700901968.xlsx";
//        ExcelToJs.excute(filePath41, fileName42, outputFilePath41);
//
//        String fileName43 = "hk-us_json1704700901968.xlsx";
//        ExcelToJs.excute(filePath41, fileName43, outputFilePath41);

        String filePath = "D:\\PCCW_WINNIE\\work\\2023-12-21(json-excel-tool)\\source\\v14\\";
        String outputFilePath = "D:\\PCCW_WINNIE\\work\\2023-12-21(json-excel-tool)\\source\\v14\\";

        String fileName = "api i18n 22012024.xlsx";
        SplitMultilingualFiles.excute(filePath, fileName, outputFilePath);
    }
}
