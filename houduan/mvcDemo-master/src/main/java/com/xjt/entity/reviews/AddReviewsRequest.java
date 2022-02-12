package com.xjt.entity.reviews;

public class AddReviewsRequest {
    //留言内容
    private String reviewsContent;

    //留言人
    private Integer userId;

    public String getReviewsContent() {
        return reviewsContent;
    }

    public void setReviewsContent(String reviewsContent) {
        this.reviewsContent = reviewsContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
