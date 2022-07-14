package com.buas_team.buas_backend2.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.buas_team.buas_backend2.dto.BankUserDTO;
import com.buas_team.buas_backend2.entity.BankUser;
import com.buas_team.buas_backend2.entity.UserInfo;
import com.buas_team.buas_backend2.mapper.BankUserMapper;
import com.buas_team.buas_backend2.mapper.UserInfoMapper;
import com.buas_team.buas_backend2.service.BankUserService;
import com.buas_team.buas_backend2.util.ShiroUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
public class BankUserServiceImpl extends ServiceImpl<BankUserMapper,BankUser> implements BankUserService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private BankUserMapper bankUserMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public int add(BankUserDTO bankUserDTO,String token) {
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(token);
        BankUser bankUser = new BankUser(userInfo);
        bankUser.setConsumptionArea(bankUserDTO.getConsumptionArea());
        bankUser.setConsumptionAmount(bankUserDTO.getConsumptionAmount());
        bankUser.setPayMethod(bankUserDTO.getPayMethod());
        bankUser.setCommodityCategory(bankUserDTO.getCommodityCategory());
        bankUser.setPayTime(bankUserDTO.getPayTime());
        int res = bankUserMapper.insert(bankUser);
        return res;
    }
}
