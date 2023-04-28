package io.renren.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.SeedYieldDao;
import io.renren.entity.SeedYieldEntity;
import io.renren.service.SeedYieldService;

import javax.annotation.Resource;


@Service("seedYieldService")
public class SeedYieldServiceImpl extends ServiceImpl<SeedYieldDao, SeedYieldEntity> implements SeedYieldService {
    @Resource
    private SeedYieldDao seedYieldDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SeedYieldEntity> page = this.page(
                new Query<SeedYieldEntity>().getPage(params),
                new QueryWrapper<SeedYieldEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> getYearyield(Integer year) { // 获取所有种子某年产量
        List<Map<String, Object>> info = seedYieldDao.getYearyield(year);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> item : info) {
            Map<String, Object> map = new HashMap<>();
            map.put("seed_type", item.get("seed_type"));
            map.put("year", item.get("year"));
            map.put("yield", item.get("yield"));
            result.add(map);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getSeedyield(String seed_type) { // 获取年产量
        List<Map<String, Object>> info = seedYieldDao.getSeedyield(seed_type);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> item : info) {
            Map<String, Object> map = new HashMap<>();
            map.put("seed_type", item.get("seed_type"));
            map.put("year", item.get("year"));
            map.put("yield", item.get("yield"));
            result.add(map);
        }
        return result;
    }
}