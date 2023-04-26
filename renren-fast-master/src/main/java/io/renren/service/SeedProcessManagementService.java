package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.SeedProcessManagementEntity;
import io.renren.entity.SeedProcessManagementEntity;

import java.util.Map;

/**
 * 种子全流程信息表
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-25 21:39:34
 */
public interface SeedProcessManagementService extends IService<SeedProcessManagementEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

