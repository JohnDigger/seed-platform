package io.renren.dao;

import io.renren.entity.TSeedCharacteristicsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-18 21:59:39
 */
@Mapper
public interface TSeedCharacteristicsDao extends BaseMapper<TSeedCharacteristicsEntity> {
	@Select("SELECT \n" +
            "  COUNT(*) AS countData, \n" +
            "  DATE_FORMAT(create_time, '%Y-%m') AS yearMonth \n" +
            "FROM \n" +
            "  t_seed_characteristics\n" +
            "WHERE \n" +
            "  YEAR(create_time) = YEAR(NOW())\n" +
            "GROUP BY \n" +
            "  DATE_FORMAT(create_time, '%Y-%m')\n" +
            "ORDER BY \n" +
            "  yearMonth ASC;")
    List<Map<String,Object>> getThisYear();
	@Select("SELECT \n" +
            "  COUNT(*) AS countData, \n" +
            "  DATE_FORMAT(create_time, '%Y-%m') AS yearMonth \n" +
            "FROM \n" +
            "  t_seed_characteristics\n" +
            "WHERE \n" +
            "  YEAR(create_time) = YEAR(NOW()) - 1\n" +
            "GROUP BY \n" +
            "  DATE_FORMAT(create_time, '%Y-%m')\n" +
            "ORDER BY \n" +
            "  yearMonth ASC;")
    List<Map<String,Object>> getLastYear();
	@Select("SELECT\n" +
            "\tseed_color AS name,\n" +
            "\tCOUNT(*) AS \n" +
            "value\n" +
            "\t\n" +
            "FROM\n" +
            "\tt_seed_characteristics \n" +
            "GROUP BY\n" +
            "name \n" +
            "ORDER BY\n" +
            "\t\n" +
            "value\n" +
            "DESC \n" +
            "\tLIMIT 10;")
    List<Map<String,Object>> getProductPercentage();

	TSeedCharacteristicsEntity selectSeedByName(String seedName);

	@Select("SELECT t_seed_characteristics.seed_name FROM t_seed_characteristics ")
	List<TSeedCharacteristicsEntity> getAllSeed();
}
