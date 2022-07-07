package com.buas_team.buas_backend2.controller;

import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.entity.Age;
import com.buas_team.buas_backend2.entity.Cards;
import com.buas_team.buas_backend2.entity.Sex;
import com.buas_team.buas_backend2.mapper.AgeMapper;
import com.buas_team.buas_backend2.mapper.CardsMapper;
import com.buas_team.buas_backend2.mapper.SexMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class BaseAttrController {
    @Resource
    public SexMapper sexMapper;
    @Resource
    public AgeMapper ageMapper;
    @Resource
    public CardsMapper cardsMapper;
    @GetMapping("/base/sex")
    public Result<?> getSex(){
        List<Sex> sexList = sexMapper.selectList(null);
        return Result.sucess(sexList);
    }
    @GetMapping("/base/age")
    public Result<?> getAge(){
        List<Age> ageList = ageMapper.selectList(null);
        return Result.sucess(ageList);
    }
    @GetMapping("/base/cards")
    public Result<?> getCards(){
        List<Cards> cardsList = cardsMapper.selectList(null);
        return Result.sucess(cardsList);
    }
}
