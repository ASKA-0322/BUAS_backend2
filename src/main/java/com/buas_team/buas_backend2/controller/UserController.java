package com.buas_team.buas_backend2.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.dto.BankUserDTO;
import com.buas_team.buas_backend2.dto.UserDTO;
import com.buas_team.buas_backend2.entity.BankUser;
import com.buas_team.buas_backend2.entity.UserInfo;
import com.buas_team.buas_backend2.mapper.BankUserMapper;
import com.buas_team.buas_backend2.mapper.UserInfoMapper;
import com.buas_team.buas_backend2.service.BankUserService;
import com.buas_team.buas_backend2.service.UserService;
import com.buas_team.buas_backend2.shiro.JwtToken;
import com.buas_team.buas_backend2.util.JwtUtil;
import com.buas_team.buas_backend2.util.MD5Util;
import com.buas_team.buas_backend2.util.ShiroUtil;
import com.buas_team.buas_backend2.vo.UserInfoVO;
import com.google.code.kaptcha.Constants;
import jdk.nashorn.internal.ir.BaseNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.python.antlr.op.Sub;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private BankUserMapper bankUserMapper;
    @Resource
    private BankUserService bankUserService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserInfoMapper userInfoMapper;


    @GetMapping("/userinfo/get-user")
    @ResponseBody
    public UserInfo getUser(){
        return ShiroUtil.getUser();
    }

    @PostMapping("/userinfo/login")
    public Result<?> login(@Valid @RequestBody UserDTO userDTO, HttpServletResponse response) {
        String captchaCode = (String) redisTemplate.opsForValue().get(Constants.KAPTCHA_SESSION_KEY);
        if (captchaCode.equals(userDTO.getCaptchaCode())) {
            Long expire = redisTemplate.boundHashOps(Constants.KAPTCHA_SESSION_KEY).getExpire();
            if (expire < 0) {
                return Result.error(400, "???????????????");
            }
        } else {
            return Result.error(400, "???????????????");
        }

        log.info("?????????????????????");

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userDTO.getUsername());
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if(userInfo==null)
            return Result.error(400,"???????????????");
        String Md5Password = MD5Util.MD5Encode(userDTO.getPassword(),"UTF-8");
        String token = JwtUtil.getJwtToken(userDTO.getUsername(),Md5Password);
        JwtToken jwtToken = new JwtToken(token);

        Subject subject = SecurityUtils.getSubject();

        Result<Object> res = new Result<>();
        try{
            subject.login(jwtToken);
            res.setCode(200);
            res.setMsg("??????");
            res.setData(MapUtil.builder().put("username",userDTO.getUsername())
                                        .put("jwt",token)
                                        .map());
            log.info(String.valueOf(SecurityUtils.getSubject().isAuthenticated()));

            //test
            redisTemplate.opsForValue().set(token,userInfo,1, TimeUnit.DAYS);
        }
        catch (UnknownAccountException e){
            res.setCode(400);
            res.setMsg("???????????????");
            e.printStackTrace();
        }
        catch (IncorrectCredentialsException e){
            res.setCode(400);
            res.setMsg("????????????");
            e.printStackTrace();
        }
        catch (ExpiredCredentialsException e){
            res.setCode(400);
            res.setMsg("token??????");
        }
        finally{
            return res;
        }
    }

    @PostMapping("/userinfo/register")
    public Result<?> register(@Valid @RequestBody UserInfo userInfo){
        String res = userService.register(userInfo);
        if(!res.equals("success"))
            return Result.error(400,"????????????");
        return Result.sucess();
    }

    @RequiresAuthentication
    @GetMapping("/userinfo/area/{province}")
    public Result<?> selectByProvince(@PathVariable String province){
        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        System.out.println(ShiroUtil.getUser());
        wrapper.eq("consumption_area",province)
                .eq("user_id",ShiroUtil.getUser().getId());
        List<BankUser> bankUserList = bankUserMapper.selectList(wrapper);
        return Result.sucess(bankUserList);
    }

    @RequiresAuthentication
    @GetMapping("/userinfo/pay-method/{payMethod}")
    public Result<?> selectByPayMethod(@PathVariable String payMethod){
        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        wrapper.eq("pay_method",payMethod)
                .eq("user_id",ShiroUtil.getUser().getId());
        List<BankUser> bankUserList = bankUserMapper.selectList(wrapper);
        return Result.sucess(bankUserList);
    }

    @RequiresAuthentication
    @GetMapping("/userinfo/pay-time/{payTime}")
    public Result<?> selectByPayWay(@PathVariable String payTime){
        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        wrapper.eq("pay_time",payTime)
                .eq("user_id",ShiroUtil.getUser().getId());
        List<BankUser> bankUserList = bankUserMapper.selectList(wrapper);
        return Result.sucess(bankUserList);
    }

    @RequiresAuthentication
    @GetMapping("/userinfo/commodity_category/{category}")
    public Result<?> selectByCategory(@PathVariable String category){
        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        wrapper.eq("commodity_category",category)
                .eq("user_id",ShiroUtil.getUser().getId());
        List<BankUser> bankUserList = bankUserMapper.selectList(wrapper);
        return Result.sucess(bankUserList);
    }

    @PostMapping("/userinfo/{page}/{pageSize}/{token}")
    public Result<?> selectInfo(@PathVariable Integer page,
                                @PathVariable Integer pageSize,
                                @PathVariable String token,
                                @RequestBody UserInfoVO userInfoVO){
        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(userInfoVO.getConsumptionArea()),
                "consumption_area",userInfoVO.getConsumptionArea());
        wrapper.eq(StringUtils.isNotBlank(userInfoVO.getPayMethod()),
                "pay_method",userInfoVO.getPayMethod());
        wrapper.eq(userInfoVO.getPayTime()!=null,
                "pay_time",userInfoVO.getPayTime());
        wrapper.eq(StringUtils.isNotBlank(userInfoVO.getCommodityCategory()),
                "commodity_category",userInfoVO.getCommodityCategory());
        UserInfo userInfo = (UserInfo) redisTemplate.opsForValue().get(token);
        wrapper.eq("user_id",userInfo.getId());
        IPage<BankUser> ipage = bankUserService.page(new Page<>(page,pageSize),wrapper);
        return Result.sucess(ipage);
    }

    @PostMapping("/userinfo/update")
    public Result<?> updateInfo(@Valid @RequestBody BankUser bankUser){
        int res = bankUserMapper.updateById(bankUser);
        return Result.sucess("????????????");
    }

    @PostMapping("/userinfo/del/{id}")
    public Result<?> del(@PathVariable Integer id){
        int res = bankUserMapper.deleteById(id);
        if(res>0)
            return Result.sucess();
        return Result.error(400,"????????????");
    }

    @GetMapping("/userinfo/get")
    public Result<?> get(@RequestParam Integer page,
                         @RequestParam Integer pageSize){

        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",ShiroUtil.getUser().getId());
        IPage<BankUser> ipage = bankUserService.page(new Page<>(page,pageSize),wrapper);
        return Result.sucess(ipage);
    }

    @PostMapping("/userinfo/add/{token}")
    public Result<?> add(@Valid @RequestBody BankUserDTO bankUserDTO,
                         @PathVariable String token){
        int res = bankUserService.add(bankUserDTO,token);
        if(res>0)
            return Result.sucess();
        return Result.error(400,"????????????");
    }

    @GetMapping("/userinfo/logout/{token}")
    public Result logout(@PathVariable String token){
        redisTemplate.delete(token);
        return Result.sucess();
    }
}
