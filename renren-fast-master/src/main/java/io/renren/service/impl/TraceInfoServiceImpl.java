package io.renren.service.impl;

import io.renren.dao.TraceInfoDao;
import io.renren.entity.TraceInfoEntity;
import io.renren.service.TraceInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TraceInfoDao;
import io.renren.entity.TraceInfoEntity;
import io.renren.service.TraceInfoService;

import javax.annotation.Resource;


@Service("traceInfoService")
public class TraceInfoServiceImpl extends ServiceImpl<TraceInfoDao, TraceInfoEntity> implements TraceInfoService {

    @Resource
    private TraceInfoDao traceInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TraceInfoEntity> page = this.page(
                new Query<TraceInfoEntity>().getPage(params),
                new QueryWrapper<TraceInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> getRecentSeven() {
        return traceInfoDao.getRecentSeven();
    }

    @Override
    public Integer getTraceCount(){
        return traceInfoDao.getTraceConut();
    }

    @Override
    public List<Map<String,Integer>> getTraceCountByYear(){
        return traceInfoDao.getTraceCountByYear();
    }

    @Override
    public List<Map<String, Integer>> getTraceCountByNY(String name) {
        return traceInfoDao.getTraceCountByNY(name);
    }

    @Override
    public List<String> getTraceAreaByName(String name) {
        return traceInfoDao.getTraceAreaByName(name);
    }

    @Override
    public List<String> getTraceSeedName() {
        return traceInfoDao.getTraceSeedName();
    }
}
