package com.liangsl.dto;

import com.alibaba.excel.annotation.ExcelProperty;


public class MultilingualFileDto {
    @ExcelProperty("key1")
    private String key1;
    @ExcelProperty("key2")
    private String key2;
    @ExcelProperty("key3")
    private String key3;
    @ExcelProperty("key4")
    private String key4;
    @ExcelProperty("key5")
    private String key5;
    @ExcelProperty("key6")
    private String key6;
    @ExcelProperty("key7")
    private String key7;
    @ExcelProperty("lookupKey")
    private String lookupKey;
    @ExcelProperty("eng")
    private String eng;
    @ExcelProperty("cn")
    private String cn;
    @ExcelProperty("hk")
    private String hk;

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }

    public String getKey3() {
        return key3;
    }

    public String getKey4() {
        return key4;
    }

    public String getKey5() {
        return key5;
    }

    public String getKey6() {
        return key6;
    }

    public String getKey7() {
        return key7;
    }

    public String getLookupKey() {
        return lookupKey;
    }

    public String getEng() {
        return eng;
    }

    public String getCn() {
        return cn;
    }

    public String getHk() {
        return hk;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public void setKey4(String key4) {
        this.key4 = key4;
    }

    public void setKey5(String key5) {
        this.key5 = key5;
    }

    public void setKey6(String key6) {
        this.key6 = key6;
    }

    public void setKey7(String key7) {
        this.key7 = key7;
    }

    public void setLookupKey(String lookupKey) {
        this.lookupKey = lookupKey;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public void setHk(String hk) {
        this.hk = hk;
    }
}
