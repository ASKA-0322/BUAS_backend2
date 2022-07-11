package com.buas_team.buas_backend2.vo;

import jnr.ffi.annotations.In;
import lombok.Data;

import java.io.Serializable;

@Data
public class RiskAgeVO implements Serializable {
    private Integer age;
    private Integer grade;
}
