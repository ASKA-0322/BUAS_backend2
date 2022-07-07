package com.buas_team.buas_backend2.controller;

import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.entity.PayCate;
import com.buas_team.buas_backend2.entity.PayWay;
import com.buas_team.buas_backend2.mapper.PayCateMapper;
import com.buas_team.buas_backend2.mapper.PayWayMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.ref.PhantomReference;
import java.util.List;

@RestController
@CrossOrigin
public class PayPreferController {
    @Resource
    private PayWayMapper payWayMapper;
    @Resource
    private PayCateMapper payCateMapper;
    @GetMapping("/pay-prefer/pay-way")
    public Result<?> getPayWay(){
        List<PayWay> payWayList = payWayMapper.selectList(null);
        return Result.sucess(payWayList);
    }
    @GetMapping("/pay-prefer/pay-category")
    public Result<?> getPayCate(){
        List<PayCate> payCateList = payCateMapper.selectList(null);
        return Result.sucess(payCateList);
    }
}
