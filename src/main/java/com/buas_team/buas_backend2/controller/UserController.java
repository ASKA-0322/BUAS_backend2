package com.buas_team.buas_backend2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.dto.BankUserDTO;
import com.buas_team.buas_backend2.dto.UserDTO;
import com.buas_team.buas_backend2.entity.BankUser;
import com.buas_team.buas_backend2.entity.UserInfo;
import com.buas_team.buas_backend2.mapper.BankUserMapper;
import com.buas_team.buas_backend2.service.BankUserService;
import com.buas_team.buas_backend2.service.UserService;
import com.buas_team.buas_backend2.util.JwtUtils;
import com.buas_team.buas_backend2.util.MD5Util;
import com.buas_team.buas_backend2.util.ShiroUtil;
import com.buas_team.buas_backend2.vo.UserInfoVO;
import com.google.code.kaptcha.Constants;
import jdk.nashorn.internal.ir.BaseNode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
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
    private JwtUtils jwtUtils;


    @GetMapping("/get-user")
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
                return Result.error(400, "验证码过期");
            }
        } else {
            return Result.error(400, "验证码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        String Md5Password = MD5Util.MD5Encode(userDTO.getPassword(), "UTF-8");
        UsernamePasswordToken token = new UsernamePasswordToken(userDTO.getUsername(), Md5Password);
        subject.login(token);  //会去调用userrealm的认证方法
        session.setTimeout(200000000L);
        String jwt = jwtUtils.generateToken(ShiroUtil.getUser().getId());//生成token
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-Control-Expose-Headers","Authorization");

        Map<String, String> res = new HashMap<String,String>(){{
            put("username",userDTO.getUsername());
            put("jwt",jwt);
        }};
        return Result.sucess(res);
    }

    @RequiresAuthentication
    @PostMapping("/userinfo/register")
    public Result<?> register(@Valid @RequestBody UserInfo userInfo){
        String res = userService.register(userInfo);
        if(!res.equals("success"))
            return Result.error(400,"注册错误");
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

    @RequiresAuthentication
    @PostMapping("/userinfo/{page}/{pageSize}")
    public Result<?> selectInfo(@PathVariable Integer page,
                                @PathVariable Integer pageSize,
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
        wrapper.eq("user_id",ShiroUtil.getUser().getId());
        IPage<BankUser> ipage = bankUserService.page(new Page<>(page,pageSize),wrapper);
        return Result.sucess(ipage);
    }

    @RequiresAuthentication
    @PostMapping("/userinfo/update")
    public Result<?> updateInfo(@Valid @RequestBody BankUser bankUser){
        int res = bankUserMapper.updateById(bankUser);
        return Result.sucess("更新成功");
    }

    @RequiresAuthentication
    @PostMapping("/userinfo/del/{id}")
    public Result<?> del(@PathVariable Integer id){
        int res = bankUserMapper.deleteById(id);
        if(res>0)
            return Result.sucess();
        return Result.error(400,"删除失败");
    }

    @RequiresAuthentication
    @GetMapping("/userinfo/get")
    public Result<?> get(@RequestParam Integer page,
                         @RequestParam Integer pageSize){
        QueryWrapper<BankUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",ShiroUtil.getUser().getId());
        IPage<BankUser> ipage = bankUserService.page(new Page<>(page,pageSize),wrapper);
        return Result.sucess(ipage);
    }

    @RequiresAuthentication
    @PostMapping("/userinfo/add")
    public Result<?> add(@Valid @RequestBody BankUserDTO bankUserDTO){
        int res = bankUserService.add(bankUserDTO);
        if(res>0)
            return Result.sucess();
        return Result.error(400,"添加错误");
    }

    @RequiresAuthentication
    @GetMapping("/userinfo/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.sucess();
    }
}
