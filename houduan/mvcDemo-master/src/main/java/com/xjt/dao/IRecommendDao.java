package com.xjt.dao;

import com.xjt.entity.recommend.AddRecommendRequest;

public interface IRecommendDao {

    void update(AddRecommendRequest addRecommendRequest);

    AddRecommendRequest searchRecommend();
}
