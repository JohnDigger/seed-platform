package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.TraceInfoEntity;
import io.renren.entity.TraceInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-23 21:36:46
 */
public interface TraceInfoService extends IService<TraceInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

