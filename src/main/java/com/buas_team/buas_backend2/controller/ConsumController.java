package com.buas_team.buas_backend2.controller;

import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.entity.ConsumArea;
import com.buas_team.buas_backend2.entity.PayCate;
import com.buas_team.buas_backend2.entity.PayWay;
import com.buas_team.buas_backend2.mapper.AreaCateVOMapper;
import com.buas_team.buas_backend2.mapper.ConsumAreaMapper;
import com.buas_team.buas_backend2.mapper.PayCateMapper;
import com.buas_team.buas_backend2.mapper.PayWayMapper;
import com.buas_team.buas_backend2.vo.AreaCateVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class ConsumController {
    @Resource
    private ConsumAreaMapper consumAreaMapper;
    @Resource
    private AreaCateVOMapper areaCateVOMapper;
    @GetMapping("/consum/area")
    public Result<?> getConsumArea(){
        List<ConsumArea> consumAreaList = consumAreaMapper.selectList(null);
        return Result.sucess(consumAreaList);
    }
    @GetMapping("/consum/category/{province}")
    public Result<?> getCategory(@PathVariable String province){
        List<AreaCateVO> list = areaCateVOMapper.getAll(province);
        return Result.sucess(list);
    }
}
