package com.xjt.controller;

import com.xjt.common.Request;
import com.xjt.common.Result;
import com.xjt.entity.goods.*;
import com.xjt.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/api/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    //添加商品
    @RequestMapping("/add")
    @ResponseBody
    @CrossOrigin
    public void add( @RequestBody Request<AddRequestEntity> request) throws Exception {
        goodsService.add(request.getData());
    }

    //修改商品
    @RequestMapping("/update")
    @ResponseBody
    @CrossOrigin
    public void update( @RequestBody Request<UpdateGoodsRequestEntity> request) throws Exception {
        goodsService.update(request.getData());
    }

    //商品一栏查看/检索
    @RequestMapping("/search")
    @ResponseBody
    @CrossOrigin
    public Result search(@RequestBody Request<SearchGoodsRequestEntity> request) throws Exception {
        Result result = new Result();
        List<SearchGoodsResponseEntity> detailResponseEntity = goodsService.search(request.getData());
        result.setData(detailResponseEntity);
        return result;
    }

    //新增商家回复
    @RequestMapping("/addCommendReply")
    @ResponseBody
    @CrossOrigin
    public void addCommendReply( @RequestBody Request<AddCommendReplyRequest> request) throws Exception {
        goodsService.addCommendReply(request.getData());
    }

    //新增商品评论
    @RequestMapping("/addCommend")
    @ResponseBody
    @CrossOrigin
    public void addCommend( @RequestBody Request<AddCommendRequest> request) throws Exception {
        goodsService.addCommend(request.getData());
    }
}
