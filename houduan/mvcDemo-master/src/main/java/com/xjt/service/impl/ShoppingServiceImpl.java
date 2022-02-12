package com.xjt.service.impl;

import com.xjt.dao.IShoppingDao;
import com.xjt.entity.shopping.AddShoppingRequest;
import com.xjt.entity.shopping.SearchShoppingRequest;
import com.xjt.entity.shopping.SearchShoppingResponse;
import com.xjt.service.IShoppingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("shoppingService")
public class ShoppingServiceImpl implements IShoppingService {
    @Resource
    private IShoppingDao shoppingDao;

    @Override
    public void add(AddShoppingRequest addShoppingRequest) {
        int num = shoppingDao.searchNum(addShoppingRequest);
        //如果当前用户购物车中没有这个商品，则新增；否则就将商品的数量加1
        if(num==0){
            shoppingDao.addShopping(addShoppingRequest);
        }else{
            shoppingDao.updateShopping(addShoppingRequest);
        }
    }

    @Override
    public List<SearchShoppingResponse> search(SearchShoppingRequest searchShoppingRequest){
        if(searchShoppingRequest.getPage()!=null&&searchShoppingRequest.getPageNum()!=null){
            Integer pageStart = (searchShoppingRequest.getPage()-1)*searchShoppingRequest.getPageNum();
            searchShoppingRequest.setPageStart(pageStart);
        }
        return shoppingDao.search(searchShoppingRequest);
    }

    @Override
    public void update(AddShoppingRequest addShoppingRequest) {
        shoppingDao.update(addShoppingRequest);
    }

    @Override
    public void delete(AddShoppingRequest addShoppingRequest) {
        shoppingDao.delete(addShoppingRequest);
    }
}
