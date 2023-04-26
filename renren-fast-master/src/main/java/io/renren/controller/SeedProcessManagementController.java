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

import io.renren.entity.SeedProcessManagementEntity;
import io.renren.service.SeedProcessManagementService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 种子全流程信息表
 *
 * @author jiajia
 * @email 541554971@qq.com
 * @date 2023-04-25 21:39:34
 */
@RestController
@RequestMapping("seed-platform/seedprocessmanagement")
public class SeedProcessManagementController {
    @Autowired
    private SeedProcessManagementService seedProcessManagementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("seed-platform:seedprocessmanagement:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = seedProcessManagementService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("seed-platform:seedprocessmanagement:info")
    public R info(@PathVariable("id") Integer id){
		SeedProcessManagementEntity seedProcessManagement = seedProcessManagementService.getById(id);

        return R.ok().put("seedProcessManagement", seedProcessManagement);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("seed-platform:seedprocessmanagement:save")
    public R save(@RequestBody SeedProcessManagementEntity seedProcessManagement){
		seedProcessManagementService.save(seedProcessManagement);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("seed-platform:seedprocessmanagement:update")
    public R update(@RequestBody SeedProcessManagementEntity seedProcessManagement){
		seedProcessManagementService.updateById(seedProcessManagement);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("seed-platform:seedprocessmanagement:delete")
    public R delete(@RequestBody Integer[] ids){
		seedProcessManagementService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
