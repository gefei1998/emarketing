package com.xjt.dao;

import com.xjt.entity.order.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderDao {
    void addOrder(AddOrderRequest addOrderRequest);

    void addOrderItem(@Param("input")List<AddOrderGood> addOrderGoodList,@Param("orderId") Integer orderId);

    List<SearchOrderResponse> searchOrder(SearchOrderRequest searchOrderRequest);

    List<SearchOrderGood> searchOrderItem(@Param("orderId") Integer orderId);

    void addGoodNum(@Param("input") List<AddOrderGood> addOrderGoodList);

    void deleteOrder(AddOrderRequest addOrderRequest);

    void deleteOrderItem(AddOrderRequest addOrderRequest);

    void addIntegral(AddOrderRequest addOrderRequest);
}
