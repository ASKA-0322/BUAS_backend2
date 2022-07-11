package com.buas_team.buas_backend2.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserInfoVO implements Serializable {
    private String consumptionArea;
    private String payMethod;
    private Integer payTime;
    private String CommodityCategory;
}
