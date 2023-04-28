package io.renren.service.impl;

import io.renren.dao.PlantingAreaDao;
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

import io.renren.dao.PlantingDensityDao;
import io.renren.entity.PlantingDensityEntity;
import io.renren.service.PlantingDensityService;

import javax.annotation.Resource;


@Service("plantingDensityService")
public class PlantingDensityServiceImpl extends ServiceImpl<PlantingDensityDao, PlantingDensityEntity> implements PlantingDensityService {
    @Resource
    private PlantingDensityDao plantingDensityDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PlantingDensityEntity> page = this.page(
                new Query<PlantingDensityEntity>().getPage(params),
                new QueryWrapper<PlantingDensityEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> getSeeddensity(String seed_type) { //获取种子的种植密度
        List<Map<String, Object>> info = plantingDensityDao.getSeeddensity(seed_type);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> item : info) {
            Map<String, Object> map = new HashMap<>();
            map.put("seed_type", item.get("seed_type"));
            map.put("region", item.get("region"));
            map.put("density", item.get("density"));
            result.add(map);
        }
        return result;
    }

}