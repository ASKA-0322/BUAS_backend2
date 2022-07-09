package com.buas_team.buas_backend2.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AreaCateListVO implements Serializable {
    private String province;
    private List<AreaCateVO> areaCateList;
}
