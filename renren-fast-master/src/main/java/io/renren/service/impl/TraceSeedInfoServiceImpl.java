package io.renren.service.impl;

import io.renren.dao.TraceSeedInfoDao;
import io.renren.entity.TraceSeedInfoEntity;
import io.renren.service.TraceSeedInfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TraceSeedInfoDao;
import io.renren.entity.TraceSeedInfoEntity;
import io.renren.service.TraceSeedInfoService;


@Service("traceSeedInfoService")
public class TraceSeedInfoServiceImpl extends ServiceImpl<TraceSeedInfoDao, TraceSeedInfoEntity> implements TraceSeedInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TraceSeedInfoEntity> page = this.page(
                new Query<TraceSeedInfoEntity>().getPage(params),
                new QueryWrapper<TraceSeedInfoEntity>()
        );

        return new PageUtils(page);
    }

}
