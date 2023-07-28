package io.renren.dao;

import io.renren.entity.TraceSeedInfoEntity;
import io.renren.entity.TraceSeedInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
public interface TraceSeedInfoDao extends BaseMapper<TraceSeedInfoEntity> {
	@Select("SELECT * from trace_seed_info\n" +
		"WHERE seed_trace_code = #{code};")
	List<Map<String,String>> getSeedInfoByCode(String code);

	@Update("UPDATE trace_seed_info\n" +
		"SET seed_trace_code = #{code}\n" +
		"WHERE seed_name = #{name} AND seed_producer = #{producer};")
	int updateTraceCode(String name,String producer,String code,String filePath);

}
