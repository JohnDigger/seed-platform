package io.renren.service.impl;

import io.renren.dao.SeedProcessManagementDao;
import io.renren.entity.SeedProcessManagementEntity;
import io.renren.service.SeedProcessManagementService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.SeedProcessManagementDao;
import io.renren.entity.SeedProcessManagementEntity;
import io.renren.service.SeedProcessManagementService;


@Service("seedProcessManagementService")
public class SeedProcessManagementServiceImpl extends ServiceImpl<SeedProcessManagementDao, SeedProcessManagementEntity> implements SeedProcessManagementService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeedProcessManagementEntity> page = this.page(
                new Query<SeedProcessManagementEntity>().getPage(params),
                new QueryWrapper<SeedProcessManagementEntity>()
        );

        return new PageUtils(page);
    }

}
