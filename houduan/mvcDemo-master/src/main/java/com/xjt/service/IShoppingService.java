package com.xjt.service;

import com.xjt.entity.shopping.AddShoppingRequest;
import com.xjt.entity.shopping.SearchShoppingRequest;
import com.xjt.entity.shopping.SearchShoppingResponse;

import java.util.List;

public interface IShoppingService {
    void add(AddShoppingRequest addShoppingRequest);

    List<SearchShoppingResponse> search(SearchShoppingRequest searchShoppingRequest);

    void update(AddShoppingRequest addShoppingRequest);

    void delete(AddShoppingRequest addShoppingRequest);
}
