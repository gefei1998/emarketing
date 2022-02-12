package com.xjt.controller;

import com.xjt.common.Request;
import com.xjt.common.Result;
import com.xjt.entity.financial.SearchFinancialRequestEntity;
import com.xjt.entity.financial.SearchFinancialResponseEntity;
import com.xjt.service.IFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/financial")
public class FinancialController {
    @Autowired
    private IFinancialService financialService;

    //财务报表
    @RequestMapping("/search")
    @ResponseBody
    @CrossOrigin
    public Result searchFinancial(@RequestBody Request<SearchFinancialRequestEntity> request) throws Exception {
        Result result = new Result();
        SearchFinancialResponseEntity searchFinancialResponseEntitie = financialService.searchFinancial(request.getData());
        result.setData(searchFinancialResponseEntitie);
        return result;
    }
}
