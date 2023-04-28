package io.renren.service.impl;

import io.renren.dao.PlantingDensityDao;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.PlantingMethodDao;
import io.renren.entity.PlantingMethodEntity;
import io.renren.service.PlantingMethodService;

import javax.annotation.Resource;


@Service("plantingMethodService")
public class PlantingMethodServiceImpl extends ServiceImpl<PlantingMethodDao, PlantingMethodEntity> implements PlantingMethodService {
    @Resource
    private PlantingMethodDao plantingMethodDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PlantingMethodEntity> page = this.page(
                new Query<PlantingMethodEntity>().getPage(params),
                new QueryWrapper<PlantingMethodEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> getSeedmethod(String seed_type) { //查询某种子的地区种植种植方式占比
        List<Map<String, Object>> info = plantingMethodDao.getSeedmethod(seed_type);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> item : info) {
            Map<String, Object> map = new HashMap<>();
            map.put("seed_type", item.get("seed_type"));
            map.put("proportion", item.get("proportion"));
            map.put("method", item.get("method"));
            result.add(map);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getSeedvalue(String seed_type) { //查询种子的种植方式对应的产量和收入
        List<Map<String, Object>> info = plantingMethodDao.getSeedvalue(seed_type);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> item : info) {
            Map<String, Object> map = new HashMap<>();
            map.put("seed_type", item.get("seed_type"));
            map.put("yield", item.get("yield"));
            map.put("income", item.get("income"));
            map.put("method", item.get("method"));
            result.add(map);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getSeedinfo() { // 返回种子信息
        List<Map<String, Object>> info = plantingMethodDao.getSeedinfo();
        Set<String> nameSet = new HashSet<>(); // 使用 Set 存储不重复的 name 值
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> item : info) {
            String name = (String) item.get("name");
            // 如果当前 name 值不存在于 Set 中，则将其加入 Set 和结果集中
            if (!nameSet.contains(name)) {
                nameSet.add(name);
                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                result.add(map);
            }
        }

        return result;
    }
}