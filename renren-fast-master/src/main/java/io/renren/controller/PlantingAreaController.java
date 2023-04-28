package io.renren.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.PlantingAreaEntity;
import io.renren.service.PlantingAreaService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-28 11:26:23
 */
@RestController
@RequestMapping("seed-platform/plantingarea")
public class PlantingAreaController {
    @Autowired
    private PlantingAreaService plantingAreaService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:plantingarea:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = plantingAreaService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:plantingarea:info")
    public R info(@PathVariable("id") Integer id){
        PlantingAreaEntity plantingArea = plantingAreaService.getById(id);

        return R.ok().put("plantingArea", plantingArea);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:plantingarea:save")
    public R save(@RequestBody PlantingAreaEntity plantingArea){
        plantingAreaService.save(plantingArea);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:plantingarea:update")
    public R update(@RequestBody PlantingAreaEntity plantingArea){
        plantingAreaService.updateById(plantingArea);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:plantingarea:delete")
    public R delete(@RequestBody Integer[] ids){
        plantingAreaService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/getYeararea") // 获取某个种子的种植总面积
    public R getYearyarea(@RequestParam("seed_type") String seed_type){
        List<Map<String,Object>> data = plantingAreaService.getYeararea(seed_type);
        Map<String,Object> map = new HashMap<>();
        map.put("data", data);
        return R.ok(map);
    }

    @RequestMapping("/getSeedregionvalue") // 获取某个种子在各个地区的产量以及收入
    public R getSeedregionvalue(@RequestParam("seed_type") String seed_type, @RequestParam("year") String year){
        List<Map<String,Object>> data = plantingAreaService.getSeedregionvalue(seed_type, year);
        Map<String,Object> map = new HashMap<>();
        map.put("data", data);
        return R.ok(map);
    }
}
