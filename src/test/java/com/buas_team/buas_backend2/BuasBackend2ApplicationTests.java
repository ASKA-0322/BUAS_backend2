package com.buas_team.buas_backend2;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buas_team.buas_backend2.entity.BankUser;
import com.buas_team.buas_backend2.entity.UserInfo;
import com.buas_team.buas_backend2.mapper.AreaCateVOMapper;
import com.buas_team.buas_backend2.mapper.BankUserMapper;
import com.buas_team.buas_backend2.service.BankUserService;
import com.buas_team.buas_backend2.service.UserService;
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
    @Resource
    private UserService userService;
    @Resource
    private BankUserService bankUserService;
    @Test
    void contextLoads() {
//        IPage<UserInfo> ipage = userService.page(new Page<>(1,1));
        IPage<BankUser> ipage = bankUserService.page(new Page<>(1,1));
        System.out.println(ipage);
    }
}
