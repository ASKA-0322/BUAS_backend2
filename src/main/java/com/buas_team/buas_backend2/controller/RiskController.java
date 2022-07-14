package com.buas_team.buas_backend2.controller;

import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.mapper.ConsumRiskVOMapper;
import com.buas_team.buas_backend2.mapper.RiskAgeMapper;
import com.buas_team.buas_backend2.mapper.RiskGenerationMapper;
import com.buas_team.buas_backend2.mapper.RiskSexMapper;
import com.buas_team.buas_backend2.vo.ConsumRiskVO;
import com.buas_team.buas_backend2.vo.RiskAgeVO;
import com.buas_team.buas_backend2.vo.RiskGenerationVO;
import com.buas_team.buas_backend2.vo.RiskSexVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RiskController {
    @Resource
    private ConsumRiskVOMapper consumRiskVOMapper;
    @Resource
    private RiskAgeMapper riskAgeMapper;
    @Resource
    private RiskGenerationMapper riskGenerationMapper;
    @Resource
    private RiskSexMapper riskSexMapper;

    @GetMapping("/risk/consum")
    public Result<?> getConsum(){
        List<ConsumRiskVO> consumRiskVOList = consumRiskVOMapper.getAll();
        return Result.sucess(consumRiskVOList);
    }

    @GetMapping("/risk/age")
    public Result<?> getAge(){
        List<RiskAgeVO> riskAgeVOList = riskAgeMapper.getAll();
        return Result.sucess(riskAgeVOList);
    }

    @GetMapping("/risk/generation")
    public Result<?> getGeneration(){
        List<RiskGenerationVO> riskGenerationVOList = riskGenerationMapper.getAll();
        return Result.sucess(riskGenerationVOList);
    }

    @GetMapping("/risk/sex")
    public Result<?> getSex(){
        List<RiskSexVO> riskSexVOList = riskSexMapper.getAll();
        return Result.sucess(riskSexVOList);
    }
}
