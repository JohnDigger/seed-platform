package io.renren.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TMatureSeedCharacteristicsDao;
import io.renren.entity.TMatureSeedCharacteristicsEntity;
import io.renren.service.TMatureSeedCharacteristicsService;


@Service("tMatureSeedCharacteristicsService")
public class TMatureSeedCharacteristicsServiceImpl extends ServiceImpl<TMatureSeedCharacteristicsDao, TMatureSeedCharacteristicsEntity> implements TMatureSeedCharacteristicsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TMatureSeedCharacteristicsEntity> page = this.page(
                new Query<TMatureSeedCharacteristicsEntity>().getPage(params),
                new QueryWrapper<TMatureSeedCharacteristicsEntity>()
        );

        return new PageUtils(page);
    }

}