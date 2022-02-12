package com.xjt.controller;

import com.xjt.common.Request;
import com.xjt.common.Result;
import com.xjt.entity.shopping.AddShoppingRequest;
import com.xjt.entity.shopping.SearchShoppingRequest;
import com.xjt.entity.shopping.SearchShoppingResponse;
import com.xjt.service.IShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/shopping")
public class ShoppingController {

    @Autowired
    private IShoppingService shoppingService;

    //新增购物车
    @RequestMapping("/add")
    @ResponseBody
    @CrossOrigin
    public void add( @RequestBody Request<AddShoppingRequest> request) throws Exception {
        shoppingService.add(request.getData());
    }

    //修改购物车
    @RequestMapping("/update")
    @ResponseBody
    @CrossOrigin
    public void update( @RequestBody Request<AddShoppingRequest> request) throws Exception {
        shoppingService.update(request.getData());
    }

    //删除购物车
    @RequestMapping("/delete")
    @ResponseBody
    @CrossOrigin
    public void delete( @RequestBody Request<AddShoppingRequest> request) throws Exception {
        shoppingService.delete(request.getData());
    }

    //查询购物车内容
    @RequestMapping("/search")
    @ResponseBody
    @CrossOrigin
    public Result search(@RequestBody Request<SearchShoppingRequest> request) throws Exception {
        Result result = new Result();
        List<SearchShoppingResponse> searchShoppingResponses = shoppingService.search(request.getData());
        result.setData(searchShoppingResponses);
        return result;
    }
}
