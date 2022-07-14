package com.buas_team.buas_backend2.mapper;

import com.buas_team.buas_backend2.vo.RiskSexVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RiskSexMapper {
    @Select("select sex,sum(if(grade=0,1,0)) low,sum(if(grade=1,1,0)) mid,sum(if(grade=2,1,0)) high " +
            "from risk_assess,(select distinct user_id,sex from bankuser) t " +
            "where risk_assess.user_id=t.user_id " +
            "group by sex")
    List<RiskSexVO> getAll();
}
