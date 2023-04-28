package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.PlantingTimeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-26 04:22:39
 */
public interface PlantingTimeService extends IService<PlantingTimeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<Map<String,Object>> getSeedtime(String seed_type); // 获取某种子的所有种植月份对应的生长时间
}

