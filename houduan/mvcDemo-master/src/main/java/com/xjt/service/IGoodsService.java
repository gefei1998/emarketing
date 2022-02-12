package com.xjt.service;

import com.xjt.entity.goods.*;

import java.util.List;

public interface IGoodsService {

    void add(AddRequestEntity addRequestEntity);

    void update(UpdateGoodsRequestEntity updateGoodsRequestEntity);

    List<SearchGoodsResponseEntity> search(SearchGoodsRequestEntity searchGoodsRequestEntity);

    void addCommendReply(AddCommendReplyRequest addCommendReplyRequest);

    void addCommend(AddCommendRequest addCommendRequest);

}
