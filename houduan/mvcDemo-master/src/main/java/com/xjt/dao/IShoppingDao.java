package com.xjt.dao;

import com.xjt.entity.shopping.AddShoppingRequest;
import com.xjt.entity.shopping.SearchShoppingRequest;
import com.xjt.entity.shopping.SearchShoppingResponse;

import java.util.List;

public interface IShoppingDao {
    int searchNum(AddShoppingRequest addShoppingRequest);

    void addShopping(AddShoppingRequest addShoppingRequest);

    void updateShopping(AddShoppingRequest addShoppingRequest);

    List<SearchShoppingResponse> search(SearchShoppingRequest searchShoppingRequest);

    void update(AddShoppingRequest addShoppingRequest);

    void delete(AddShoppingRequest addShoppingRequest);
}
