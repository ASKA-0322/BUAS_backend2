package com.buas_team.buas_backend2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("bankuser")
public class BankUser implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;

    @NotBlank(message = "银行卡号")
    @TableField("user_id")
    private Integer userId;

    @NotBlank(message = "姓名不能为空")
    @TableField("name")
    private String name;

    @NotBlank(message = "性别不能为空")
    @TableField("sex")
    private String sex;

    @NotBlank(message = "年龄不能为空")
    @Size(min=18,max=80,message = "年龄在18到80岁之间")
    @TableField("age")
    private Integer age;

    @NotBlank(message = "银行卡数不能为空")
    @TableField("credit_cards")
    private Integer creditCards;

    @NotBlank(message = "办卡地区不能为空")
    @TableField("area")
    private String area;

    @NotBlank(message = "消费地区不能为空")
    @TableField("consumption_area")
    private String consumptionArea;

    @NotBlank(message = "消费金额不能为空")
    @TableField("consumption_amount")
    private Integer consumptionAmount;

    @NotBlank(message = "支付方式不能为空")
    @TableField("pay_method")
    private String payMethod;

    @NotBlank(message = "支付时间不能为空")
    @TableField("pay_time")
    private Integer payTime;

    @NotBlank(message = "商品类别不能为空")
    @TableField("commodity_category")
    private String commodityCategory;
}
