package com.xjt.controller;

import com.xjt.common.Request;
import com.xjt.common.Result;
import com.xjt.entity.recommend.AddRecommendRequest;
import com.xjt.entity.recommend.SearchRecommendResponse;
import com.xjt.service.IRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/recommend")
public class RecommendController {
    @Autowired
    private IRecommendService recommendService;

    //新增推荐活动、推荐商品
    @RequestMapping("/update")
    @ResponseBody
    @CrossOrigin
    public void add( @RequestBody Request<AddRecommendRequest> request) throws Exception {
        recommendService.update(request.getData());
    }

    //查询推荐
    @RequestMapping("/search")
    @ResponseBody
    @CrossOrigin
    public Result search() throws Exception {
        Result result = new Result();
        SearchRecommendResponse searchOrderResponses = recommendService.search();
        result.setData(searchOrderResponses);
        return result;
    }
}
