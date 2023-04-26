package io.renren.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.entity.TSeedCharacteristicsEntity;
import io.renren.service.TSeedCharacteristicsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 *
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-18 21:59:39
 */
@RestController
@RequestMapping("seed-platform/tseedcharacteristics")
public class TSeedCharacteristicsController {
    @Autowired
    private TSeedCharacteristicsService tSeedCharacteristicsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:tseedcharacteristics:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = tSeedCharacteristicsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:tseedcharacteristics:info")
    public R info(@PathVariable("id") Long id){
		TSeedCharacteristicsEntity tSeedCharacteristics = tSeedCharacteristicsService.getById(id);

        return R.ok().put("tSeedCharacteristics", tSeedCharacteristics);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:tseedcharacteristics:save")
    public R save(@RequestBody TSeedCharacteristicsEntity tSeedCharacteristics){
		tSeedCharacteristicsService.save(tSeedCharacteristics);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:tseedcharacteristics:update")
    public R update(@RequestBody TSeedCharacteristicsEntity tSeedCharacteristics){
		tSeedCharacteristicsService.updateById(tSeedCharacteristics);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:tseedcharacteristics:delete")
    public R delete(@RequestBody Long[] ids){
		tSeedCharacteristicsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/getThisYear")
    public R getThisYear(){
        Map<String,Object> map = new HashMap<>();
        map.put("data",tSeedCharacteristicsService.getThisYear());
        return R.ok(map);
    }
    @RequestMapping("/getLastYear")
    public R getLastYear(){
        Map<String,Object> map = new HashMap<>();
        map.put("data",tSeedCharacteristicsService.getLastYear());
        return R.ok(map);
    }
    @GetMapping("/getPercent")
    public R getPercent(){
        Map<String,Object> map = new HashMap<>();
        map.put("data",tSeedCharacteristicsService.getProductPercentage());
        return R.ok(map);
    }

    @GetMapping("/selectSeedByName")
    public R selectSeedByName(@RequestParam("seedName")String seedName){
        Map<String,Object> map = new HashMap<>();
        map.put("data",tSeedCharacteristicsService.selectSeedByName(seedName));
        return R.ok(map);
    }

    @GetMapping("/getAllSeedName")
    public R getAllSeedName(){
        Map<String,Object> map = new HashMap<>();
        map.put("data",tSeedCharacteristicsService.getAllSeed());
        return R.ok(map);
    }

}
