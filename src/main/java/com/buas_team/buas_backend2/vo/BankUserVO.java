package com.buas_team.buas_backend2.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BankUserVO implements Serializable {
    Integer userId;
    String name;
    String area;
}
