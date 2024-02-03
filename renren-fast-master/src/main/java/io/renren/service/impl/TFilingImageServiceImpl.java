package io.renren.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TFilingImageDao;
import io.renren.entity.TFilingImageEntity;
import io.renren.service.TFilingImageService;


@Service("tFilingImageService")
public class TFilingImageServiceImpl extends ServiceImpl<TFilingImageDao, TFilingImageEntity> implements TFilingImageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TFilingImageEntity> page = this.page(
                new Query<TFilingImageEntity>().getPage(params),
                new QueryWrapper<TFilingImageEntity>()
        );

        return new PageUtils(page);
    }

}