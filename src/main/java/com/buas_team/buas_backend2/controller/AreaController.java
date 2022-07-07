package com.buas_team.buas_backend2.controller;

import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.entity.HoldCard;
import com.buas_team.buas_backend2.entity.PayPlace;
import com.buas_team.buas_backend2.mapper.HoldCardMapper;
import com.buas_team.buas_backend2.mapper.PayPlaceMapper;
import lombok.experimental.PackagePrivate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class AreaController {
    @Resource
    private PayPlaceMapper payPlaceMapper;
    @Resource
    private HoldCardMapper holdCardMapper;

    @GetMapping("/area/pay-place")
    public Result<?> getPayPlace(){
        List<PayPlace> payPlaceList = payPlaceMapper.selectList(null);
        return Result.sucess(payPlaceList);
    }
    @GetMapping("/area/hold-card")
    public Result<?> getHoldCard(){
        List<HoldCard> holdCardList = holdCardMapper.selectList(null);
        return Result.sucess(holdCardList);
    }
}
