package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TImageDao;
import io.renren.entity.TImageEntity;
import io.renren.service.TImageService;


@Service("tImageService")
public class TImageServiceImpl extends ServiceImpl<TImageDao, TImageEntity> implements TImageService {
    @Autowired
    private TImageDao tImageDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TImageEntity> page = this.page(
                new Query<TImageEntity>().getPage(params),
                new QueryWrapper<TImageEntity>()
        );

        return new PageUtils(page);
    }
    @Override
    public int returnIdForInsert(TImageEntity tImageEntity) {
        int result = tImageDao.InsertImage(tImageEntity);
        return result;
    }

}