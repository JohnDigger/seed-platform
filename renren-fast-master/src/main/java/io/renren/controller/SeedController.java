package io.renren.controller;


import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.entity.SeedEntity;
import io.renren.service.SeedService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-18 21:59:40
 */
@RestController
@RequestMapping("seed-platform/seed")
public class SeedController {
    @Autowired
    private SeedService seedService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:seed:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seedService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{seedId}")
    @RequiresPermissions("seed-platform:seed:info")
    public R info(@PathVariable("seedId") Integer seedId){
		SeedEntity seed = seedService.getById(seedId);

        return R.ok().put("seed", seed);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:seed:save")
    public R save(@RequestBody SeedEntity seed){
		seedService.save(seed);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:seed:update")
    public R update(@RequestBody SeedEntity seed){
		seedService.updateById(seed);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:seed:delete")
    public R delete(@RequestBody Integer[] seedIds){
		seedService.removeByIds(Arrays.asList(seedIds));

        return R.ok();
    }

}
