package io.renren.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.SeedDao;
import io.renren.entity.SeedEntity;
import io.renren.service.SeedService;


@Service("seedService")
public class SeedServiceImpl extends ServiceImpl<SeedDao, SeedEntity> implements SeedService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeedEntity> page = this.page(
                new Query<SeedEntity>().getPage(params),
                new QueryWrapper<SeedEntity>()
        );

        return new PageUtils(page);
    }

}