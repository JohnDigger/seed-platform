package io.renren.dao;

import io.renren.entity.PlantingDensityEntity;
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
public interface PlantingDensityDao extends BaseMapper<PlantingDensityEntity> {
    @Select("select * from planting_density where seed_type=#{seed_type}") // 查询某种子的地区种植密度
    List<Map<String, Object>> getSeeddensity(@Param("seed_type") String seed_type);

}
