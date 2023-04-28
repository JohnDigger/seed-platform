package io.renren.dao;

import io.renren.entity.SeedYieldEntity;
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
public interface SeedYieldDao extends BaseMapper<SeedYieldEntity> {
    @Select("select * from seed_yield where year=#{year}") //返回特定年限的数据
    List<Map<String,Object>> getYearyield(@Param("year") int year);
    @Select("select * from seed_yield where seed_type=#{seed_type}")
    List<Map<String,Object>> getSeedyield(@Param("seed_type") String seed_type); //返回某个种子所有年份的产量
}
