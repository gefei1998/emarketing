package com.xjt.service;

import com.xjt.entity.recommend.AddRecommendRequest;
import com.xjt.entity.recommend.SearchRecommendResponse;

public interface IRecommendService {

   void update(AddRecommendRequest addRecommendRequest);

   SearchRecommendResponse search();
}
