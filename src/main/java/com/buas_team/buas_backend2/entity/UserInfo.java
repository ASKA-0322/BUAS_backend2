package com.buas_team.buas_backend2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.xml.internal.ws.api.model.MEP;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@TableName("user_info")
public class UserInfo implements Serializable {
    @TableId(type= IdType.INPUT)
    @Size(min=8,max=8,message = "银行卡号为8位")
    private Integer id;
    @NotBlank(message = "账号不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "姓名不能为空")
    private String name;
    @NotBlank(message = "性别不能为空")
    private String sex;
    @NotBlank(message = "年龄不能为空")
    private Integer age;
    @TableField("credit_cards")
    @NotBlank(message = "银行卡数不能为空")
    private Integer creditCards;
    @NotBlank(message = "办卡地区不能为空")
    private String area;
}
