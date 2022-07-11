package com.buas_team.buas_backend2.mapper;

import com.buas_team.buas_backend2.vo.PreConsumVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PreConsumMapper {
    @Select("SELECT pay_time,sum(amount) amount " +
            "from area_consumption_target " +
            "group by pay_time")
    List<PreConsumVO> getAll();
}
