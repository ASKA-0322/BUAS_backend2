package com.buas_team.buas_backend2.controller;

import com.buas_team.buas_backend2.util.CaptchaUtils;
import com.google.code.kaptcha.Constants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Controller
public class CaptchaController {
    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/captcha-image")
    public void createImageCode(HttpServletResponse response) throws IOException {
        //禁止缓存
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        //设置响应格式为png图片
        response.setContentType("image/png");
        // 生成图片验证码
        BufferedImage image = new BufferedImage(300, 75, BufferedImage.TYPE_INT_RGB);
        String randomText = CaptchaUtils.drawRandomText(image);
        System.out.println("验证码->>>"+randomText);
        // 存入redis(设置过期时间1分钟)
        redisTemplate.opsForValue().set(Constants.KAPTCHA_SESSION_KEY, randomText,600, TimeUnit.SECONDS);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
        out.flush();
        out.close();
    }
}
