package com.buas_team.buas_backend2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("pay_category")
public class PayCate implements Serializable {
    @TableField("commodity_category")
    private String commodityCategory;
    private Integer amount;
}
