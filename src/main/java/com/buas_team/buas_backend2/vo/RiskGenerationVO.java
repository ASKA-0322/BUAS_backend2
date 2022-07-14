package com.buas_team.buas_backend2.vo;

import jnr.ffi.annotations.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class RiskGenerationVO implements Serializable {
    private String generation ;
    private Integer low;
    private Integer mid;
    private Integer high;
}
