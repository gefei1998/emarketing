package com.xjt.controller;

import com.xjt.common.Request;
import com.xjt.common.Result;
import com.xjt.entity.dictionary.SearchDictionaryRequestEntity;
import com.xjt.entity.dictionary.SearchDictionaryResponseEntity;
import com.xjt.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/api/dictionary")
public class DictionaryController {
    @Autowired
    private IDictionaryService dictionaryService;

    //查询字典
    @RequestMapping("/search")
    @ResponseBody
    @CrossOrigin
    public Result searchDictionary() throws Exception {
        Result result = new Result();
        List<SearchDictionaryResponseEntity> searchActiveResponseEntity = dictionaryService.searchDictionary();
        result.setData(searchActiveResponseEntity);
        return result;
    }

    //修改字典
    @RequestMapping("/update")
    @ResponseBody
    @CrossOrigin
    public void updateDictionary(@RequestBody Request<SearchDictionaryRequestEntity> request) throws Exception {
        dictionaryService.updateDictionary(request.getData());
    }
}
