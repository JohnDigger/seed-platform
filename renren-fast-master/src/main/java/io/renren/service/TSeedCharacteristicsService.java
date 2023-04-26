package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.TSeedCharacteristicsEntity;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-18 21:59:39
 */
public interface TSeedCharacteristicsService extends IService<TSeedCharacteristicsEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<Map<String,Object>> getThisYear();
    List<Map<String,Object>> getLastYear();
    List<Map<String,Object>> getProductPercentage();
    TSeedCharacteristicsEntity selectSeedByName(String seedName);
    List<TSeedCharacteristicsEntity> getAllSeed();
}

