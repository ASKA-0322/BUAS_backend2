package com.buas_team.buas_backend2.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.buas_team.buas_backend2.dto.BankUserDTO;
import com.buas_team.buas_backend2.entity.BankUser;


public interface BankUserService extends IService<BankUser> {
    int add(BankUserDTO bankUserDTO,String token);
}
