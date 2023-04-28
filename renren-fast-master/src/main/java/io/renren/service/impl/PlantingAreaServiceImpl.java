package io.renren.service.impl;

import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.dao.PlantingAreaDao;
import io.renren.entity.PlantingAreaEntity;
import io.renren.service.PlantingAreaService;

import javax.annotation.Resource;


@Service("plantingAreaService")
public class PlantingAreaServiceImpl extends ServiceImpl<PlantingAreaDao, PlantingAreaEntity> implements PlantingAreaService {
    @Resource
    private PlantingAreaDao plantingAreaDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PlantingAreaEntity> page = this.page(
                new Query<PlantingAreaEntity>().getPage(params),
                new QueryWrapper<PlantingAreaEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> getYeararea(String seed_type) { //获取种子的种植面积
        List<Map<String, Object>> info = plantingAreaDao.getYeararea(seed_type);
        Map<Integer, Double> yearAreaMap = new HashMap<>(); // 用于保存每个年份的总种植面积

        for (Map<String, Object> item : info) {
            int year = Integer.parseInt(item.get("year").toString());
            double area = Double.parseDouble(item.get("area").toString());

            // 如果该年份已经有种植面积数据，则将当前面积累加到已有面积上
            if (yearAreaMap.containsKey(year)) {
                double totalArea = yearAreaMap.get(year) + area;
                yearAreaMap.put(year, totalArea);
            } else {
                yearAreaMap.put(year, area);
            }
        }
        List<Map.Entry<Integer, Double>> sortedEntries = new ArrayList<>(yearAreaMap.entrySet());
        Collections.sort(sortedEntries, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        // 将结果转换为 List<Map<String, Object>> 形式返回
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : sortedEntries) { // 修改此处
            Map<String, Object> map = new HashMap<>();
            map.put("seed_type", seed_type);
            map.put("year", entry.getKey());
            map.put("area", String.format("%.2f", entry.getValue()));
            result.add(map);
        }

        return result;
    }

    @Override
    public List<Map<String, Object>> getSeedregionvalue(String seed_type, String year) {
        List<Map<String, Object>> info = plantingAreaDao.getSeedregionvalue(seed_type, year);
        info.sort((a, b) -> {
            double incomeA = Double.parseDouble((String) a.get("income"));
            double incomeB = Double.parseDouble((String) b.get("income"));
            return Double.compare(incomeB, incomeA);
        });
        return info;
    }
}