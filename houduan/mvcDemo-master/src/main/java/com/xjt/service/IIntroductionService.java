package com.xjt.service;

import com.xjt.entity.introdution.SearchIntroductionResponseEntity;
import com.xjt.entity.introdution.UpdateIntroductionRequestEntity;

public interface IIntroductionService {

    void updateIntroduction(UpdateIntroductionRequestEntity updateIntroductionRequestEntity);

    SearchIntroductionResponseEntity searchIntroduction();
}
