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

}
