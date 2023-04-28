package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.PlantingMethodEntity;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-27 15:12:02
 */
public interface PlantingMethodService extends IService<PlantingMethodEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<Map<String,Object>> getSeedmethod(String seed_type); // 查询某种子的地区种植种植方式占比
    List<Map<String,Object>> getSeedvalue(String seed_type); //查询种子的种植方式对应的产量和收入
    List<Map<String,Object>> getSeedinfo(); // 获取种子信息
}

