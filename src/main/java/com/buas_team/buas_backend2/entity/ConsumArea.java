package com.buas_team.buas_backend2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("consumption_area")
public class ConsumArea implements Serializable {
    @TableField("consumption_area")
    private String consumptionArea;
    private Integer amount;
}
