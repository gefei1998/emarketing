package com.xjt.service.impl;

import com.xjt.dao.IUserDao;
import com.xjt.entity.user.*;
import com.xjt.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public LoginResponseEntity login(LoginRequestEntity loginRequestEntity) {
        //管理员登录
        if(loginRequestEntity.getOperation()==0){
            return userDao.loginAdmin(loginRequestEntity);
        }
        //用户登录
        else{
            userDao.updateLogin(loginRequestEntity);
            return userDao.loginUser(loginRequestEntity);
        }
    }

    public List<DetailResponseEntity> detail(DetailRequestEntity detailRequestEntity) {
        if(detailRequestEntity.getPage()!=null&&detailRequestEntity.getPageNum()!=null){
            Integer pageStart = (detailRequestEntity.getPage()-1)*detailRequestEntity.getPageNum();
            detailRequestEntity.setPageStart(pageStart);
        }
        return userDao.detail(detailRequestEntity);
    }

    public void register(RegisterRequestEntity registerRequestEntity){
        userDao.register(registerRequestEntity);
    }

    public void update(UpdateRequestEntity updateRequestEntity){
        userDao.update(updateRequestEntity);
    }

    public void delete(DeleteRequestEntity deleteRequestEntity){
        userDao.delete(deleteRequestEntity);
    }

}
