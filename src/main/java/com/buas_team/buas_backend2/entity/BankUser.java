package com.buas_team.buas_backend2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("bankuser")
public class BankUser implements Serializable {
    @TableId(type= IdType.AUTO)
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("name")
    private String name;
    @TableField("sex")
    private String sex;
    @TableField("age")
    private Integer age;
    @TableField("credit_cards")
    private Integer creditCards;
    @TableField("area")
    private String area;
    @TableField("consumption_area")
    private String consumptionArea;
    @TableField("consumption_amount")
    private Integer consumptionAmount;
    @TableField("pay_method")
    private String payMethod;
    @TableField("pay_time")
    private Integer payTime;
    @TableField("commodity_category")
    private String commodityCategory;
}
