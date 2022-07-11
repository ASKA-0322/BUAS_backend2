package com.buas_team.buas_backend2.mapper;

import com.buas_team.buas_backend2.vo.ConsumRiskVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ConsumRiskVOMapper {
    @Select("select grade,count(user_id) amount " +
            "from risk_assess " +
            "group by grade")
    List<ConsumRiskVO> getAll();
}
