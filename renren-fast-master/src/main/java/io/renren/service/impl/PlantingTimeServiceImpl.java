package io.renren.service.impl;

import io.renren.dao.PlantingMethodDao;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.PlantingTimeDao;
import io.renren.entity.PlantingTimeEntity;
import io.renren.service.PlantingTimeService;

import javax.annotation.Resource;


@Service("plantingTimeService")
public class PlantingTimeServiceImpl extends ServiceImpl<PlantingTimeDao, PlantingTimeEntity> implements PlantingTimeService {

    @Resource
    private PlantingTimeDao plantingTimeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PlantingTimeEntity> page = this.page(
                new Query<PlantingTimeEntity>().getPage(params),
                new QueryWrapper<PlantingTimeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> getSeedtime(String seed_type) { //获取某种子的所有种植月份对应的生长时间
        List<Map<String, Object>> info = plantingTimeDao.getSeedtime(seed_type);

        // 定义一个 Comparator 接口的实现，按照种植时间升序排列
        Comparator<Map<String, Object>> comparator = new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Object growthTime1 = o1.get("growth_time");
                Object growthTime2 = o2.get("growth_time");

                if (growthTime1 instanceof Date && growthTime2 instanceof Date) {
                    return ((Date) growthTime1).compareTo((Date) growthTime2);
                } else if (growthTime1 instanceof Long && growthTime2 instanceof Long) {
                    return Long.compare((Long) growthTime1, (Long) growthTime2);
                } else if (growthTime1 instanceof Integer && growthTime2 instanceof Integer) {
                    return Integer.compare((Integer) growthTime1, (Integer) growthTime2);
                } else {
                    // 如果 growth_time 不是 Date、Long 或 Integer 类型，则抛出异常
                    throw new ClassCastException("Unable to compare growth_time values of different types");
                }
            }
        };
        // 使用 Collections.sort() 方法进行排序
        Collections.sort(info, comparator);

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> item : info) {
            Map<String, Object> map = new HashMap<>();
            map.put("seed_type", item.get("seed_type"));
            map.put("month", item.get("month"));
            map.put("growth_time", item.get("growth_time"));
            result.add(map);
        }
        return result;
    }
}