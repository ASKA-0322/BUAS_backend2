package com.buas_team.buas_backend2.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buas_team.buas_backend2.entity.UserInfo;
import com.buas_team.buas_backend2.mapper.UserInfoMapper;
import com.buas_team.buas_backend2.service.UserService;
import com.buas_team.buas_backend2.util.ShiroUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

@Component
public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserInfoMapper userMapper;
    @Resource
    private UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username",token.getUsername());
        UserInfo userInfo = userMapper.selectOne(wrapper);
        if(userInfo==null){
            return null;
        }
        return new SimpleAuthenticationInfo(userInfo,userInfo.getPassword(),getName());
    }
}
