package com.buas_team.buas_backend2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user_sex")
public class Sex implements Serializable {
    private String sex;
    private Integer amount;
}
