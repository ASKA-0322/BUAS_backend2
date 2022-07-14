package com.buas_team.buas_backend2.util;

import com.buas_team.buas_backend2.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;

import javax.security.auth.Subject;
@Slf4j
public class ShiroUtil {
    public static UserInfo getUser(){
        log.info(((UserInfo)SecurityUtils.getSubject().getPrincipal()).toString());
        return (UserInfo) SecurityUtils.getSubject().getPrincipal();
    }
}


