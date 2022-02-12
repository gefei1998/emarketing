package com.xjt.service.impl;

import com.xjt.dao.IGoodsDao;
import com.xjt.entity.goods.*;
import com.xjt.service.IGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService {

    @Resource
    private IGoodsDao goodsDao;

    public void add(AddRequestEntity addRequestEntity){
        goodsDao.add(addRequestEntity);
    }

    public void update(UpdateGoodsRequestEntity updateGoodsRequestEntity){
        goodsDao.update(updateGoodsRequestEntity);
    }

    public List<SearchGoodsResponseEntity> search(SearchGoodsRequestEntity searchGoodsRequestEntity){
        List<SearchGoodsResponseEntity> searchGoodsResponseEntityList = new ArrayList<>();
        //如果是用户
        if(searchGoodsRequestEntity.getFlag()==0){
            //只查询启用的商品
            searchGoodsRequestEntity.setDeleteFlag(0);
        }
        if(searchGoodsRequestEntity.getPage()!=null&&searchGoodsRequestEntity.getPageNum()!=null){
            Integer pageStart = (searchGoodsRequestEntity.getPage()-1)*searchGoodsRequestEntity.getPageNum();
            searchGoodsRequestEntity.setPageStart(pageStart);
        }
        //获取商品信息
        searchGoodsResponseEntityList = goodsDao.search(searchGoodsRequestEntity);
        //获取每个商品的评论并赋值
        for (SearchGoodsResponseEntity searchGoodsResponseEntity : searchGoodsResponseEntityList) {
            List<Comment> commendList = goodsDao.getComment(searchGoodsResponseEntity.getGoodId());
            //判断这些评论中哪些是用户自己的评论
            if(searchGoodsRequestEntity.getUserId()!=null&&searchGoodsRequestEntity.getFlag()==0){
                for (Comment comment : commendList) {
                    JudgeFlag judgeFlag = goodsDao.judgeFlag(comment.getCommendId(),searchGoodsRequestEntity.getUserId());
                    if(judgeFlag.getCommentNum()==1){
                        comment.setFlag(1);
                    }else {
                        comment.setFlag(0);
                    }
                }
            }
            searchGoodsResponseEntity.setCommendList(commendList);
        }
        return searchGoodsResponseEntityList;
    }

    @Override
    public void addCommendReply(AddCommendReplyRequest addCommendReplyRequest) {
        //回复
        if(addCommendReplyRequest.getReplyFlag()==1){
            goodsDao.addCommendReply(addCommendReplyRequest);
        }
        //删除回复
        else if(addCommendReplyRequest.getReplyFlag()==2){
            goodsDao.deleteCommendReply(addCommendReplyRequest);
        }

    }

    @Override
    public void addCommend(AddCommendRequest addCommendRequest) {
        goodsDao.addCommend(addCommendRequest);
    }
}
