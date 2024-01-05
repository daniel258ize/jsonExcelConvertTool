package com.liangsl.dto;

import com.alibaba.excel.annotation.ExcelProperty;


public class SimpleMergeDto {
    @ExcelProperty("key")
    private String key;
    @ExcelProperty("value")
    private String value;

    @Override
    public String toString() {
        return "Obj{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SimpleMergeDto() {
    }

    public SimpleMergeDto(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
