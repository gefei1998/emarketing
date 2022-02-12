package com.xjt.dao;

import com.xjt.entity.user.*;

import java.util.List;

public interface IUserDao {

    LoginResponseEntity loginUser(LoginRequestEntity loginRequestEntity);

    void updateLogin(LoginRequestEntity loginRequestEntity);

    LoginResponseEntity loginAdmin(LoginRequestEntity loginRequestEntity);

    List<DetailResponseEntity> detail(DetailRequestEntity detailRequestEntity);

    void register(RegisterRequestEntity registerRequestEntity);

    void update(UpdateRequestEntity updateRequestEntity);

    void delete(DeleteRequestEntity deleteRequestEntity);
}
