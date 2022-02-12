package com.xjt.entity.dictionary;

import java.math.BigDecimal;

public class SearchDictionaryResponseEntity {

    private BigDecimal dictionaryValue;

    private String dictionaryName;

    private String dictionaryDesc;

    public BigDecimal getDictionaryValue() {
        return dictionaryValue;
    }

    public void setDictionaryValue(BigDecimal dictionaryValue) {
        this.dictionaryValue = dictionaryValue;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getDictionaryDesc() {
        return dictionaryDesc;
    }

    public void setDictionaryDesc(String dictionaryDesc) {
        this.dictionaryDesc = dictionaryDesc;
    }
}
