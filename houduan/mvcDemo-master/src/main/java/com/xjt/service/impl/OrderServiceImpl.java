package com.xjt.service.impl;

import com.xjt.common.IdWorker;
import com.xjt.dao.IOrderDao;
import com.xjt.entity.order.*;
import com.xjt.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    @Resource
    private IOrderDao orderDao;

    @Override
    public void add(AddOrderRequest addOrderRequest) {
		//将交易额去掉小数部分
//        Integer integral = addOrderRequest.getMoney().setScale(0, BigDecimal.ROUND_HALF_DOWN).intValue();
//        addOrderRequest.setIntegral(integral);

        //保存订单基本信息并获取插入的订单id
        orderDao.addOrder(addOrderRequest);
        //orderId反映射到了request中
        Integer orderId = addOrderRequest.getOrderId();

        orderDao.addOrderItem(addOrderRequest.getGoodList(),orderId);

        //新增商品销量
        orderDao.addGoodNum(addOrderRequest.getGoodList());

        //新增客户积分
        orderDao.addIntegral(addOrderRequest);
    }

    @Override
    public List<SearchOrderResponse> search(SearchOrderRequest searchOrderRequest) {
        List<SearchOrderResponse> searchOrderResponseList = new ArrayList<>();
        if(searchOrderRequest.getPage()!=null&&searchOrderRequest.getPageNum()!=null){
            Integer pageStart = (searchOrderRequest.getPage()-1)*searchOrderRequest.getPageNum();
            searchOrderRequest.setPageStart(pageStart);
        }
        //获取订单基本信息
        searchOrderResponseList = orderDao.searchOrder(searchOrderRequest);
        //获取订单商品详细信息
        for (SearchOrderResponse searchOrderResponse : searchOrderResponseList) {
            List<SearchOrderGood> goodList = new ArrayList<>();
            goodList = orderDao.searchOrderItem(searchOrderResponse.getOrderId());
            searchOrderResponse.setGoodList(goodList);
        }
        return searchOrderResponseList;
    }

    @Override
    public GetOrderNumResponse getOrderNum() {
        GetOrderNumResponse getOrderNumResponse = new GetOrderNumResponse();
        IdWorker worker = new IdWorker(1,1,1);
        for (int i = 0; i < 1; i++) {
            //通过雪花算法生成的订单编号赋值给ordernum
            getOrderNumResponse.setOrderNum(worker.nextId());
        }
        return getOrderNumResponse;
    }

    @Override
    public void delete(AddOrderRequest addOrderRequest) {
        orderDao.deleteOrder(addOrderRequest);
        orderDao.deleteOrderItem(addOrderRequest);
    }
}
