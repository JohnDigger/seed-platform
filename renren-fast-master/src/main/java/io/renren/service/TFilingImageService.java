package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.TFilingImageEntity;

import java.util.Map;

/**
 * 
 *
 * @author baligonglu
 * @email 2060736540@qq.com
 * @date 2023-11-06 20:50:36
 */
public interface TFilingImageService extends IService<TFilingImageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

