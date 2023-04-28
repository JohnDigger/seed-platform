package io.renren.dao;

import io.renren.entity.TraceCodeInfoEntity;
import io.renren.entity.TraceCodeInfoEntity;
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
public interface TraceCodeInfoDao extends BaseMapper<TraceCodeInfoEntity> {

	@Select("SELECT count(*) FROM trace_code_info;")
	Integer getTraceCodeCount();

	@Select("SELECT YEAR(create_time) AS year, " +
		"COUNT(*) AS count FROM trace_code_info " +
		"GROUP BY YEAR(create_time);")
	List<Map<String,Integer>> getTraceCodeCountByYear();

	@Select("SELECT warning_level, " +
		"COUNT(*) AS count FROM trace_code_info " +
		"GROUP BY warning_level;")
	List<Map<String,Integer>> getTraceCodeCountByWarningLevel();

}
