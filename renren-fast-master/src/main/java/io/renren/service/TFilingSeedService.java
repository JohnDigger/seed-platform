package io.renren.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.entity.TFilingImageEntity;
import io.renren.entity.TFilingSeedEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author baligonglu
 * @email 2060736540@qq.com
 * @date 2023-11-06 19:35:31
 */
public interface TFilingSeedService extends IService<TFilingSeedEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<TFilingSeedEntity> getAll(int page_cur, int page_size);

    int getCount();

    TFilingSeedEntity getByids(Integer id);

    List<TFilingImageEntity> queryProductImage(int filingId);

    int updateProductImageById(int image_id,int  id);

    int deleteById(int id);

    int insertById(int image_id,int filing_id);

    List<Map<String,Object>> getProductPercentage();
}

