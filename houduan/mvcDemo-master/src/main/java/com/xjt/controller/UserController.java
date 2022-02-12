package com.xjt.controller;

import com.xjt.common.Request;
import com.xjt.common.Result;
import com.xjt.entity.user.*;
import com.xjt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //登录
    @RequestMapping("/login")
    @ResponseBody
    @CrossOrigin
    public Result login( @RequestBody Request<LoginRequestEntity> request) throws Exception {
        Result result = new Result();
        LoginResponseEntity loginResponseEntity = userService.login(request.getData());
        result.setData(loginResponseEntity);
        return result;
    }

    //用户信息查看
    @RequestMapping("/detail")
    @ResponseBody
    @CrossOrigin
    public Result detail( @RequestBody Request<DetailRequestEntity> request) throws Exception {
        Result result = new Result();
        List<DetailResponseEntity> detailResponseEntity = userService.detail(request.getData());
        result.setData(detailResponseEntity);
        return result;
    }

    //注册，新增用户
    @RequestMapping("/register")
    @ResponseBody
    @CrossOrigin
    public void register( @RequestBody Request<RegisterRequestEntity> request) throws Exception {
        userService.register(request.getData());
    }

    //修改用户
    @RequestMapping("/update")
    @ResponseBody
    @CrossOrigin
    public void update( @RequestBody Request<UpdateRequestEntity> request) throws Exception {
        userService.update(request.getData());
    }

    //删除用户
    @RequestMapping("/delete")
    @ResponseBody
    @CrossOrigin
    public void delete( @RequestBody Request<DeleteRequestEntity> request) throws Exception {
        userService.delete(request.getData());
    }

}
