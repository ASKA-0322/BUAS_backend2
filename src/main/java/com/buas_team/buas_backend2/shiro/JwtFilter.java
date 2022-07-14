package com.buas_team.buas_backend2.shiro;

import com.buas_team.buas_backend2.common.Result;
import com.buas_team.buas_backend2.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.*;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;


@Slf4j
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter{

    //token验证
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        //在请求头中获取token
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization"); //前端命名Authorization
        //token不存在
        if(token == null || "".equals(token)){
            Result<Object> res = new Result<>();
            res.setCode(400);
            res.setMsg("没有token");
            out(response, res);
            return false;
        }
        log.info("in executeLogin.....");
        //token存在，进行验证
        JwtToken jwtToken = new JwtToken(token);
        try {
            SecurityUtils.getSubject().login(jwtToken);  //通过subject，提交给myRealm进行登录验证

            return true;
        } catch (ExpiredCredentialsException e){
            Result<Object> res = new Result<>();
            res.setCode(400);
            res.setMsg("token过期");
            e.printStackTrace();
            return false;
        } catch (ShiroException e){
            // 其他情况抛出的异常统一处理，由于先前是登录进去的了，所以都可以看成是token被伪造造成的
            Result<Object> res = new Result<>();
            res.setCode(400);
            res.setMsg("token被伪造");
            out(response,res);
            e.printStackTrace();
            return false;
        }
    }


     // json形式返回结果token验证失败信息，无需转发
    private void out(ServletResponse response, Result<?> res) throws IOException {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        ObjectMapper mapper = new ObjectMapper();
        String jsonRes = mapper.writeValueAsString(res);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.getOutputStream().write(jsonRes.getBytes());
    }

    /**
     * 过滤器拦截请求的入口方法
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("in isAccessAllowed");
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        try {
            log.info("try");
            String jwt = httpRequest.getHeader("Authorization");
            log.info(jwt);
            if(StringUtils.isEmpty(jwt))
                return false;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * isAccessAllowed()方法返回false，即认证不通过时进入onAccessDenied方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("in onAccessDenied");
        boolean res = executeLogin(request,response);
        return res;
    }

    /**
     * token认证executeLogin成功后，进入此方法，可以进行token更新过期时间
     */
//    @Override
//    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

//    }
}


