package com.buas_team.buas_backend2.vo;

import jnr.ffi.annotations.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class RiskSexVO implements Serializable {
    private String sex;
    private Integer low;
    private Integer mid;
    private Integer high;
}
