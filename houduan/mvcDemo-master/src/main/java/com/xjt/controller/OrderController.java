package com.xjt.controller;

import com.xjt.common.Request;
import com.xjt.common.Result;
import com.xjt.entity.order.AddOrderRequest;
import com.xjt.entity.order.GetOrderNumResponse;
import com.xjt.entity.order.SearchOrderRequest;
import com.xjt.entity.order.SearchOrderResponse;
import com.xjt.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    //新增订单
    @RequestMapping("/add")
    @ResponseBody
    @CrossOrigin
    public void add( @RequestBody Request<AddOrderRequest> request) throws Exception {
        orderService.add(request.getData());
    }

    //删除订单
    @RequestMapping("/delete")
    @ResponseBody
    @CrossOrigin
    public void delete( @RequestBody Request<AddOrderRequest> request) throws Exception {
        orderService.delete(request.getData());
    }

    //查询订单
    @RequestMapping("/search")
    @ResponseBody
    @CrossOrigin
    public Result search(@RequestBody Request<SearchOrderRequest> request) throws Exception {
        Result result = new Result();
        List<SearchOrderResponse> searchOrderResponses = orderService.search(request.getData());
        result.setData(searchOrderResponses);
        return result;
    }

    //生成订单号
    @RequestMapping("/getOrderNum")
    @ResponseBody
    @CrossOrigin
    public Result getOrderNum() throws Exception {
        Result result = new Result();
        GetOrderNumResponse getOrderNumResponse = orderService.getOrderNum();
        result.setData(getOrderNumResponse);
        return result;
    }
}