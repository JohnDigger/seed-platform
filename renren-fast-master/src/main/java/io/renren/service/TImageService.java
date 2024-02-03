package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.TImageEntity;

import java.util.Map;

/**
 * 
 *
 * @author baligonglu
 * @email 2060736540@qq.com
 * @date 2023-11-06 20:50:35
 */
public interface TImageService extends IService<TImageEntity> {

    PageUtils queryPage(Map<String, Object> params);
    int returnIdForInsert(TImageEntity tImageEntity);
}

