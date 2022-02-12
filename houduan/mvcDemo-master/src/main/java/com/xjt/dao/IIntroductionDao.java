package com.xjt.dao;

import com.xjt.entity.introdution.SearchIntroductionResponseEntity;
import com.xjt.entity.introdution.UpdateIntroductionRequestEntity;

public interface IIntroductionDao {

    void updateIntroduction(UpdateIntroductionRequestEntity updateIntroductionRequestEntity);

    SearchIntroductionResponseEntity searchIntroduction();
}
