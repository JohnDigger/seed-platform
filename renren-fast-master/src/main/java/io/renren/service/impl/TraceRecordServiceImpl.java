package io.renren.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TraceRecordDao;
import io.renren.entity.TraceRecordEntity;
import io.renren.service.TraceRecordService;


@Service("traceRecordService")
public class TraceRecordServiceImpl extends ServiceImpl<TraceRecordDao, TraceRecordEntity> implements TraceRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TraceRecordEntity> page = this.page(
                new Query<TraceRecordEntity>().getPage(params),
                new QueryWrapper<TraceRecordEntity>()
        );

        return new PageUtils(page);
    }

}