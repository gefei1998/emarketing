package com.xjt.controller;

import com.xjt.common.Request;
import com.xjt.common.Result;
import com.xjt.entity.reviews.AddReviewReplyRequest;
import com.xjt.entity.reviews.AddReviewsRequest;
import com.xjt.entity.reviews.SearchReviewsRequest;
import com.xjt.entity.reviews.SearchReviewsResponse;
import com.xjt.service.IReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/reviews")
public class ReviewsController {
    @Autowired
    private IReviewsService reviewsService;

    //新增网站留言
    @RequestMapping("/add")
    @ResponseBody
    @CrossOrigin
    public void addReviews( @RequestBody Request<AddReviewsRequest> request) throws Exception {
        reviewsService.addReviews(request.getData());
    }

    //查看网站留言
    @RequestMapping("/search")
    @ResponseBody
    @CrossOrigin
    public Result searchReviews(@RequestBody Request<SearchReviewsRequest> request) throws Exception {
        Result result = new Result();
        List<SearchReviewsResponse> searchReviewsResponseList = reviewsService.searchReviews(request.getData());
        result.setData(searchReviewsResponseList);
        return result;
    }

    //新增商家回复
    @RequestMapping("/addReviewReply")
    @ResponseBody
    @CrossOrigin
    public void addReviewReply( @RequestBody Request<AddReviewReplyRequest> request) throws Exception {
        reviewsService.addReviewReply(request.getData());
    }

}
