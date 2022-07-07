package com.buas_team.buas_backend2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("hold_card")
public class HoldCard implements Serializable {
    private String area;
    private Integer amount;
}
