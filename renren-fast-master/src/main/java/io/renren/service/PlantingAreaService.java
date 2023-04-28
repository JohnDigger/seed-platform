package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.PlantingAreaEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-26 04:22:39
 */
public interface PlantingAreaService extends IService<PlantingAreaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<Map<String,Object>> getYeararea(String seed_type); // 获取年产量信息
    List<Map<String,Object>> getSeedregionvalue(String seed_type, String year); // 获取种子在各个地区的产量与收入

}

