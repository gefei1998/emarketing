package com.xjt.service;

import com.xjt.entity.user.*;

import java.util.List;

public interface IUserService {

    LoginResponseEntity login(LoginRequestEntity loginRequestEntity);

    List<DetailResponseEntity> detail(DetailRequestEntity detailRequestEntity);

    void register(RegisterRequestEntity registerRequestEntity);

    void update(UpdateRequestEntity updateRequestEntity);

    void delete(DeleteRequestEntity deleteRequestEntity);

}
