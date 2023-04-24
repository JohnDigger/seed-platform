package io.renren.service.impl;

import io.renren.dao.TraceCodeInfoDao;
import io.renren.entity.TraceCodeInfoEntity;
import io.renren.service.TraceCodeInfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TraceCodeInfoDao;
import io.renren.entity.TraceCodeInfoEntity;
import io.renren.service.TraceCodeInfoService;


@Service("traceCodeInfoService")
public class TraceCodeInfoServiceImpl extends ServiceImpl<TraceCodeInfoDao, TraceCodeInfoEntity> implements TraceCodeInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TraceCodeInfoEntity> page = this.page(
                new Query<TraceCodeInfoEntity>().getPage(params),
                new QueryWrapper<TraceCodeInfoEntity>()
        );

        return new PageUtils(page);
    }

}
