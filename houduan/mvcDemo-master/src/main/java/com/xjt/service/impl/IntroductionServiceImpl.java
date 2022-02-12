package com.xjt.service.impl;

import com.xjt.dao.IIntroductionDao;
import com.xjt.entity.introdution.SearchIntroductionResponseEntity;
import com.xjt.entity.introdution.UpdateIntroductionRequestEntity;
import com.xjt.service.IIntroductionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("introductionService")
public class IntroductionServiceImpl implements IIntroductionService {

    @Resource
    private IIntroductionDao introductionDao;

    @Override
    public void updateIntroduction(UpdateIntroductionRequestEntity updateIntroductionRequestEntity) {
        introductionDao.updateIntroduction(updateIntroductionRequestEntity);
    }

    @Override
    public SearchIntroductionResponseEntity searchIntroduction() {
        return introductionDao.searchIntroduction();
    }
}
