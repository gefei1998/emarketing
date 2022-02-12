package com.xjt.service;

import com.xjt.entity.dictionary.SearchDictionaryRequestEntity;
import com.xjt.entity.dictionary.SearchDictionaryResponseEntity;

import java.util.List;

public interface IDictionaryService {

    List<SearchDictionaryResponseEntity> searchDictionary();

    void updateDictionary(SearchDictionaryRequestEntity searchDictionaryRequestEntity);
}
