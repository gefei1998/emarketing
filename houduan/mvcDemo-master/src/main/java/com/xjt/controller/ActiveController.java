package com.xjt.controller;

import com.xjt.common.Request;
import com.xjt.common.Result;
import com.xjt.entity.active.AddActiveEntity;
import com.xjt.entity.active.SearchActiveRequestEntity;
import com.xjt.entity.active.SearchActiveResponseEntity;
import com.xjt.service.IActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/active")
public class ActiveController {

    @Autowired
    private IActiveService activeService;

    //添加活动
    @RequestMapping("/add")
    @ResponseBody
    @CrossOrigin
    public void addActive( @RequestBody Request<AddActiveEntity> request) throws Exception {
        activeService.addActive(request.getData());
    }

    //修改活动
    @RequestMapping("/update")
    @ResponseBody
    @CrossOrigin
    public void updateActive( @RequestBody Request<AddActiveEntity> request) throws Exception {
        activeService.updateActive(request.getData());
    }

    //查询活动
    @RequestMapping("/search")
    @ResponseBody
    @CrossOrigin
    public Result searchActive(@RequestBody Request<SearchActiveRequestEntity> request) throws Exception {
        Result result = new Result();
        List<SearchActiveResponseEntity> searchActiveResponseEntities = activeService.searchActive(request.getData());
        result.setData(searchActiveResponseEntities);
        return result;
    }
}
