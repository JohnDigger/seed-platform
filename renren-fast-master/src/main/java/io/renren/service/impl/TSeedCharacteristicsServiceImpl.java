package io.renren.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.TSeedCharacteristicsDao;
import io.renren.entity.TSeedCharacteristicsEntity;
import io.renren.service.TSeedCharacteristicsService;

import javax.annotation.Resource;


@Service("tSeedCharacteristicsService")
public class TSeedCharacteristicsServiceImpl extends ServiceImpl<TSeedCharacteristicsDao, TSeedCharacteristicsEntity> implements TSeedCharacteristicsService {
    @Resource
    private TSeedCharacteristicsDao tSeedCharacteristicsDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TSeedCharacteristicsEntity> page = this.page(
                new Query<TSeedCharacteristicsEntity>().getPage(params),
                new QueryWrapper<TSeedCharacteristicsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> getThisYear() {
        return tSeedCharacteristicsDao.getThisYear();
    }

    @Override
    public List<Map<String, Object>> getLastYear() {
        return tSeedCharacteristicsDao.getLastYear();
    }

    @Override
    public List<Map<String, Object>> getProductPercentage() {
        return tSeedCharacteristicsDao.getProductPercentage();
    }

    @Override
    public TSeedCharacteristicsEntity selectSeedByName(String seedName) {
        return tSeedCharacteristicsDao.selectSeedByName(seedName);
    }

    @Override
    public List<TSeedCharacteristicsEntity> getAllSeed() {
        return tSeedCharacteristicsDao.getAllSeed();
    }

}
