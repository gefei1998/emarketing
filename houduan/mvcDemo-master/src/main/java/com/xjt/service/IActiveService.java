package com.xjt.service;

import com.xjt.entity.active.AddActiveEntity;
import com.xjt.entity.active.SearchActiveRequestEntity;
import com.xjt.entity.active.SearchActiveResponseEntity;

import java.util.List;

public interface IActiveService {

    void addActive(AddActiveEntity addActiveEntity);

    void updateActive(AddActiveEntity addActiveEntity);

    List<SearchActiveResponseEntity> searchActive(SearchActiveRequestEntity searchActiveRequestEntity);
}
