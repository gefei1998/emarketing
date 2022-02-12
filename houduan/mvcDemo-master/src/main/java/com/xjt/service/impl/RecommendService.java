package com.xjt.service.impl;

import com.xjt.dao.IRecommendDao;
import com.xjt.entity.active.SearchActiveRequestEntity;
import com.xjt.entity.active.SearchActiveResponseEntity;
import com.xjt.entity.goods.SearchGoodsRequestEntity;
import com.xjt.entity.goods.SearchGoodsResponseEntity;
import com.xjt.entity.recommend.AddRecommendRequest;
import com.xjt.entity.recommend.SearchRecommendResponse;
import com.xjt.service.IActiveService;
import com.xjt.service.IGoodsService;
import com.xjt.service.IRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("recommendService")
public class RecommendService implements IRecommendService {
    @Resource
    private IRecommendDao recommendDao;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IActiveService activeService;

    @Override
    public void update(AddRecommendRequest addRecommendRequest) {
        recommendDao.update(addRecommendRequest);
    }

    @Override
    public SearchRecommendResponse search() {
        SearchRecommendResponse result = new SearchRecommendResponse();
        AddRecommendRequest recommendResult = recommendDao.searchRecommend();
        SearchGoodsRequestEntity searchGoodsRequestEntity1 = new SearchGoodsRequestEntity();
        searchGoodsRequestEntity1.setGoodId( Integer.valueOf(recommendResult.getGoodIds().split(",")[0]));
        searchGoodsRequestEntity1.setFlag(1);
        List<SearchGoodsResponseEntity> searchGoodsResponseEntityList1 = goodsService.search(searchGoodsRequestEntity1);
        for (SearchGoodsResponseEntity searchGoodsResponseEntity : searchGoodsResponseEntityList1) {
            result.setGoodId1(searchGoodsResponseEntity.getGoodId().toString());
            result.setGoodPath1(searchGoodsResponseEntity.getGoodPath());
        }

        SearchGoodsRequestEntity searchGoodsRequestEntity2 = new SearchGoodsRequestEntity();
        searchGoodsRequestEntity2.setGoodId( Integer.valueOf(recommendResult.getGoodIds().split(",")[1]));
        searchGoodsRequestEntity2.setFlag(1);
        List<SearchGoodsResponseEntity> searchGoodsResponseEntityList2 = goodsService.search(searchGoodsRequestEntity2);
        for (SearchGoodsResponseEntity searchGoodsResponseEntity : searchGoodsResponseEntityList2) {
            result.setGoodId2(searchGoodsResponseEntity.getGoodId().toString());
            result.setGoodPath2(searchGoodsResponseEntity.getGoodPath());
        }

        SearchGoodsRequestEntity searchGoodsRequestEntity3 = new SearchGoodsRequestEntity();
        searchGoodsRequestEntity3.setGoodId( Integer.valueOf(recommendResult.getGoodIds().split(",")[2]));
        searchGoodsRequestEntity3.setFlag(1);
        List<SearchGoodsResponseEntity> searchGoodsResponseEntityList3 = goodsService.search(searchGoodsRequestEntity3);
        for (SearchGoodsResponseEntity searchGoodsResponseEntity : searchGoodsResponseEntityList3) {
            result.setGoodId3(searchGoodsResponseEntity.getGoodId().toString());
            result.setGoodPath3(searchGoodsResponseEntity.getGoodPath());
        }

        SearchActiveRequestEntity searchActiveRequestEntity = new SearchActiveRequestEntity();
        searchActiveRequestEntity.setActiveId(recommendResult.getActiveId());
        List<SearchActiveResponseEntity> searchActiveResponseEntities = activeService.searchActive(searchActiveRequestEntity);
        for (SearchActiveResponseEntity searchActiveResponseEntity : searchActiveResponseEntities) {
            result.setActiveId(searchActiveResponseEntity.getActiveId());
            result.setActivePath(searchActiveResponseEntity.getActivePath());
        }
        return result;
    }
}
