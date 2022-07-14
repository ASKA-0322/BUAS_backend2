package com.buas_team.buas_backend2.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buas_team.buas_backend2.entity.UserInfo;
import com.buas_team.buas_backend2.mapper.UserInfoMapper;
import com.buas_team.buas_backend2.service.UserService;
import com.buas_team.buas_backend2.util.JwtUtil;
import com.buas_team.buas_backend2.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {
    @Resource
    private UserInfoMapper userInfoMapper;
    /**
     * 限定这个realm只能处理JwtToken（不加的话会报错）
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) {
        String token = (String) auth.getCredentials();  //JwtToken中重写了这个方法了
        String username = JwtUtil.getUsername(token);   // 获得username
        log.info("in doGet方法");
        //用户不存在（这个在登录时不会进入，只有在token校验时才有可能进入）
        if(username == null)
            throw new UnknownAccountException();

        //根据用户名，查询数据库获取到正确的用户信息
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);

        //用户不存在（这个在登录时不会进入，只有在token校验时才有可能进入）
        if(userInfo == null)
            throw new UnknownAccountException();

        //密码错误(这里获取到password，就是3件套处理后的保存到数据库中的凭证，作为密钥)
        if (! JwtUtil.verifyToken(token, username, userInfo.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        //toke过期
        if(JwtUtil.isExpire(token)){
            throw new ExpiredCredentialsException();
        }
        return new SimpleAuthenticationInfo(userInfo, token, getName());
    }
}