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

import io.renren.entity.PlantingMethodEntity;
import io.renren.service.PlantingMethodService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-28 10:29:39
 */
@RestController
@RequestMapping("seed-platform/plantingmethod")
public class PlantingMethodController {
    @Autowired
    private PlantingMethodService plantingMethodService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = plantingMethodService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        PlantingMethodEntity plantingMethod = plantingMethodService.getById(id);

        return R.ok().put("plantingMethod", plantingMethod);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PlantingMethodEntity plantingMethod){
        plantingMethodService.save(plantingMethod);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PlantingMethodEntity plantingMethod){
        plantingMethodService.updateById(plantingMethod);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        plantingMethodService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/getSeedmethod") // 获取某个种子种植方式的占比方式
    public R getSeedmethod(@RequestParam("seed_type") String seed_type){
        List<Map<String,Object>> data = plantingMethodService.getSeedmethod(seed_type);
        Map<String,Object> map = new HashMap<>();
        map.put("data", data);
        return R.ok(map);
    }

    @RequestMapping("/getSeedvalue") // 查询种子的种植方式对应的产量和收入
    public R getSeedvalue(@RequestParam("seed_type") String seed_type){
        List<Map<String,Object>> data = plantingMethodService.getSeedvalue(seed_type);
        Map<String,Object> map = new HashMap<>();
        map.put("data", data);
        return R.ok(map);
    }

    @RequestMapping("/getSeedinfo") // 获取所有种子
    public R getSeedinfo(){
        List<Map<String,Object>> data = plantingMethodService.getSeedinfo();
        Map<String,Object> map = new HashMap<>();
        map.put("data", data);
        return R.ok(map);
    }


}
