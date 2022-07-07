package com.buas_team.buas_backend2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user_cards")
public class Cards implements Serializable {
    private String cards;
    private Integer amount;
}
