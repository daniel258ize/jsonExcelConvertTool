package com.liangsl;

import com.liangsl.util.ExcelToJs;
import com.liangsl.util.JsToExcelSplitKey;


public class TestMaster {
    public static void main(String[] args) {
        String filePath41 = "D:\\PCCW_WINNIE\\work\\2023-12-21\\source\\v5\\";
//        String fileName21 = "en 1.js";
//        JsToExcelSplitKey.excute(filePath41, fileName21, filePath41);

        /*
         * Snippets:Convert excel to i18n js
         */

        String fileName41 = "en 1_js1704945100264.xlsx";
        ExcelToJs.excute(filePath41, fileName41, filePath41);


    }
}
