package io.renren.dao;

import io.renren.entity.TraceInfoEntity;
import io.renren.entity.TraceInfoEntity;
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
 * @date 2023-04-23 21:36:46
 */
@Mapper
public interface TraceInfoDao extends BaseMapper<TraceInfoEntity> {
	@Select("SELECT COUNT(*) FROM Trace_info;")
	Integer getTraceConut();

	@Select("SELECT YEAR(trace_date) AS year, COUNT(*) AS count\n" +
		"FROM Trace_info\n" +
		"GROUP BY YEAR(trace_date);")
	List<Map<String,Integer>> getTraceCountByYear();

	@Select("SELECT YEAR(trace_date) " +
		"AS year, " +
		"IFNULL(COUNT(*), 0) AS count\n" +
		"FROM trace_info\n" +
		"WHERE trace_seed_name = #{name}"+
		"AND trace_date >= DATE_SUB(CURDATE(), " +
		"INTERVAL 9 YEAR)\n" +
		"GROUP BY year\n" +
		"ORDER BY year ASC;")
	List<Map<String,Integer>> getTraceCountByNY(String name);

	@Select("SELECT trace_area " +
		"FROM trace_info " +
		"WHERE trace_seed_name = #{area};")
	List<String> getTraceAreaByName(String area);

	@Select("SELECT DISTINCT trace_seed_name " +
		"FROM trace_info;")
	List<String> getTraceSeedName();
	@Select("SELECT DATE(trace_info.trace_date) AS date, COUNT(*) AS count FROM trace_info\n" +
			"WHERE trace_info.trace_date BETWEEN (\n" +
			"  SELECT MIN(trace_info.trace_date) FROM (\n" +
			"    SELECT trace_info.trace_date FROM trace_info\n" +
			"    WHERE trace_info.trace_date <= CURDATE()\n" +
			"    ORDER BY trace_info.trace_date DESC\n" +
			"    LIMIT 7\n" +
			"  ) AS t\n" +
			") AND CURDATE()\n" +
			"GROUP BY DATE(trace_info.trace_date)\n" +
			"ORDER BY DATE(trace_info.trace_date) LIMIT 7\n" +
			"\n")
	List<Map<String,Object>> getRecentSeven();

}
