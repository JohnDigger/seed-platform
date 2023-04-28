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

import io.renren.entity.PlantingDensityEntity;
import io.renren.service.PlantingDensityService;
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
@RequestMapping("seed-platform/plantingdensity")
public class PlantingDensityController {
    @Autowired
    private PlantingDensityService plantingDensityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:plantingdensity:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = plantingDensityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:plantingdensity:info")
    public R info(@PathVariable("id") Integer id){
		PlantingDensityEntity plantingDensity = plantingDensityService.getById(id);

        return R.ok().put("plantingDensity", plantingDensity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:plantingdensity:save")
    public R save(@RequestBody PlantingDensityEntity plantingDensity){
		plantingDensityService.save(plantingDensity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:plantingdensity:update")
    public R update(@RequestBody PlantingDensityEntity plantingDensity){
		plantingDensityService.updateById(plantingDensity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:plantingdensity:delete")
    public R delete(@RequestBody Integer[] ids){
		plantingDensityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/getSeeddensity") // 获取种子某年的种植密度
    public R getSeeddensity(@RequestParam("seed_type") String seed_type){
        List<Map<String,Object>> data = plantingDensityService.getSeeddensity(seed_type);
        Map<String,Object> map = new HashMap<>();
        map.put("data", data);
        return R.ok(map);
    }
}
