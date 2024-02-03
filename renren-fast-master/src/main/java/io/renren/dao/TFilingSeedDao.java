package io.renren.dao;

import io.renren.entity.TFilingSeedEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author baligonglu
 * @email 2060736540@qq.com
 * @date 2023-11-06 19:35:31
 */
@Mapper
public interface TFilingSeedDao extends BaseMapper<TFilingSeedEntity> {
    List<TFilingSeedEntity> getWithImg(int page_cur, int page_capacity);

    @Select("select count(*) from t_filing_seed")
    Integer getCount();

    TFilingSeedEntity getById(Integer id);
    @Select("SELECT t_filing_seed.filing_seed_name as name, COUNT(t_filing_seed.filing_seed_name) as value\n" +
            "FROM t_filing_seed\n" +
            "GROUP BY name\n" +
            "ORDER BY value DESC\n" +
            "LIMIT 10;\n")
    List<Map<String,Object>> getProductPercentage();
}
