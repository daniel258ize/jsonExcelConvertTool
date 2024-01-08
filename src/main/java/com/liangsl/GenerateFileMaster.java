package com.liangsl;

import com.liangsl.util.ExcelToJs;
import com.liangsl.util.ExcelToJson;
import com.liangsl.util.JsToExcelSplitKey;
import com.liangsl.util.JsonToExcelSplitKey;


public class GenerateFileMaster {
    public static void main(String[] args) {
        /*
         * Snippets: Convert i18n json to excel
         */
        String filePath11 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\ori\\ksk i18n 08012024\\self\\";
        String outputFilePath11 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\excel\\ksk i18n 08012024\\self\\";

        String fileName11 = "en-US.json";
        JsonToExcelSplitKey.excute(filePath11, fileName11, outputFilePath11);

        String fileName12 = "zh-CN.json";
        JsonToExcelSplitKey.excute(filePath11, fileName12, outputFilePath11);

        String fileName13 = "zh-HK.json";
        JsonToExcelSplitKey.excute(filePath11, fileName13, outputFilePath11);


        /* Snippets: Convert i18n js to excel
         *
         * importance:
         * If the keys in the js file contain single quotes, you need to remove the single quotes manually before executing the program
         * e.g.'er_name':'YOU_HAVA' -> er_name:'YOU_HAVA'
         */

        String filePath21 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\update\\";
        String outputFilePath21 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\update\\";

        String fileName21 = "en.js.txt";
        JsToExcelSplitKey.excute(filePath21, fileName21, outputFilePath21);

        String fileName22 = "zh_cn.js.txt";
        JsToExcelSplitKey.excute(filePath21, fileName22, outputFilePath21);

        String fileName23 = "zh_hk.js.txt";
        JsToExcelSplitKey.excute(filePath21, fileName23, outputFilePath21);

        /*
         *Snippets: Convert excel to i18n json
         */
        String filePath31 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\ori\\ksk i18n 08012024\\self\\";
        String outputFilePath31 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\ori\\ksk i18n 08012024\\self\\";

        String fileName31 = "WEB (st)_JW20231228  - cn.xlsx";
        ExcelToJson.excute(filePath31, fileName31, outputFilePath31);

        String fileName32 = "WEB (st)_JW20231228  - en.xlsx";
        ExcelToJson.excute(filePath31, fileName32, outputFilePath31);

        String fileName33 = "WEB (st)_JW20231228  - hk.xlsx";
        ExcelToJson.excute(filePath31, fileName33, outputFilePath31);


        /*
         *Snippets:Convert excel to i18n js
         */
        String filePath41 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\ori\\ksk i18n 08012024\\self\\";
        String outputFilePath41 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\ori\\ksk i18n 08012024\\self\\";

        String fileName41 = "en-us_json1704700901968.xlsx";
        ExcelToJs.excute(filePath41, fileName41, outputFilePath41);

        String fileName42 = "cn-us_json1704700901968.xlsx";
        ExcelToJs.excute(filePath41, fileName42, outputFilePath41);

        String fileName43 = "hk-us_json1704700901968.xlsx";
        ExcelToJs.excute(filePath41, fileName43, outputFilePath41);


    }
}
