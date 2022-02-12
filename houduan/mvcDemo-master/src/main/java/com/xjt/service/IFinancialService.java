package com.xjt.service;

import com.xjt.entity.financial.SearchFinancialRequestEntity;
import com.xjt.entity.financial.SearchFinancialResponseEntity;

public interface IFinancialService {

    SearchFinancialResponseEntity searchFinancial(SearchFinancialRequestEntity searchFinancialRequestEntity);
}
