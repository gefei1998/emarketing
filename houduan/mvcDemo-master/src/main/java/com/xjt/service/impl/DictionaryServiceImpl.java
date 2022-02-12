package com.xjt.service.impl;

import com.xjt.dao.IDictionaryDao;
import com.xjt.entity.dictionary.SearchDictionaryRequestEntity;
import com.xjt.entity.dictionary.SearchDictionaryResponseEntity;
import com.xjt.service.IDictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dictionaryService")
public class DictionaryServiceImpl implements IDictionaryService {
    @Resource
    private IDictionaryDao dictionaryDao;

    public List<SearchDictionaryResponseEntity> searchDictionary(){
        return dictionaryDao.searchDictionary();
    }

    @Override
    public void updateDictionary(SearchDictionaryRequestEntity searchDictionaryRequestEntity) {
        dictionaryDao.updateDictionary(searchDictionaryRequestEntity);
    }
}
