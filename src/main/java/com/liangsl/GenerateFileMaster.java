package com.liangsl;

import com.liangsl.util.ExcelToJs;
import com.liangsl.util.ExcelToJson;
import com.liangsl.util.JsToExcelSplitKey;
import com.liangsl.util.JsonToExcelSplitKey;

/**
 * @ClassName test
 * @Description TODO
 * @Author LSL
 * @Date 2023/12/23 23:28
 * @Version 1.0
 * @Email lsl.yx@foxmail.com
 **/
public class GenerateFileMaster {
    public static void main(String[] args) {
        /*
         * Convert i18n json to excel
         */
        String filePath11 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\ori\\ksk i18n 08012024\\self\\";
        String targetFilePath11 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool v1.1\\src\\main\\resources\\excel\\ksk i18n 08012024\\self\\";

        String fileName11 = "en-US.json";
        JsonToExcelSplitKey.excute(filePath11, fileName11, targetFilePath11);

        String fileName12 = "zh-CN.json";
        JsonToExcelSplitKey.excute(filePath11, fileName12, targetFilePath11);

        String fileName13 = "zh-HK.json";
        JsonToExcelSplitKey.excute(filePath11, fileName13, targetFilePath11);

        /*
         * Convert i18n js to excel
         */
//        String filePath21 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\update\\";
//        String fileName21 = "en.js.txt";
//        JsToExcelSplitKey.excute(filePath21,fileName21);
//
//        String fileName22 = "zh_cn.js.txt";
//        JsToExcelSplitKey.excute(filePath21,fileName22);
//
//        String fileName23 = "zh_hk.js.txt";
//        JsToExcelSplitKey.excute(filePath21,fileName23);

        /*
         * Convert excel to i18n json
         */
//        String pathName31 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\ori\\WEB (st)_JW20231228 cn.xlsx";
//        String fileName31 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\target\\zh-cn_converted.json";
//        ExcelToJson.excute(pathName31,fileName31);
//
//        String pathName32 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\ori\\WEB (st)_JW20231228 en.xlsx";
//        String fileName32 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\target\\en-us_converted.json";
//        ExcelToJson.excute(pathName32,fileName32);
//
//        String pathName33 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\ori\\WEB (st)_JW20231228 hk.xlsx";
//        String fileName33 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\target\\zh-hk_converted.json";
//        ExcelToJson.excute(pathName33,fileName33);
//
//
//        /*
//         * Convert excel to i18n js
//         */
//        String pathName41 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - en.xlsx";
//        String fileName41 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - en.js";
//        ExcelToJs.excute(pathName41,fileName41);
//
//        String pathName42 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - hk.xlsx";
//        String fileName42 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - hk.js";
//        ExcelToJs.excute(pathName42,fileName42);
//
//        String pathName43 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - cn.xlsx";
//        String fileName43 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - cn.js";
//        ExcelToJs.excute(pathName43,fileName43);
    }
}
