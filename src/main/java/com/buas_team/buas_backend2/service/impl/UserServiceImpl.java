package com.buas_team.buas_backend2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buas_team.buas_backend2.dto.UserDTO;
import com.buas_team.buas_backend2.entity.UserInfo;
import com.buas_team.buas_backend2.mapper.UserInfoMapper;
import com.buas_team.buas_backend2.service.UserService;
import com.buas_team.buas_backend2.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public String login(UserDTO userDTO) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userDTO.getUsername());
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if(userInfo==null)
            return "error";
        String Md5Password = MD5Util.MD5Encode(userDTO.getPassword(),"UTF-8");
        if(!userInfo.getPassword().equals(Md5Password))
            return "error";
        return "success";
    }

    @Override
    public String register(UserInfo userInfo) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userInfo.getUsername());
        UserInfo temp = userInfoMapper.selectOne(wrapper);
        if(temp!=null)
            return "error";
        String password = MD5Util.MD5Encode(userInfo.getPassword(),"UTF-8");
        userInfo.setPassword(password);
        userInfoMapper.insert(userInfo);
        return "success";
    }
}
