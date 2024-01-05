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
        JsonToExcelSplitKey jsonToExcelSplitKey = new JsonToExcelSplitKey();
        JsToExcelSplitKey jsToExcelSplitKey = new JsToExcelSplitKey();
        ExcelToJs excelToJs = new ExcelToJs();
        ExcelToJson excelToJson = new ExcelToJson();
        /*String filePath = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\update\\";
        String fileName11 = "en-us_converted.json";
        jsonToExcelSplitKey.excute(filePath, fileName11);
        String fileName12 = "zh-cn.json";
        jsonToExcelSplitKey.excute(filePath, fileName12);
        String fileName13 = "zh-hk.json";
        jsonToExcelSplitKey.excute(filePath, fileName13);
        */

        /*String filePath = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\update\\";
        JsToExcelSplitKey jsToExcelSplitKey = new JsToExcelSplitKey();
        String fileName21 = "en.js.txt";
        jsToExcelSplitKey.excute(filePath,fileName21);
        String fileName22 = "zh_cn.js.txt";
        jsToExcelSplitKey.excute(filePath,fileName22);
        String fileName23 = "zh_hk.js.txt";
        jsToExcelSplitKey.excute(filePath,fileName23);*/

        /*String filePath = "D:\\PCCW_WINNIE\\工作\\2023-12-27\\ksk i18n\\edu ksk\\i18n\\locales\\";
        String fileName11 = "en-US.json";
        jsonToExcelSplitKey.excute(filePath, fileName11);
        String fileName12 = "zh-CN.json";
        jsonToExcelSplitKey.excute(filePath, fileName12);
        String fileName13 = "zh-HK.json";
        jsonToExcelSplitKey.excute(filePath, fileName13);*/

        /*String filePath = "D:\\PCCW_WINNIE\\工作\\2023-12-27\\ksk i18n\\edu ksk\\i18n\\locales\\common\\";
        String fileName11 = "en-US.json";
        jsonToExcelSplitKey.excute(filePath, fileName11);
        String fileName12 = "zh-CN.json";
        jsonToExcelSplitKey.excute(filePath, fileName12);
        String fileName13 = "zh-HK.json";
        jsonToExcelSplitKey.excute(filePath, fileName13);*/
        
        /*String filePath = "D:\\PCCW_WINNIE\\工作\\2023-12-27\\ksk i18n\\self ksk\\i18n\\locales\\";
        String fileName11 = "en-US.json";
        jsonToExcelSplitKey.excute(filePath, fileName11);
        String fileName12 = "zh-CN.json";
        jsonToExcelSplitKey.excute(filePath, fileName12);
        String fileName13 = "zh-HK.json";
        jsonToExcelSplitKey.excute(filePath, fileName13);*/

//        String filePath = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\ori\\";
//        String fileName11 = "en-US.json";
//        jsonToExcelSplitKey.excute(filePath, fileName11);
//        String fileName12 = "zh-CN.json";
//        jsonToExcelSplitKey.excute(filePath, fileName12);
//        String fileName13 = "zh-HK.json";
//        jsonToExcelSplitKey.excute(filePath, fileName13);

        /*String filePath = "F:\\min_winnie\\source\\";
        String fileName11 = "en-US.json";
        jsonToExcelSplitKey.excute(filePath, fileName11);*/

        /*String filePath = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\update\\";
        String fileName11 = "en.js.txt";
        jsToExcelSplitKey.excute(filePath, fileName11);
*/
        /*String pathName = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - ch.xlsx";
        String fileName = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - ch.txt";
        excelToJs.excute(pathName, fileName);*/

        /*String pathName2 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - en.xlsx";
        String fileName2 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - en.txt";
        excelToJs.excute(pathName2, fileName2);*/

        /*String pathName3 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - hk.xlsx";
        String fileName3 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - hk.txt";
        excelToJs.excute(pathName3, fileName3);*/

        /*String filePath = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\update\\";
        String fileName11 = "en-us_converted.json";
        jsonToExcelSplitKey.excute(filePath, fileName11);*/

       /* String pathName = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\WEB (st)_JW20231228  - cn.xlsx";
        String fileName = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\WEB (st)_JW20231228  - cn.txt";
        excelToJson.excute(pathName, fileName);
        String pathName2 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\WEB (st)_JW20231228  - en.xlsx";
        String fileName2 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\WEB (st)_JW20231228  - en.txt";
        excelToJson.excute(pathName2, fileName2);
        String pathName3 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\WEB (st)_JW20231228  - hk.xlsx";
        String fileName3 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\WEB (st)_JW20231228  - hk.txt";
        excelToJson.excute(pathName3, fileName3);*/

        /*String filePath = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\update\\";
        String fileName21 = "en.js.txt";
        jsToExcelSplitKey.excute(filePath,fileName21);*/

        /*String pathName1 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - en.xlsx";
        String fileName1 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - en.js";
        excelToJs.excute(pathName1,fileName1);*/

        /*String pathName2 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - hk.xlsx";
        String fileName2 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - hk.js";
        excelToJs.excute(pathName2,fileName2);

        String pathName3 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - cn.xlsx";
        String fileName3 = "D:\\PCCW_WINNIE\\工作\\2023-12-21\\source\\excel\\2024-1-2file\\APP (st)_JW20231229 - cn.js";
        excelToJs.excute(pathName3,fileName3);*/

//        String pathName1 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\ori\\WEB (st)_JW20231228 cn.xlsx";
//        String fileName1 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\target\\zh-cn_converted.json";
//        excelToJson.excute(pathName1,fileName1);

        String pathName2 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\ori\\WEB (st)_JW20231228 en.xlsx";
        String fileName2 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\target\\en-us_converted.json";
        excelToJson.excute(pathName2,fileName2);

//        String pathName3 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\ori\\WEB (st)_JW20231228 hk.xlsx";
//        String fileName3 = "C:\\Users\\XCL00321\\Downloads\\file_conversion_tool\\src\\main\\resources\\target\\zh-hk_converted.json";
//        excelToJson.excute(pathName3,fileName3);
    }
}
