package com.buas_team.buas_backend2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buas_team.buas_backend2.dto.UserDTO;
import com.buas_team.buas_backend2.entity.UserInfo;
import com.buas_team.buas_backend2.mapper.UserInfoMapper;
import com.buas_team.buas_backend2.service.UserService;
import com.buas_team.buas_backend2.util.MD5Util;
import com.google.code.kaptcha.Constants;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserInfoMapper,UserInfo> implements UserService {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private RedisTemplate redisTemplate;

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
