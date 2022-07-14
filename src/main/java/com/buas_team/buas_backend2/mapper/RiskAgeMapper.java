package com.buas_team.buas_backend2.mapper;

import com.buas_team.buas_backend2.vo.RiskAgeVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RiskAgeMapper {
    @Select("SELECT age,index_mark " +
            "FROM risk_assess,(select distinct user_id,age from bankuser) t " +
            "where t.user_id = risk_assess.user_id")
    List<RiskAgeVO> getAll();
}
