package com.buas_team.buas_backend2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.entity.BankUser;
import com.buas_team.buas_backend2.entity.UserInfo;
import com.buas_team.buas_backend2.mapper.BankUserMapper;
import com.buas_team.buas_backend2.service.BankUserService;
import com.buas_team.buas_backend2.vo.BankUserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class BankUserController {
    @Resource
    public BankUserMapper bankUserMapper;
    @Resource
    public BankUserService bankUserService;

    @PostMapping("/user/get")
    public Result<?> getList(@RequestParam Integer page,
                             @RequestParam Integer pageSize){
        IPage<BankUser> ipage = bankUserService.page(new Page<>(page,pageSize));
        return Result.sucess(ipage);
    }

    @GetMapping("/user/uid/{user_id}")
    public Result<?> selectById(@PathVariable Integer user_id,@RequestParam Integer page,@RequestParam Integer pageSize){
        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",user_id);
        List<BankUser> bankUserList = bankUserMapper.selectList(wrapper);
        IPage<BankUser> res = bankUserService.page(new Page<>(page,pageSize),wrapper);
        return Result.sucess(res);
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

    @PostMapping("/user/{page}/{pageSize}")
    public Result<?> select(@PathVariable Integer page,
                            @PathVariable Integer pageSize,
                            @RequestBody BankUserVO bankUserVO){
        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        wrapper.eq(bankUserVO.getUserId()!=null,"user_id",bankUserVO.getUserId());
        wrapper.eq(StringUtils.isNotBlank(bankUserVO.getName()),"name",bankUserVO.getName());
        wrapper.eq(StringUtils.isNotBlank(bankUserVO.getArea()),"area",bankUserVO.getArea());
        IPage<BankUser> bankUserIpage = bankUserService.page(new Page<>(page,pageSize),wrapper);
        return Result.sucess(bankUserIpage);
    }

    @PostMapping("/user/del/{id}")
    public Result<?> deleteById(@PathVariable Integer id){
        int result = bankUserMapper.deleteById(id);
        if(result>0)
            return Result.sucess();
        return Result.error(400,"删除失败");
    }

    @PostMapping("/user/add")
    public Result<?> add(@Valid @RequestBody BankUser bankUser){
        int res = bankUserMapper.insert(bankUser);
        if(res>0)
            return Result.sucess();
        return Result.error(400,"添加错误");
    }

    @PostMapping("/user/update")
    public Result<?> update(@Valid @RequestBody BankUser bankUser){
        bankUserMapper.updateById(bankUser);
        return Result.sucess("更新成功");
    }

    @GetMapping("/user/get/{id}")
    public Result<?> getById(@PathVariable Integer id){
        BankUser bankUser = bankUserMapper.selectById(id);
        return Result.sucess(bankUser);
    }
}
