package com.buas_team.buas_backend2.util;

import com.buas_team.buas_backend2.entity.UserInfo;
import org.apache.shiro.SecurityUtils;

import javax.security.auth.Subject;

public class ShiroUtil {
    public static UserInfo getUser(){
        return (UserInfo) SecurityUtils.getSubject().getPrincipal();
    }
}


