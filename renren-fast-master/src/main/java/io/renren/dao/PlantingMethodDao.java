package io.renren.dao;

import io.renren.entity.PlantingMethodEntity;
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
 * @date 2023-04-27 15:12:02
 */
@Mapper
public interface PlantingMethodDao extends BaseMapper<PlantingMethodEntity> {
    @Select("select * from planting_method where seed_type=#{seed_type}") // 查询某种子的地区种植种植方式占比
    List<Map<String, Object>> getSeedmethod(@Param("seed_type") String seed_type);

    @Select("select * from planting_method where seed_type=#{seed_type}") // 查询种子的种植方式对应的产量和收入
    List<Map<String, Object>> getSeedvalue(@Param("seed_type") String seed_type);

    @Select("select * from seed_process_management")
    List<Map<String, Object>> getSeedinfo();  // 查询种子信息
}
