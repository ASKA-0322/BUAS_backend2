package com.buas_team.buas_backend2.controller;

import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.entity.ConsumArea;
import com.buas_team.buas_backend2.entity.PayCate;
import com.buas_team.buas_backend2.entity.PayWay;
import com.buas_team.buas_backend2.mapper.AreaCateVOMapper;
import com.buas_team.buas_backend2.mapper.ConsumAreaMapper;
import com.buas_team.buas_backend2.mapper.PayCateMapper;
import com.buas_team.buas_backend2.mapper.PayWayMapper;
import com.buas_team.buas_backend2.vo.AreaCateListVO;
import com.buas_team.buas_backend2.vo.AreaCateVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/consum/category")
    public Result<?> getCategory(){
        String[] province = {"黑龙江", "香港", "青海", "陕西", "重庆", "辽宁", "贵州","西藏", "福建", "甘肃", "澳门", "湖南", "湖北", "海南", "浙江", "河南", "河北", "江西","江苏","新疆", "广西", "广东", "山西", "山东", "安徽", "宁夏", "天津", "四川", "吉林", "台湾","北京","内蒙古","云南","上海"};
        List<AreaCateListVO> list = new ArrayList<>();
        for(String p:province) {
            AreaCateListVO temp = new AreaCateListVO();
            temp.setProvince(p);
            temp.setAreaCateList(areaCateVOMapper.getAll(p));
            list.add(temp);
        }
        return Result.sucess(list);
    }
}
