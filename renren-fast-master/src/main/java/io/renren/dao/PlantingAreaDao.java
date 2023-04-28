package io.renren.dao;

import io.renren.entity.PlantingAreaEntity;
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
public interface PlantingAreaDao extends BaseMapper<PlantingAreaEntity> {
    @Select("select * from planting_area where seed_type=#{seed_type}") // 查询地区种植面积信息,根据种植类型查询
    List<Map<String, Object>> getYeararea(@Param("seed_type") String seed_type);

    @Select("select * from planting_area where seed_type=#{seed_type} and year=#{year}") // 查询某年地区种植面积信息,根据种植类型查询
    List<Map<String, Object>> getSeedregionvalue(@Param("seed_type") String seed_type, @Param("year") String year);
}
