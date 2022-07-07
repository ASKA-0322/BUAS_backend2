package com.buas_team.buas_backend2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buas_team.buas_backend2.vo.AreaCateVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AreaCateVOMapper extends BaseMapper<AreaCateVO> {
    @Select("select commodity_category,count(id) amount " +
            "from bankuser " +
            "where consumption_area= #{consumption_area} " +
            "group by commodity_category ")
    List<AreaCateVO> getAll(@Param("consumption_area") String consumption_area);
}
