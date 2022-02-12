package com.xjt.service;

import com.xjt.entity.reviews.AddReviewReplyRequest;
import com.xjt.entity.reviews.AddReviewsRequest;
import com.xjt.entity.reviews.SearchReviewsRequest;
import com.xjt.entity.reviews.SearchReviewsResponse;

import java.util.List;

public interface IReviewsService {

    void addReviews(AddReviewsRequest addReviewsRequest);

    List<SearchReviewsResponse> searchReviews(SearchReviewsRequest searchReviewsRequest);

    void addReviewReply( AddReviewReplyRequest addReviewReplyRequest);
}
