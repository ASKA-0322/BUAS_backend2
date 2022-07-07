package com.buas_team.buas_backend2;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buas_team.buas_backend2.entity.BankUser;
import com.buas_team.buas_backend2.mapper.AreaCateVOMapper;
import com.buas_team.buas_backend2.mapper.BankUserMapper;
import com.buas_team.buas_backend2.vo.AreaCateVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class BuasBackend2ApplicationTests {
    @Resource
    private AreaCateVOMapper areaCateVOMapper;
    @Resource
    private BankUserMapper bankUserMapper;

    @Test
    void contextLoads() {

    }
}
