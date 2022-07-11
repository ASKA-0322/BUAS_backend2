package com.buas_team.buas_backend2.vo;

import jnr.ffi.annotations.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class ConsumRiskVO implements Serializable {
    private Integer grade;
    private Integer amount;
}
