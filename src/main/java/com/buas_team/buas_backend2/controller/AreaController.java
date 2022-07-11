package com.buas_team.buas_backend2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.entity.AreaConsumTarget;
import com.buas_team.buas_backend2.entity.HoldCard;
import com.buas_team.buas_backend2.entity.PayPlace;
import com.buas_team.buas_backend2.mapper.AreaConsumTargetMapper;
import com.buas_team.buas_backend2.mapper.HoldCardMapper;
import com.buas_team.buas_backend2.mapper.PayPlaceMapper;
import com.buas_team.buas_backend2.mapper.PreConsumMapper;
import com.buas_team.buas_backend2.vo.PreConsumVO;
import lombok.experimental.PackagePrivate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.WeakHashMap;

@RestController
@CrossOrigin
public class AreaController {
    @Resource
    private PayPlaceMapper payPlaceMapper;
    @Resource
    private HoldCardMapper holdCardMapper;
    @Resource
    private PreConsumMapper preConsumMapper;
    @Resource
    private AreaConsumTargetMapper areaConsumTargetMapper;

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

    @GetMapping("/area/predict/{area}")
    public Result<?> getAreaPredict(@PathVariable String area){
        QueryWrapper<AreaConsumTarget> wrapper = new QueryWrapper<>();
        wrapper.eq("consumption_area",area);
        List<AreaConsumTarget> list = areaConsumTargetMapper.selectList(wrapper);
        return Result.sucess(list);
    }

    @GetMapping("/area/predict/consum")
    public Result<?> getConsum(){
        List<PreConsumVO> preConsumVOList = preConsumMapper.getAll();
        return Result.sucess(preConsumVOList);
    }
}
