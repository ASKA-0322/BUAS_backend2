package com.buas_team.buas_backend2.service;

import com.buas_team.buas_backend2.dto.UserDTO;
import com.buas_team.buas_backend2.entity.UserInfo;

public interface UserService {
    String login(UserDTO userDTO);
    String register(UserInfo userInfo);
}
