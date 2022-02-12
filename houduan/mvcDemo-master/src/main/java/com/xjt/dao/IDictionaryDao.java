package com.xjt.dao;

import com.xjt.entity.dictionary.SearchDictionaryRequestEntity;
import com.xjt.entity.dictionary.SearchDictionaryResponseEntity;

import java.util.List;

public interface IDictionaryDao {
    List<SearchDictionaryResponseEntity> searchDictionary();

    void updateDictionary(SearchDictionaryRequestEntity searchDictionaryRequestEntity);
}
