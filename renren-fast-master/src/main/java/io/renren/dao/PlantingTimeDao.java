package io.renren.dao;

import io.renren.entity.PlantingTimeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-26 04:22:39
 */
@Mapper
public interface PlantingTimeDao extends BaseMapper<PlantingTimeEntity> {
    @Select("select * from planting_time where seed_type=#{seed_type}") // 查询某种子的种植月份对应的成熟时间
    List<Map<String, Object>> getSeedtime(@Param("seed_type") String seed_type);
}
