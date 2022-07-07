package com.buas_team.buas_backend2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user_age")
public class Age implements Serializable {
    private String period;
    private Integer amount;
}
