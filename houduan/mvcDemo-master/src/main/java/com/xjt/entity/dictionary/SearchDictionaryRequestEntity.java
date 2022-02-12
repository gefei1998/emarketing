package com.xjt.entity.dictionary;

import java.math.BigDecimal;

public class SearchDictionaryRequestEntity {
    private String dictionaryName;

    private BigDecimal dictionaryValue;

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public BigDecimal getDictionaryValue() {
        return dictionaryValue;
    }

    public void setDictionaryValue(BigDecimal dictionaryValue) {
        this.dictionaryValue = dictionaryValue;
    }
}
