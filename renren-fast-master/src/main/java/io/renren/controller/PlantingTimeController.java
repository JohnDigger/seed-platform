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

import io.renren.entity.PlantingTimeEntity;
import io.renren.service.PlantingTimeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-26 04:22:39
 */
@RestController
@RequestMapping("seed-platform/plantingtime")
public class PlantingTimeController {
    @Autowired
    private PlantingTimeService plantingTimeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:plantingtime:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = plantingTimeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:plantingtime:info")
    public R info(@PathVariable("id") Integer id){
		PlantingTimeEntity plantingTime = plantingTimeService.getById(id);

        return R.ok().put("plantingTime", plantingTime);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:plantingtime:save")
    public R save(@RequestBody PlantingTimeEntity plantingTime){
		plantingTimeService.save(plantingTime);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:plantingtime:update")
    public R update(@RequestBody PlantingTimeEntity plantingTime){
		plantingTimeService.updateById(plantingTime);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:plantingtime:delete")
    public R delete(@RequestBody Integer[] ids){
		plantingTimeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/getSeedtime") // 查询某种子的种植月份对应的成熟时间
    public R getSeedtime(@RequestParam("seed_type") String seed_type){
        List<Map<String,Object>> data = plantingTimeService.getSeedtime(seed_type);
        Map<String,Object> map = new HashMap<>();
        map.put("data", data);
        return R.ok(map);
    }
}
