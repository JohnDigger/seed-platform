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

import io.renren.entity.SeedYieldEntity;
import io.renren.service.SeedYieldService;
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
@RequestMapping("seed-platform/seedyield")
public class SeedYieldController {
    @Autowired
    private SeedYieldService seedYieldService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:seedyield:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seedYieldService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:seedyield:info")
    public R info(@PathVariable("id") Integer id){
		SeedYieldEntity seedYield = seedYieldService.getById(id);

        return R.ok().put("seedYield", seedYield);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:seedyield:save")
    public R save(@RequestBody SeedYieldEntity seedYield){
		seedYieldService.save(seedYield);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:seedyield:update")
    public R update(@RequestBody SeedYieldEntity seedYield){
		seedYieldService.updateById(seedYield);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:seedyield:delete")
    public R delete(@RequestBody Integer[] ids){
		seedYieldService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/getYearyield") // 获取所有种子的年产量
    public R getYearyield(@RequestParam("year") Integer year){
        List<Map<String,Object>> data = seedYieldService.getYearyield(year);
        Map<String,Object> map = new HashMap<>();
        map.put("data", data);
        return R.ok(map);
    }

    @RequestMapping("/getSeedyield") // 获取某个种子的所有年产量
    public R getSeedyield(@RequestParam("seed_type") String seed_type){
        List<Map<String,Object>> data = seedYieldService.getSeedyield(seed_type);
        Map<String,Object> map = new HashMap<>();
        map.put("data", data);
        return R.ok(map);
    }
}
