package com.xjt.service.impl;

import com.xjt.dao.IActiveDao;
import com.xjt.entity.active.AddActiveEntity;
import com.xjt.entity.active.SearchActiveRequestEntity;
import com.xjt.entity.active.SearchActiveResponseEntity;
import com.xjt.service.IActiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("activeService")
public class ActiveServiceImpl implements IActiveService {

    @Resource
    private IActiveDao activeDao;

    @Override
    public void addActive(AddActiveEntity addActiveEntity) {
        activeDao.addActive(addActiveEntity);
    }

    @Override
    public void updateActive(AddActiveEntity addActiveEntity) {
        activeDao.updateActive(addActiveEntity);
    }

    @Override
    public List<SearchActiveResponseEntity> searchActive(SearchActiveRequestEntity searchActiveRequestEntity) {
        //实现查询活动功能的逻辑代码
        return activeDao.searchActive(searchActiveRequestEntity);
    }
}
