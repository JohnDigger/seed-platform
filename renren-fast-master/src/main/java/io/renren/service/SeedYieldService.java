package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.SeedYieldEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-26 04:22:39
 */
public interface SeedYieldService extends IService<SeedYieldEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<Map<String,Object>> getYearyield(Integer year); // 获取年产量信息
    List<Map<String,Object>> getSeedyield(String seed_type); // 获取某个种子的所有产量信息。

}

