package com.buas_team.buas_backend2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("pay_way")
public class PayWay implements Serializable {
    @TableField("pay_method")
    private String payMethod;
    private Integer amount;
}
