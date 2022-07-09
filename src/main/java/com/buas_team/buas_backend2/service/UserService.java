package com.buas_team.buas_backend2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.buas_team.buas_backend2.dto.UserDTO;
import com.buas_team.buas_backend2.entity.UserInfo;

public interface UserService extends IService<UserInfo> {
    String register(UserInfo userInfo);
}
