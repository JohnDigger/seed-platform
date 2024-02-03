package io.renren.dao;

import io.renren.entity.TImageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author baligonglu
 * @email 2060736540@qq.com
 * @date 2023-11-06 20:50:35
 */
@Mapper
public interface TImageDao extends BaseMapper<TImageEntity> {
    int InsertImage(TImageEntity tImageEntity);
}
