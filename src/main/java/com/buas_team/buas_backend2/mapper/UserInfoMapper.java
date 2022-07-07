package com.buas_team.buas_backend2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buas_team.buas_backend2.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
