package com.buas_team.buas_backend2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jnr.ffi.annotations.In;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("area_consumption_target")
public class AreaConsumTarget implements Serializable {
    private String consumptionArea;
    private String payTime;
    private Integer amount;
}
