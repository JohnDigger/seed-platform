package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.TMatureSeedCharacteristicsEntity;

import java.util.Map;

/**
 * 
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-18 21:59:40
 */
public interface TMatureSeedCharacteristicsService extends IService<TMatureSeedCharacteristicsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

