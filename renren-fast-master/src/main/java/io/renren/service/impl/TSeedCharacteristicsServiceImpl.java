package io.renren.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TSeedCharacteristicsDao;
import io.renren.entity.TSeedCharacteristicsEntity;
import io.renren.service.TSeedCharacteristicsService;


@Service("tSeedCharacteristicsService")
public class TSeedCharacteristicsServiceImpl extends ServiceImpl<TSeedCharacteristicsDao, TSeedCharacteristicsEntity> implements TSeedCharacteristicsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TSeedCharacteristicsEntity> page = this.page(
                new Query<TSeedCharacteristicsEntity>().getPage(params),
                new QueryWrapper<TSeedCharacteristicsEntity>()
        );

        return new PageUtils(page);
    }

}