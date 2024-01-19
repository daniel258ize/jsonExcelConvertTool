package com.liangsl.dto;

import java.util.ArrayList;
import java.util.List;

public class SplitMultilingualFilesDto {
    List<JsonDetailedSplitDto> engJsonDetailedSplitList;
    List<JsonDetailedSplitDto> hkJsonDetailedSplitList;
    List<JsonDetailedSplitDto> cnJsonDetailedSplitList;

    public List<JsonDetailedSplitDto> getEngJsonDetailedSplitList() {
        return engJsonDetailedSplitList;
    }

    public List<JsonDetailedSplitDto> getHkJsonDetailedSplitList() {
        return hkJsonDetailedSplitList;
    }

    public List<JsonDetailedSplitDto> getCnJsonDetailedSplitList() {
        return cnJsonDetailedSplitList;
    }

    public void setEngJsonDetailedSplitList(List<JsonDetailedSplitDto> engJsonDetailedSplitList) {
        this.engJsonDetailedSplitList = engJsonDetailedSplitList;
    }

    public void setHkJsonDetailedSplitList(List<JsonDetailedSplitDto> hkJsonDetailedSplitList) {
        this.hkJsonDetailedSplitList = hkJsonDetailedSplitList;
    }

    public void setCnJsonDetailedSplitList(List<JsonDetailedSplitDto> cnJsonDetailedSplitList) {
        this.cnJsonDetailedSplitList = cnJsonDetailedSplitList;
    }

    @Override
    public String toString() {
        return "SplitMultilingualFilesDto{" +
                "engJsonDetailedSplitList=" + engJsonDetailedSplitList +
                ", hkJsonDetailedSplitList=" + hkJsonDetailedSplitList +
                ", cnJsonDetailedSplitList=" + cnJsonDetailedSplitList +
                '}';
    }
}
