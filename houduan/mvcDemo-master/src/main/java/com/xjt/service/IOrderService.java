package com.xjt.service;

import com.xjt.entity.order.AddOrderRequest;
import com.xjt.entity.order.GetOrderNumResponse;
import com.xjt.entity.order.SearchOrderRequest;
import com.xjt.entity.order.SearchOrderResponse;

import java.util.List;

public interface IOrderService {

    void add(AddOrderRequest addOrderRequest);

    List<SearchOrderResponse> search(SearchOrderRequest searchOrderRequest);

    GetOrderNumResponse getOrderNum();

    void delete(AddOrderRequest addOrderRequest);
}
