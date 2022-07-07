package com.buas_team.buas_backend2.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

//地域消费类别统计
@Data
public class AreaCateVO implements Serializable {
    @TableField("commodity_category")
    private String commodityCategory;
    @TableField("amount")
    private Integer amount;
}
