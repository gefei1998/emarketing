package com.xjt.service.impl;

import com.xjt.dao.IReviewsDao;
import com.xjt.entity.goods.JudgeFlag;
import com.xjt.entity.reviews.AddReviewReplyRequest;
import com.xjt.entity.reviews.AddReviewsRequest;
import com.xjt.entity.reviews.SearchReviewsRequest;
import com.xjt.entity.reviews.SearchReviewsResponse;
import com.xjt.service.IReviewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("reviewsService")
public class ReviewsServiceImpl implements IReviewsService {
    @Resource
    private IReviewsDao reviewsDao;

    @Override
    public void addReviews(AddReviewsRequest addReviewsRequest) {
        reviewsDao.addReviews(addReviewsRequest);
    }

    @Override
    public List<SearchReviewsResponse> searchReviews(SearchReviewsRequest searchReviewsRequest) {
        List<SearchReviewsResponse> searchReviewsResponseList = new ArrayList<>();
        if(searchReviewsRequest.getPage()!=null&&searchReviewsRequest.getPageNum()!=null){
            Integer pageStart = (searchReviewsRequest.getPage()-1)*searchReviewsRequest.getPageNum();
            searchReviewsRequest.setPageStart(pageStart);
        }
        searchReviewsResponseList = reviewsDao.searchReviews(searchReviewsRequest);
        if(searchReviewsRequest.getSearchFlag()!=null&&searchReviewsRequest.getSearchFlag()==1 ){
            for (SearchReviewsResponse searchReviewsResponse : searchReviewsResponseList) {
                JudgeFlag judgeFlag = reviewsDao.judgeFlag(searchReviewsResponse.getReviewsId(),searchReviewsRequest.getUserId());
                if(judgeFlag.getCommentNum()==1){
                    searchReviewsResponse.setFlag(1);
                }else {
                    searchReviewsResponse.setFlag(0);
                }
            }
        }
        return searchReviewsResponseList;
    }

    @Override
    public void addReviewReply(AddReviewReplyRequest addReviewReplyRequest) {
        //回复
        if(addReviewReplyRequest.getReplyFlag()==1){
            reviewsDao.addReviewReply(addReviewReplyRequest);
        }
        //删除
        else if(addReviewReplyRequest.getReplyFlag()==2){
            reviewsDao.deleteReviewReply(addReviewReplyRequest);
        }

    }
}
