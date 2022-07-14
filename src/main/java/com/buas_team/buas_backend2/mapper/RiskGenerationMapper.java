package com.buas_team.buas_backend2.mapper;

import com.buas_team.buas_backend2.vo.RiskGenerationVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RiskGenerationMapper {
    @Select("select t.g generation,sum(if(grade=0,1,0)) low,sum(if(grade=1,1,0)) mid,sum(if(grade=2,1,0)) high " +
            "from   " +
            "(SELECT " +
            "case when age >=18 and age <30 then \"18-30\" " +
            "when age >=30 and age <40 then \"30-40\" " +
            "when age >=40 and age<50 then \"40-50\" " +
            "when age >=50 and age<60 then \"50-60\" " +
            "when age >=60 and age<70 then \"60-70\" " +
            "when age >=70 and age<=80 then \"70-80\" end as g,grade " +
            "from risk_assess,(select distinct user_id,age from bankuser) b  " +
            "where b.user_id = risk_assess.user_id) t  " +
            "group by t.g")
    List<RiskGenerationVO> getAll();
}
