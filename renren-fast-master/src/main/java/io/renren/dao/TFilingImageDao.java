package io.renren.dao;

import io.renren.entity.TFilingImageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author baligonglu
 * @email 2060736540@qq.com
 * @date 2023-11-06 20:50:36
 */
@Mapper
public interface TFilingImageDao extends BaseMapper<TFilingImageEntity> {
    List<TFilingImageEntity> queryByFilingId(int filingId);

    int updateFilingImage(int image_id,int id);

    int insertById(int image_id,int filing_id);
}
