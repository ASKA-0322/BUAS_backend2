package com.buas_team.buas_backend2.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class BankUserDTO implements Serializable {
    @NotBlank(message = "消费地点不能为空")
    private String consumptionArea;
    @NotBlank(message = "消费金额不能为空")
    private Integer consumptionAmount;
    @NotBlank(message = "支付方式不能为空")
    private String payMethod;
    @NotBlank(message = "商品类别不能为空")
    private String commodityCategory;
    @NotBlank(message = "支付时间不能为空")
    private Integer payTime;
}
