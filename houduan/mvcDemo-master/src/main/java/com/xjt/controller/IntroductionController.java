package com.xjt.controller;

import com.xjt.common.Request;
import com.xjt.common.Result;
import com.xjt.entity.introdution.SearchIntroductionResponseEntity;
import com.xjt.entity.introdution.UpdateIntroductionRequestEntity;
import com.xjt.service.IIntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/introduce")
public class IntroductionController {
    @Autowired
    private IIntroductionService introductionService;

    //更新简介
    @RequestMapping("/update")
    @ResponseBody
    @CrossOrigin
    public void updateIntroduction( @RequestBody Request<UpdateIntroductionRequestEntity> request) throws Exception {
        introductionService.updateIntroduction(request.getData());
    }

    //获取简介内容
    @RequestMapping("/search")
    @ResponseBody
    @CrossOrigin
    public Result searchIntroduction() throws Exception {
        Result result = new Result();
        SearchIntroductionResponseEntity searchIntroductionResponseEntity =  introductionService.searchIntroduction();
        result.setData(searchIntroductionResponseEntity);
        return result;
    }
}
