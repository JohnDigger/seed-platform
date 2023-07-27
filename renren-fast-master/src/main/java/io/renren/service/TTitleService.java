package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.TTitleEntity;

import java.util.Map;

/**
 * 
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-07-27 19:11:30
 */
public interface TTitleService extends IService<TTitleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

