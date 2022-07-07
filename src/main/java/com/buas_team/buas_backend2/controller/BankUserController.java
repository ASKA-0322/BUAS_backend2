package com.buas_team.buas_backend2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.entity.BankUser;
import com.buas_team.buas_backend2.mapper.BankUserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class BankUserController {
    @Resource
    public BankUserMapper bankUserMapper;

    @GetMapping("/user/get")
    public Result<?> getAll(){
        List<BankUser> bankUserList = bankUserMapper.selectList(null);
        return Result.sucess(bankUserList);
    }
    @GetMapping("/user/uid/{user_id}")
    public Result<?> selectById(@PathVariable Integer user_id){
        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user_id);
        List<BankUser> bankUserList = bankUserMapper.selectList(wrapper);
        return Result.sucess(bankUserList);
    }
    @GetMapping("/user/name/{name}")
    public Result<?> selectByName(@PathVariable String name){
        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        List<BankUser> bankUserList = bankUserMapper.selectList(wrapper);
        return Result.sucess(bankUserList);
    }
    @GetMapping("/user/area/{area}")
    public Result<?> selectByArea(@PathVariable String area){
        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        wrapper.eq("area",area);
        List<BankUser> bankUserList = bankUserMapper.selectList(wrapper);
        return Result.sucess(bankUserList);
    }
    @PostMapping("/user/del/{id}")
    public Result<?> deleteById(@PathVariable Integer id){
        int result = bankUserMapper.deleteById(id);
        if(result>0)
            return Result.sucess();
        return Result.error(400,"删除失败");
    }
}
