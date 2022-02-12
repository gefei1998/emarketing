package com.xjt.dao;

import com.xjt.entity.goods.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGoodsDao {

    void add(AddRequestEntity addRequestEntity);

    void update(UpdateGoodsRequestEntity updateGoodsRequestEntity);

    List<SearchGoodsResponseEntity> search(SearchGoodsRequestEntity searchGoodsRequestEntity);

    List<Comment> getComment(Integer goodId);

    JudgeFlag judgeFlag(@Param("commendId")Integer commendId, @Param("userId")Integer userId);

    void addCommendReply(AddCommendReplyRequest addCommendReplyRequest);

    void deleteCommendReply(AddCommendReplyRequest addCommendReplyRequest);

    void addCommend(AddCommendRequest addCommendRequest);
}
