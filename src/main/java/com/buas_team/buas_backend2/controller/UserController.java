package com.buas_team.buas_backend2.controller;

import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.dto.UserDTO;
import com.buas_team.buas_backend2.entity.UserInfo;
import com.buas_team.buas_backend2.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("/user/login")
    public Result<?> login(@Valid @RequestBody UserDTO userDTO){
        String res = userService.login(userDTO);
        if(!res.equals("success"))
            return Result.error(400,"登录错误");
        return Result.sucess();
    }
    @PostMapping("/user/register")
    public Result<?> register(@Valid @RequestBody UserInfo userInfo){
        String res = userService.register(userInfo);
        if(!res.equals("success"))
            return Result.error(400,"注册错误");
        return Result.sucess();
    }
}
