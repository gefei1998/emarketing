package com.xjt.dao;

import com.xjt.entity.goods.JudgeFlag;
import com.xjt.entity.reviews.AddReviewReplyRequest;
import com.xjt.entity.reviews.AddReviewsRequest;
import com.xjt.entity.reviews.SearchReviewsRequest;
import com.xjt.entity.reviews.SearchReviewsResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IReviewsDao {
    void addReviews(AddReviewsRequest addReviewsRequest);

    List<SearchReviewsResponse> searchReviews(SearchReviewsRequest searchReviewsRequest);

    JudgeFlag judgeFlag(@Param("reviewsId")Integer reviewsId,@Param("userId") Integer userId);

    void addReviewReply( AddReviewReplyRequest addReviewReplyRequest);

    void deleteReviewReply( AddReviewReplyRequest addReviewReplyRequest);
}
