package io.renren.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TSeedGrowEnvironmentDao;
import io.renren.entity.TSeedGrowEnvironmentEntity;
import io.renren.service.TSeedGrowEnvironmentService;


@Service("tSeedGrowEnvironmentService")
public class TSeedGrowEnvironmentServiceImpl extends ServiceImpl<TSeedGrowEnvironmentDao, TSeedGrowEnvironmentEntity> implements TSeedGrowEnvironmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TSeedGrowEnvironmentEntity> page = this.page(
                new Query<TSeedGrowEnvironmentEntity>().getPage(params),
                new QueryWrapper<TSeedGrowEnvironmentEntity>()
        );

        return new PageUtils(page);
    }

}