package io.renren.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TPlantCharacteristicsDao;
import io.renren.entity.TPlantCharacteristicsEntity;
import io.renren.service.TPlantCharacteristicsService;


@Service("tPlantCharacteristicsService")
public class TPlantCharacteristicsServiceImpl extends ServiceImpl<TPlantCharacteristicsDao, TPlantCharacteristicsEntity> implements TPlantCharacteristicsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TPlantCharacteristicsEntity> page = this.page(
                new Query<TPlantCharacteristicsEntity>().getPage(params),
                new QueryWrapper<TPlantCharacteristicsEntity>()
        );

        return new PageUtils(page);
    }

}