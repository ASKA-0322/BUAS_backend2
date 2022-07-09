package com.buas_team.buas_backend2.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2,max = 15,message = "账号长度在2-15")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6,max = 20,message = "密码长度在6-20")
    private String password;

    @NotBlank(message = "验证码不能为空")
    private String captchaCode;
}
