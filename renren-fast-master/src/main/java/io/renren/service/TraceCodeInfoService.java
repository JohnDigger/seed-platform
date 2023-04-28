package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.TraceCodeInfoEntity;
import io.renren.entity.TraceCodeInfoEntity;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-23 21:36:46
 */
public interface TraceCodeInfoService extends IService<TraceCodeInfoEntity> {

    Integer getTraceCodeCount();
    List<Map<String,Integer>> getTraceCodeCountByYear();
    List<Map<String,Integer>> getTraceCodeCountByWarningLevel();

    PageUtils queryPage(Map<String, Object> params);
}

