package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.PlantingDensityEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-26 04:22:39
 */
public interface PlantingDensityService extends IService<PlantingDensityEntity> {
    PageUtils queryPage(Map<String, Object> params);
    List<Map<String,Object>> getSeeddensity(String seed_type); // 获取某种子的种植密度
}

