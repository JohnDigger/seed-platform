package io.renren.service.impl;

import io.renren.dao.TraceInfoDao;
import io.renren.entity.TraceInfoEntity;
import io.renren.service.TraceInfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TraceInfoDao;
import io.renren.entity.TraceInfoEntity;
import io.renren.service.TraceInfoService;


@Service("traceInfoService")
public class TraceInfoServiceImpl extends ServiceImpl<TraceInfoDao, TraceInfoEntity> implements TraceInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TraceInfoEntity> page = this.page(
                new Query<TraceInfoEntity>().getPage(params),
                new QueryWrapper<TraceInfoEntity>()
        );

        return new PageUtils(page);
    }

}
